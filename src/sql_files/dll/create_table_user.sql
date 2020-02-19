create table user
(
id int not null primary key,
username varchar(50) unique,
password_hashed varchar(500),
salt varchar(16),
is_admin boolean
);