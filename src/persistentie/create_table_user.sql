create table USER
(
username varchar(50) unique,
password_hashed varchar(500),
salt varchar(16),
is_admin boolean,
name varchar(50),
firstname varchar(50)
);