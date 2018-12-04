drop table if exists biscuits_user, biscuits_mission, biscuits_kid;

create table biscuits_user (
user_id serial primary key,
user_username character varying not null,
user_email character varying not null,
user_password character varying not null,
user_role character varying not null
);

create table biscuits_mission (
  mission_id serial primary key,
  mission_action character varying not null,
  mission_image character varying not null,
  mission_done boolean not null,
  mission_biscuits integer not null
);

create table biscuits_kid (
  kid_id serial primary key,
  kid_nickname character varying not null,
  kid_image character varying not null,
  kid_biscuits integer not null
);