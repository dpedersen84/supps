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

insert into goal(name)
	values 
	('Build Muscle'),
	('Burn Fat'),
	('Increase Energy'),
	('Increase Health'),
	('Increase Strength');

create table category(
	id serial primary key,
	name varchar(25) not null
);

insert into category(name)
	values
	('Protein'),
	('Pre-Workout'),
	('Appetite Surpressant'),
	('Vitamins'),
	('Creatine');

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

insert into product(name, price, inventory, goalId, categoryId) values
	('Buzz', 35.00, 10, 3, 2),
	('Pro', 55.00, 10, 1, 1),
	('Yo!', 25.00, 10, 3, 5);

create table review(
	id serial primary key not null,
	productId int not null references product(productId),
	rating int not null,
	description varchar(300)
);

insert into review(productid, rating, description)
	values(1, 5, 'Best ever!'), (2, 5, 'Awesome!');

create table public."user"(
	id serial primary key not null,
	name varchar(100) not null,
	email varchar(50) not null,
	isAdmin bool not null,
	password varchar(100) not null
);

insert into public."user"(name, email, isAdmin, password)
	values('Dan P', 'email@email.com', true, 'password');

create table orders(
	orderId serial primary key not null,
	totalPrice decimal(5,2) not null,
	orderDate date not null,
	userId int not null references public."user"(id)
);

insert into orders(totalPrice, orderDate, userId)
	values(90.00, '2019-01-01', 1);
	
create table orderproduct(
	orderId int not null references orders(orderId),
	productId int not null references product(productId),
	primary key(orderId, productId)
);

insert into orderproduct(orderId, productId) values
	(1, 1),
	(1, 2);