create table if not exists CONFERENCE_TALK (
  id          SERIAL PRIMARY key,
  title       varchar,
  description text
);

create table if not exists TOPIC (
  id          SERIAL PRIMARY key,
  title VARCHAR
);