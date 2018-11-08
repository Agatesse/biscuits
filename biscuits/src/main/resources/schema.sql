drop table if exists biscuit_mission;
create table if not exists biscuit_mission (
  mission_id bigint auto_increment primary key,
  mission_title text not null
);