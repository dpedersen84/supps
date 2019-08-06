drop table if exists review;
drop table if exists product_ingredient;
drop table if exists order_products;
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

create table ingredient(
	id serial primary key,
	name varchar(50) not null,
	benefit varchar(100) not null
);

insert into ingredient(name, benefit) values
('Whey Protein', 'Decrease appetite, increase protein consumption.'),
('Caffiene', 'Increase energy, surpress appetite.'),
('L-Citrulline Malate', 'Boost nitric oxide production to increase blood flow.'),
('Taurine', 'Increase muscle endurace.'),
('Beta Alanine', 'Increase muscle endurance.'),
('Betaine Anhydrous', 'Improve athletic performance'),
('Creatine Monohydrate', 'Increase strength.'),
('Create Ethyl-ester', 'Increase strength.'),
('Vitamin C', 'Improve physical endurace. Strengthen immune system.'),
('Vitamin E', 'Prevent inflammation, support immune function.'),
('Vitamin D3', 'Maintaine healthy bones and teeth.'),
('Vitamin B12', 'Red blood cell production.'),
('Vitamin B6', 'Promotes protein, fat and carbohydrate metabolism.'),
('Vitamin A', 'Immune system health, improve vision.'),
('Zinc', 'Boost immune system.');

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

insert into product(name, price, inventory, goalId, categoryId) values
	('Buzz', 35.00, 10, 3, 2),
	('Pro', 55.00, 10, 1, 1);

create table product_ingredient(
	productId int not null references product(id),
	ingredientId int not null references ingredient(id),
	primary key(productId, ingredientId)
);

insert into product_ingredient(productId, ingredientId) values
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(2, 1),
	(2, 13);

create table review(
	id serial primary key not null,
	rating int not null,
	description varchar(300)
);

insert into review(rating, description)
	values(5, 'Best ever!');

create table public."user"(
	id serial primary key not null,
	name varchar(100) not null,
	email varchar(50) not null,
	isAdmin bool not null,
	password varchar(100) not null
);
insert into public."user"(name, email, isAdmin, password)
	values('Dan P', 'email@email.com', true, 'password');

create table public."order"(
	id serial primary key not null,
	totalPrice decimal(5,2) not null,
	orderDate date not null,
	userId int not null references public."user"(id)
);

insert into public."order"(totalPrice, orderDate, userId)
	values(90.00, '2019-01-01', 1);
	
create table order_products(
	orderId int not null references public."order"(id),
	productId int not null references product(id),
	primary key(orderId, productId)
);

insert into order_products(orderId, productId) values
	(1, 1),
	(1, 2);