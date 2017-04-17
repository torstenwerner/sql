-- docker run -d --name postgres -p 5432:5432 postgres:alpine
-- docker run -i --rm --link postgres:postgres postgres:alpine psql -h postgres -U postgres sen < world.sql
-- docker stop postgres
-- docker start postgres

select * from city;

select countrycode, sum(percentage) from countrylanguage GROUP BY countrycode;

SELECT countrycode, language, percentage, rank() OVER (PARTITION BY countrycode ORDER BY percentage DESC)
  FROM countrylanguage ORDER BY countrycode, percentage DESC;

select c.name, to_char((c.population * cl.percentage) / 100, '9999999999') as speakers, cl.language
  from country c JOIN countrylanguage cl on c.code = cl.countrycode;

select to_char(sum(c.population * cl.percentage) / 100, '9999999999') as speakers, cl.language
  from country c JOIN countrylanguage cl on c.code = cl.countrycode
  GROUP BY cl.language ORDER BY speakers DESC;

select sum(population) from country;

select
  to_char(sum(c.population * cl.percentage) / 100 / (select sum(population) from country), '0.999999') as percentage,
  cl.language
  from country c JOIN countrylanguage cl on c.code = cl.countrycode
  GROUP BY cl.language ORDER BY percentage DESC;

WITH pl AS (SELECT
  to_char(sum(c.population * cl.percentage) / 100 / (select sum(population) from country), '0.999999') as percentage,
  cl.language
from country c JOIN countrylanguage cl on c.code = cl.countrycode
GROUP BY cl.language ORDER BY percentage DESC)
SELECT row_number() OVER () as rank, percentage, language FROM pl;
