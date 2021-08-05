drop table if exists car_body CASCADE;
drop table if exists engine CASCADE;
drop table if exists transmission CASCADE;
drop table if exists car CASCADE;



create table car_body (
	id     serial primary key,
	name   character varying(110)
);
create table engine (
	id     serial primary key,
	name   character varying(120)
);
create table transmission (
	id     serial primary key,
	name   character varying(130)
);
create table car (
	id                serial primary key,
	name              character varying(100),
	car_body_id       int references car_body(id),
	engine_id         int references engine(id),
	transmission_id   int references transmission(id)
);


insert into car_body(name)       values ('mk1'), ('mk2'), ('mk2-1'), ('mk-3');
insert into engine(name)         values ('v500'), ('v600'), ('v500m'), ('v750');
insert into transmission(name)   values ('A'), ('M'), ('AM'), ('AF');
insert into car(name, car_body_id, engine_id, transmission_id) values
('Дура, не разбей!!!', 2, 2, 2), -- XDXDXD
('Абсолют Огурчик', 3, 3, 3), -- XDXDXD
('Местный караван с картошкой', 4, 4, 4),  -- XDXDXD
('Не тарантай, а Бюлбдозер!', 2, 3, 4); -- XDXDXD


-- 1) Вывести список всех машин и все привязанные к ним детали.
select car.name, body.name, e.name, t.name from car
	left join car_body as body    on body.id = car.car_body_id
	left join engine as e         on e.id = car.engine_id
	left join transmission as t   on t.id = car.transmission_id;

-- 2) Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select body.name, e.name, t.name from car
	full outer join car_body as body    on car.car_body_id = body.id
	full outer join engine as e         on e.id = car.engine_id
	full outer join transmission as t   on t.id = car.transmission_id
where car.name is null;
