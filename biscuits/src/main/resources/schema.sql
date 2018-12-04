drop table if exists biscuits_mission, biscuits_kid;
create table if not exists biscuits_mission (
  mission_id bigint auto_increment primary key,
  mission_action text not null,
  mission_image text not null,
  mission_done boolean not null,
  mission_biscuits integer not null
);

create table if not exists biscuits_kid (
  kid_id bigint auto_increment primary key,
  kid_nickname text not null,
  kid_image text not null,
  kid_biscuits integer not null,
);