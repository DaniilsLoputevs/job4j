drop if exists company, person;
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

insert into company(id, name) values ('1', 'first');
insert into company(id, name) values ('2', 'second');
insert into company(id, name) values ('3', 'third');

insert into person(id, name, company_id) values ('100', 'one', '1');
insert into person(id, name, company_id) values ('200', 'two', '2');
insert into person(id, name, company_id) values ('300', 'three', '3');


-- TASK
-- 1) имя компании = человек имена.
-- все кто ВНЕ компании с id = 5.
select company.name, person.name
from person join company on person.id = company.id
group by company.name, person.name;






-- 2) компания с макс. людей и её численность.
