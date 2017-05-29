-- some kind of MaybeSQL database

SELECT a1.*
FROM attribute a1
WHERE a1.key = 'l' AND a1.value ILIKE '%Berlin%'
ORDER BY a1.userid ASC;

SELECT a0.*
FROM attribute a0
WHERE a0.userid IN (
  SELECT a1.userid
  FROM attribute a1
  WHERE a1.key = 'l' AND a1.value ILIKE '%Berlin%')
ORDER BY a0.userid ASC;

SELECT a0.*
FROM attribute a0
WHERE a0.userid IN (
  SELECT a1.userid
  FROM attribute a1
  WHERE a1.key = 'l' AND a1.value ILIKE '%Berlin%'
        AND exists(SELECT 1
                   FROM attribute a2
                   WHERE a2.userid = a1.userid AND a2.key = 'sn' AND a2.value ILIKE '%Muster%'))
ORDER BY a0.userid ASC;

SELECT a0.*
FROM attribute a0
WHERE a0.userid IN (
  SELECT a1.userid
  FROM attribute a1
  WHERE a1.key = 'l' AND a1.value ILIKE '%Berlin%'
        AND exists(SELECT 1
                   FROM attribute a2
                   WHERE a2.userid = a1.userid AND a2.key = 'sn' AND a2.value ILIKE '%Muster%'
                         AND exists(SELECT 1
                                    FROM attribute a3
                                    WHERE a3.userid = a1.userid AND a3.key = 'givenName' AND a3.value ILIKE '%Hans%')))
ORDER BY a0.userid ASC;

-- an ldap query to the sql query above:
-- (&(l=*Berlin*)(sn=*Muster*)(givenName=*Hans*))

SELECT a0.*
FROM attribute a0
  JOIN attribute s0 ON a0.userid = s0.userid
WHERE s0.key = 'givenName' AND a0.userid IN (
  SELECT a1.userid
  FROM attribute a1
    JOIN attribute s1 ON a1.userid = s1.userid
  WHERE s1.key = 'givenName' AND a1.key = 'l' AND a1.value ILIKE '%Berlin%'
        AND exists(SELECT 1
                   FROM attribute a2
                   WHERE a2.userid = a1.userid AND a2.key = 'sn' AND a2.value ILIKE '%Muster%'
                         AND exists(SELECT 1
                                    FROM attribute a3
                                    WHERE a3.userid = a1.userid AND a3.key = 'givenName' AND a3.value ILIKE '%a%'))
  ORDER BY s1.value DESC
  OFFSET 0
  LIMIT 2)
ORDER BY s0.value DESC;
