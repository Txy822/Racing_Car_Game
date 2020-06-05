 CREATE DATABASE racing_game_database; /*to create database */

USE racing_game_database;/* To create a table inside the newly created Database */

/*to create the table called player_detail with the following parameter	*/
CREATE TABLE player_detail(
id int not null primary key AUTO_INCREMENT,
user_name varchar(55) not null,
email varchar(55) not null,
password varchar(300) not null);

/*to create the table called leader_board with the following parameter	*/
CREATE TABLE leader_board(
id int not null primary key ,
time int not null,
track_id int not null,
player_rank int not null);

/*to create the table called player_time with the following parameter	*/
CREATE TABLE players_time(
id int not null primary key ,
time int not null,
track_id int not null);
