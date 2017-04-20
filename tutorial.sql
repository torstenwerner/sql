-- check if language percentages add up to 100%
SELECT
  countrycode,
  sum(percentage)
FROM countrylanguage
GROUP BY countrycode;

-- calculate rounded number of speakers of each language in each country
SELECT
  c.name,
  round((c.population * cl.percentage) / 100) AS speakers,
  cl.language
FROM country c
  JOIN countrylanguage cl ON c.code = cl.countrycode;

-- calculate rounded number of speakers of each language in the world; sort by speakers descending
SELECT
  round(sum(c.population * cl.percentage) / 100) AS speakers,
  cl.language
FROM country c
  JOIN countrylanguage cl ON c.code = cl.countrycode
GROUP BY cl.language
ORDER BY speakers DESC;

-- calculate the world population
SELECT sum(population)
FROM country;

-- calculate world population, average and median population of all countries, europe's population
SELECT
  sum(population) AS sum,
  round(avg(population)) as average,
  percentile_cont(0.5) WITHIN GROUP (ORDER BY population) as median,
  sum(population) FILTER (WHERE continent = 'Europe') as europe
FROM country;

-- calculate number of cities per country, sort by city count descending
SELECT co.name, (SELECT count(*) FROM city ci WHERE ci.countrycode = co.code) AS citycount
  FROM country co
  ORDER BY citycount DESC;

-- convert first 10 countries to a JSON array of country objects
SELECT json_agg(c) FROM country c LIMIT 10;

-- first 10 countries as JSON with 2 fields: country name and languages which is an array of json objects
SELECT json_agg(cs) FROM
  (SELECT c.name, (
    SELECT json_agg(cl.*) FROM countrylanguage cl WHERE cl.countrycode = c.code
  ) AS languages
   FROM country c) cs
LIMIT 10;

-- calculate the percentage of each language in the world; format percentage like 0.123456; sort by percentage descending
SELECT
  to_char(sum(c.population * cl.percentage) / 100 / (SELECT sum(population)
                                                     FROM country), '0.999999') AS percentage,
  cl.language
FROM country c
  JOIN countrylanguage cl ON c.code = cl.countrycode
GROUP BY cl.language
ORDER BY percentage DESC;

-- as above but add a rank column e.g. chinese 1, hindi 2, ...
SELECT
  row_number()
  OVER (
    ORDER BY percentage DESC )    AS rank,
  language,
  to_char(percentage, '0.999999') AS percentage
FROM (
       SELECT
         sum(c.population * cl.percentage) / 100 / (SELECT sum(population)
                                                    FROM country) AS percentage,
         cl.language
       FROM country c
         JOIN countrylanguage cl ON c.code = cl.countrycode
       GROUP BY cl.language) AS pl;

-- sort countries by population descending and add a rank column e.g. china 1, india 2
SELECT
  code,
  name,
  row_number()
  OVER (
    ORDER BY population DESC ) AS rank,
  population
FROM country;

-- calculated rank of language – sorted by percentage descending – in each country
SELECT
  c.code,
  cl.language,
  row_number()
  OVER (
    PARTITION BY countrycode
    ORDER BY percentage DESC ) AS rank,
  cl.percentage
FROM country c
  JOIN countrylanguage cl ON c.code = cl.countrycode;

-- combine the tasks above – display 6 columns sorted by population rank and language rank:
-- country name, rank of country by population, population, language, rank of language within the country, percentage
SELECT
  cr.name,
  cr.rank AS poprank,
  cr.population,
  lr.language,
  lr.rank AS langrank,
  lr.percentage
FROM (
       SELECT
         code,
         name,
         row_number()
         OVER (
           ORDER BY population DESC ) AS rank,
         population
       FROM country
     ) AS cr
  JOIN (
         SELECT
           c.code,
           cl.language,
           row_number()
           OVER (
             PARTITION BY countrycode
             ORDER BY percentage DESC ) AS rank,
           cl.percentage
         FROM country c
           JOIN countrylanguage cl ON c.code = cl.countrycode) AS lr
    ON cr.code = lr.code
ORDER BY cr.rank, lr.rank;
