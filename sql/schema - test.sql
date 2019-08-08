drop table if exists review;
drop table if exists product_ingredient;
drop table if exists order_product;
drop table if exists public."order";
drop table if exists product;
drop table if exists ingredient;
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

create table ingredient(
	id serial primary key,
	name varchar(50) not null,
	benefit varchar(100) not null
);

create table product(
	id serial primary key not null,
	name varchar(100) not null,
	price decimal(5,2) not null,
	inventory int not null,
	goalId int not null references goal(id),
	categoryId int not null references category(id),
	description varchar(200),
	image varchar(300)
);

create table product_ingredient(
	productId int not null references product(id),
	ingredientId int not null references ingredient(id),
	primary key(productId, ingredientId)
);

create table review(
	id serial primary key not null,
	productId int not null references product(id),
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

create table public."order"(
	id serial primary key not null,
	totalPrice decimal(5,2) not null,
	orderDate date not null,
	userId int not null references public."user"(id)
);
	
create table order_product(
	orderId int not null references public."order"(id),
	productId int not null references product(id),
	primary key(orderId, productId)
);