-- Создать SQL скрипт инициализирующий создание новой базы данных.
-- Создать SQL скрипт создающий таблицы для хранения структуры системы заявок.
-- Создать SQL скрипт заполняющий начальные данные для системы заявок.
-- Скрипты должны выполняться последовательно.

-- Дроп базы, если нужно. 
-- drop table role_access CASCADE;
-- drop table users_role CASCADE;
-- drop table users CASCADE;
-- drop table items_status CASCADE;
-- drop table items_category CASCADE;
-- drop table items CASCADE;
-- drop table comments CASCADE;
-- drop table added_files CASCADE;

-- Права ролей.
create table role_access(
	id serial primary key,
	access_level character varying(1000) NOT NULL
);
-- Роли.
create table users_role(
	id serial primary key,
	role_name character varying(300),
	access_level int references role_access(id)
);
--  Пользователи.
create table users(
	id serial primary key,
	login character varying(2000),
	password character varying(100),
	user_role int references users_role(id),
	create_date timestamp
);
-- Состояние заявки.
create table items_status(
	id serial primary key,
	name character varying(100) unique
);
-- Категории заявки.
create table items_category(
	id serial primary key,
	name character varying(100) unique
);
-- Заявки.
create table items(
	id serial primary key,
	category text references items_category(name),
	status text references items_status(name),
	description text
);
-- Комментарии Заявок.
create table comments(
	id serial primary key,
	description text,
	item_id integer references items(id)
);
-- Приложенные Файлы.
create table added_files(
	id serial primary key,
	name text
);



-- Инит. Права ролей.
insert into role_access(access_level) values('full');
insert into role_access(access_level) values('admin');
insert into role_access(access_level) values('user');
insert into role_access(access_level) values('none');
-- Инит. Роли.
insert into users_role(role_name, access_level) values('root', 1);
insert into users_role(role_name, access_level) values('admin', 2);
insert into users_role(role_name, access_level) values('user', 3);
insert into users_role(role_name, access_level) values('bad_user', 4);
-- Инит. Пользователи.
insert into users(login, password, user_role) values('login_000', 'password_000', 1);
insert into users(login, password, user_role) values('login_001', 'password_001', 2);
insert into users(login, password, user_role) values('login_002', 'password_002', 3);
insert into users(login, password, user_role) values('login_003', 'password_003', 4);
-- Инит. Состояние заявки.
insert into items_status(name) values('confirmed');
insert into items_status(name) values('processing');
insert into items_status(name) values('started');
insert into items_status(name) values('finished');
-- Инит. Категории заявки.
insert into items_category(name) values('bug');
insert into items_category(name) values('update');
insert into items_category(name) values('task');
insert into items_category(name) values('offer');
-- Инит. Заявки.
insert into items(category, status, description) values('bug', 'started', 'мелкий баг.');
insert into items(category, status, description) values('update', 'finished', 'большое обновление.');
insert into items(category, status, description) values('task', 'finished', 'задача для Junior.');
insert into items(category, status, description) values('offer', 'confirmed', 'новое предложение по улучшению.');
-- Инит. Комментарии Заявок.
insert into comments(description, item_id) values('это интересно.', 1);
insert into comments(description, item_id) values('так и легко.', 2);
-- Инит. Приложенные Файлы.
insert into added_files(name) values('something_just_this.mp3');
insert into added_files(name) values('new_jpg.jpg');

