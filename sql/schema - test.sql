drop table if exists review;
drop table if exists orderproduct;
drop table if exists orders;
drop table if exists product;
drop table if exists goal;
drop table if exists category;
drop table if exists users;

create table goal(
	id serial primary key,
	name varchar(25) not null
);

insert into goal(name) values 
	('Build Muscle');

create table category(
	id serial primary key,
	name varchar(25) not null
);

insert into category(name) values
	('Protein');

create table product(
	productId serial primary key not null,
	name varchar(100) not null,
	price decimal(5,2) not null,
	inventory int not null,
	goalId int not null references goal(id),
	categoryId int not null references category(id),
	headline varchar(200),
	image varchar(300)
);

insert into product(name, price, inventory, goalId, categoryId, headline, image) values
	('Buzz', 35.00, 10, 1, 1, 'The highest caffeinated pre-workout on the market!', 'https://i.imgur.com/X4bpIpS.png');

create table review(
	id serial primary key not null,
	productId int not null references product(productId),
	rating int not null,
	description varchar(300)
);

create table users(
	id serial primary key not null,
	username varchar(100) not null,
	isAdmin bool not null,
	password varchar(100) not null,
	role varchar(10)
);

create table orders(
	orderId serial primary key not null,
	totalPrice decimal(5,2),
	orderDate date not null,
	userId int not null references users(id),
	orderSent bool not null
);
	
create table orderproduct(
	orderId int not null references orders(orderId),
	productId int not null references product(productId),
	primary key(orderId, productId)
);
