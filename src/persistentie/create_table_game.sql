create table GAME
(
id int not null primary key auto_increment,
name varchar(50) unique
);

create table GAMEBOARD
(
id	int not null primary key auto_increment,
game_id int not null
);

alter table GAMEBOARD add foreign key (game_id) references GAME(id);

create table TILE 
(
id	int not null primary key auto_increment,
gameboard_id int not null,
row_index	int not null,
column_index int not null,
type varchar(50) not null,
contains_player boolean not null
);

alter table TILE add foreign key(gameboard_id) references GAMEBOARD(id);
alter table TILE add unique key(gameboard_id, row_index, column_index);
