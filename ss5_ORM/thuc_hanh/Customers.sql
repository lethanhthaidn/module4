create database module4;
use module4;
create table customer(
	id int(3) not null auto_increment,
	name varchar(120) NOT NULL,
	email varchar(220) NOT NULL,
	adress varchar(120),
	PRIMARY KEY (id)
);