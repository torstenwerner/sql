-- some kind of MaybeSQL database

SELECT a1.*
FROM attribute a1
WHERE a1.key = 'l' AND a1.value ILIKE '%Berlin%';

SELECT a0.*
FROM attribute a0
WHERE a0.userid IN (
  SELECT a1.userid
  FROM attribute a1
  WHERE a1.key = 'l' AND a1.value ILIKE '%Berlin%');

SELECT a0.*
FROM attribute a0
WHERE a0.userid IN (
  SELECT a1.userid
  FROM attribute a1
  WHERE a1.key = 'l' AND a1.value ILIKE '%Berlin%'
        AND exists(SELECT 1
                   FROM attribute a2
                   WHERE a2.userid = a1.userid AND a2.key = 'sn' AND a2.value ILIKE '%Muster%'));

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
                                    WHERE a3.userid = a1.userid AND a3.key = 'givenName' AND a3.value ILIKE '%Hans%'))
  OFFSET 0
  LIMIT 2);

SELECT a0.*
FROM attribute a0
  JOIN attribute s ON a0.userid = s.userid
WHERE s.key = 'givenName' AND a0.userid IN (
  SELECT a1.userid
  FROM attribute a1
  WHERE a1.key = 'l' AND a1.value ILIKE '%Berlin%'
        AND exists(SELECT 1
                   FROM attribute a2
                   WHERE a2.userid = a1.userid AND a2.key = 'sn' AND a2.value ILIKE '%Muster%'
                         AND exists(SELECT 1
                                    FROM attribute a3
                                    WHERE a3.userid = a1.userid AND a3.key = 'givenName' AND a3.value ILIKE '%a%'))
  OFFSET 0
  LIMIT 2)
ORDER BY s.value DESC;
