drop table if exists biscuit_mission;
create table if not exists biscuit_mission (
  mission_id bigint auto_increment primary key,
  mission_action text not null,
  mission_image text not null,
  mission_done boolean not null,
  mission_biscuits int not null
);