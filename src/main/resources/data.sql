insert into user(id, username, email_address, description, password)
values
(10000, 'johndoe', 'john.doe@foo.com', 'TBD', '$2a$04$PeDIisKVAvzYHZ3sRFKT/.9sQuI/vWBubOZGbKAe4ixU5S.0aRs6u'),
(10001, 'janedoe', 'jane.doe@foo.com', 'TBD', '$2a$04$hixdKmmaeROeQk9Jk8S02uHM3e/OEQqfgElhPGpvHwnuS5K9o6pFO'),
(10002, 'Rusty', 'rusty@rr.com', 'Some juicy stuff..', '$2a$04$Z2D8weOvoMk9hnD6fxGpQ.11AcMy2apd8TPt.GeiWEqjc3tC9IXcm'),
(10003, 'Mr.Smiley', 'onion@foo.bar', '<span style="font-size: 20px; color: lightred;">&#9786;</span>', '$2a$04$hGbwrcTDuX7CBtZkpFiWt.BzNXyFvMm7GrwkrB5ACRv1N7vZ/tDoy');

insert into event(id, event_name, event_date, organizer_id)
values
(20000, 'Spring Booty', '2017-01-30', 10000),
(20001, 'April Fools'' Day Event', '2017-04-01', 10001);

insert into event_attendees(user_id, event_id)
values
(10000, 20000),
(10002, 20000),
(10003, 20000),
(10001, 20001),
(10002, 20001),
(10003, 20001);