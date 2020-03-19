drop table if exists type CASCADE;
drop table if exists product CASCADE;


create table type (
	id serial primary key,
	name character varying(1000)
);
create table product (
	id serial primary key,
	name character varying(1000),
	type_id integer references type(id),
	expired_date timestamp,
	price integer
);


insert into type(name) values ('СЫР'), ('МОЛОКО'), ('РЫБА'), ('МЯСО');


insert into product(name, type_id, expired_date, price)
values ('сгущеное молко', 2, '2020-03-19 21:15:58.430634', 70);
insert into product(name, type_id, expired_date, price)
values ('местное молко', 2, '2020-03-19 21:15:58.430634', 55);

insert into product(name, type_id, expired_date, price)
values ('Сыр "восточный"', 1, '2029-03-19 21:15:58.430634', 75);
insert into product(name, type_id, expired_date, price)
values ('Белорусский сыр', 1, '2020-04-19 21:15:58.430634', 95);

insert into product(name, type_id, expired_date, price)
values ('мороженное мясо', 4, '2020-04-19 21:15:58.430634', 70);


-- 1) получение всех продуктов с типом "СЫР"
select * from product where type_id=2;
-- 2) получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where name like '%мороженное%';
-- 3) который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product where expired_date between '2020-04-01 00:00:00.430634' and '2020-04-30 00:00:00.430634';
-- 4) который выводит самый дорогой продукт.
select name, price from product order by price DESC limit 1;
-- 5) который выводит количество всех продуктов определенного типа.
select * from product where type_id=1;  -- тут меняем цыфру, вибираем нужный тип.
-- 6) получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product where type_id=1 or type_id=2;
-- 7) который выводит тип продуктов, которых осталось меньше 10 штук. (сделал не 10, а меньше 2)
select type.name from type, product where product.type_id = type.id group by type.id having count(type.id) < 2;
-- 8) Вывести все продукты и их тип.
select product.name, type.name from type, product where product.type_id = type.id;