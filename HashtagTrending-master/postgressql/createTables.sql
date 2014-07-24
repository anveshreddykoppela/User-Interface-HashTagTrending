CREATE TABLE hashtags
(
  tweetid bigint NOT NULL,
  hashtag character varying(100) NOT NULL,
  breaking character(1)
);


CREATE TABLE retweet
(
  tweetid bigint NOT NULL,
  uidfrom bigint NOT NULL,
  uidto bigint NOT NULL
);


CREATE TABLE tweet
(
  tweetid bigint NOT NULL,
  text character varying(200) NOT NULL,
  createdat character varying(50) NOT NULL,
  geo character varying(30) DEFAULT NULL::character varying,
  place character varying(100) DEFAULT NULL::character varying,
  retweetcount bigint NOT NULL DEFAULT 0::bigint,
  userid bigint NOT NULL
);


CREATE TABLE url
(
  tweetid bigint NOT NULL,
  url character varying(2000) NOT NULL
);


CREATE TABLE "user"
(
  userid bigint NOT NULL,
  name character varying(45) NOT NULL,
  screenname character varying(45) NOT NULL,
  url character varying(600) DEFAULT NULL::character varying,
  location character varying(30) DEFAULT NULL::character varying,
  language character varying(45) DEFAULT NULL::character varying
);


CREATE TABLE user_mention
(
  tweetid bigint NOT NULL,
  userid bigint NOT NULL,
  screenname character varying(45) NOT NULL
);


CREATE TABLE hashtag_picked
(
  hashtag character varying(45) NOT NULL,
  count integer,
  mean double precision,
  sigma double precision,
  date character varying(50) NOT NULL,
  last_modified timestamp without time zone DEFAULT now(),
  id serial NOT NULL,
  CONSTRAINT pk_hashtag_picked PRIMARY KEY (hashtag, date)
);

