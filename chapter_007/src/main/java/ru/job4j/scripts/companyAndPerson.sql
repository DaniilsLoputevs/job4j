drop table company, person;
--truncate table company, person;

CREATE TABLE IF NOT EXISTS company(
	id integer NOT NULL,
	name character varying,
	CONSTRAINT company_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS person(
	id integer NOT NULL,
	name character varying,
	company_id integer,
	CONSTRAINT person_pkey PRIMARY KEY (id)
);

----- TASK -----

insert into company(id, name) values ('1', 'first');
insert into company(id, name) values ('2', 'second');
insert into company(id, name) values ('3', 'third');
insert into company(id, name) values ('4', 'fourth');
insert into company(id, name) values ('5', 'fifth');

insert into person(id, name, company_id) values ('100', 'one', '1');
insert into person(id, name, company_id) values ('200', 'two', '2');
insert into person(id, name, company_id) values ('300', 'three', '3');
insert into person(id, name, company_id) values ('400', 'four', '4');
insert into person(id, name, company_id) values ('500', 'five', '5');
insert into person(id, name, company_id) values ('600', 'six', '5');



--Получить в одном запросе:
-- имя человека, имя компании.
-- * все кроме копании с id = 3
select company.name, person.name
from person join company on person.company_id = company.id
where company.id != 3;

-- компании с макс кол-во человек.
with tbl as (select company.name as c_names, count(person.company_id) as person_count
from company, person where person.company_id = company.id group by company.name)
select * from tbl where person_count >= all(select person_count from tbl);
