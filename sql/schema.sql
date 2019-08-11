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
	('Increase Strength'),
	('Mental Clarity');

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
	headline varchar(200),
	image varchar(300)
);

insert into product(name, price, inventory, goalId, categoryId, headline, image) values
	('Buzz', 35.00, 10, 3, 2, 'The highest caffeinated pre-workout on the market!', 'https://i.imgur.com/9XFRtjA.png'),
	('Pro', 55.00, 10, 1, 1, '30 grams of whey protein isolate in one serving of our Pro whey protein!', 'https://i.imgur.com/rUoVS2K.png'),
	('Yo!', 25.00, 10, 3, 2, 'Lower dose caffeine in this super formulated pre-workout product.', 'https://i.imgur.com/5gNCSrG.jpg'),
	('Veg Pro', 65.00, 10, 1, 1, '25 grams of veggie protein formulated for Vegetarians and Vegans looking to gain quality muscle!', 'https://i.imgur.com/aDvQDmF.png'),
	('Starve Me', 22.00, 10, 2, 3, 'Kill your appetite with Starve Me!', 'https://i.imgur.com/ILzRkXU.png'),
	('sPills', 30.00, 10, 4, 4, 'Increase overall health with our sPills multi-vitamins!', 'https://i.imgur.com/qCb1M6S.png'),
	('Creature', 45.00, 10, 5, 5, 'Strength gains through the roof with Creature pure creatine powder!', 'https://i.imgur.com/gckBXHx.png'),
	('Brain Food', 30.00, 10, 6, 4, 'Gain mental focus and clarity with our proprietary blend of vitamins and minerals', 'https://i.imgur.com/VlZvrpo.png');

create table review(
	id serial primary key not null,
	productId int not null references product(productId),
	rating int not null,
	description varchar(300)
);

insert into review(productid, rating, description) values
	(1, 5, 'Best ever!'), 
	(2, 5, 'Awesome!'),
	(2, 2, 'Tasted like crap!'),
	(2, 5, 'Greate product!');

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

insert into orders(totalPrice, orderDate, userId) values
	(90.00, '2019-01-01', 1),
	(25.00, '2019-02-01', 1);
	
create table orderproduct(
	orderId int not null references orders(orderId),
	productId int not null references product(productId),
	primary key(orderId, productId)
);

insert into orderproduct(orderId, productId) values
	(1, 1),
	(1, 2),
	(2, 3);
	