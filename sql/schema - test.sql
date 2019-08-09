drop table if exists review;
drop table if exists orderproduct;
drop table if exists orders;
drop table if exists product;
drop table if exists goal;
drop table if exists category;
drop table if exists public."user";

create table goal(
	id serial primary key,
	name varchar(25) not null
);

create table category(
	id serial primary key,
	name varchar(25) not null
);

create table product(
	productId serial primary key not null,
	name varchar(100) not null,
	price decimal(5,2) not null,
	inventory int not null,
	goalId int not null references goal(id),
	categoryId int not null references category(id),
	description varchar(200),
	image varchar(300)
);

create table review(
	id serial primary key not null,
	productId int not null references product(productId),
	rating int not null,
	description varchar(300)
);

create table public."user"(
	id serial primary key not null,
	name varchar(100) not null,
	email varchar(50) not null,
	isAdmin bool not null,
	password varchar(100) not null
);

create table orders(
	orderId serial primary key not null,
	totalPrice decimal(5,2) not null,
	orderDate date not null,
	userId int not null references public."user"(id)
);
	
create table orderproduct(
	orderId int not null references orders(orderId),
	productId int not null references product(productId),
	primary key(orderId, productId)
);