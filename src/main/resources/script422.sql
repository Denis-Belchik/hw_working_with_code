create table driver
(
	id serial primary key,
	car_id int references car (id) not null,
	name varchar(64) not null,
	age int not null check (age > 0),
	driver_license boolean
);

create table car
(
	id serial primary key,
	mark varchar(64) not null,
	model varchar(64) not null,
	price numeric(30,2) not null
);