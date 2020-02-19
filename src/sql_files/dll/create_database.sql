CREATE DATABASE sokoban;

CREATE USER 'sokoban_user'@'localhost' IDENTIFIED BY 'sokoban';
GRANT ALL ON sokoban.* TO 'sokoban_user'@'localhost';