CREATE TABLE attribute
(
  userid VARCHAR NOT NULL,
  key    VARCHAR NOT NULL,
  value  VARCHAR NOT NULL,
  CONSTRAINT attribute_userid_key_pk
  PRIMARY KEY (userid, key)
);

INSERT INTO public.attribute (userid, key, value) VALUES ('hans', 'givenName', 'Hans');
INSERT INTO public.attribute (userid, key, value) VALUES ('hans', 'sn', 'Muster');
INSERT INTO public.attribute (userid, key, value) VALUES ('hans', 'l', 'Berlin');
INSERT INTO public.attribute (userid, key, value) VALUES ('anna', 'givenName', 'Anna');
INSERT INTO public.attribute (userid, key, value) VALUES ('anna', 'sn', 'Lehmann');
INSERT INTO public.attribute (userid, key, value) VALUES ('anna', 'l', 'Berlin');
INSERT INTO public.attribute (userid, key, value) VALUES ('max', 'givenName', 'Max');
INSERT INTO public.attribute (userid, key, value) VALUES ('max', 'sn', 'Muster');
INSERT INTO public.attribute (userid, key, value) VALUES ('max', 'l', 'Berlin');
INSERT INTO public.attribute (userid, key, value) VALUES ('nina', 'givenName', 'Nina');
INSERT INTO public.attribute (userid, key, value) VALUES ('nina', 'sn', 'Meyer');
INSERT INTO public.attribute (userid, key, value) VALUES ('nina', 'l', 'Hamburg');
