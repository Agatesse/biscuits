insert into biscuits_kid (
kid_nickname,
kid_image,
kid_biscuits
)
values
  (
  'Hanaé',
  '/assets/images/secret-mission-stamp.jpg',
  12
  ),
  (
  'Momiji',
  '/assets/images/secret-mission-stamp.jpg',
  25
  ),
  (
  'Gwen',
  '/assets/images/secret-mission-stamp.jpg',
  52
  ),
  (
  'Manon',
  '/assets/images/secret-mission-stamp.jpg',
  3
  );

insert into biscuits_mission (
mission_action,
mission_image,
mission_done,
mission_biscuits,
mission_kid_id
)
values
  (
  'Brush your teeth',
  '/assets/images/secret-mission-stamp.jpg',
  false,
  5,
  3
  ),
  (
  'Make your bed',
  '/assets/images/secret-mission-stamp.jpg',
  false,
  3,
  1
  ),
  (
  'Feed pets',
  '/assets/images/secret-mission-stamp.jpg',
  false,
  10,
  2
  ),
  (
  'Clean your room',
  '/assets/images/secret-mission-stamp.jpg',
  false,
  15,
  2
  ),
  ('Get dressed',
  '/assets/images/secret-mission-stamp.jpg',
  false,
  3,
  1
  );

