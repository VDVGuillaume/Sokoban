create table USER
(
id int not null primary key auto_increment,
username varchar(50) unique,
password_hashed varchar(500),
salt varchar(16),
is_admin boolean
);