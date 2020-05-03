--drop table girl, toy;

CREATE TABLE if not exists girl (
  id serial PRIMARY KEY,
  name VARCHAR
);

CREATE TABLE if not exists toy (
  id serial PRIMARY KEY,
  name VARCHAR
);

ALTER TABLE girl
ADD toy_id int;

ALTER TABLE girl
ADD FOREIGN KEY (toy_id) REFERENCES toy(id);

insert into toy(name) values ('one');
insert into toy(name) values ('two');
insert into toy(name) values ('three');
insert into toy(name) values ('more one');
insert into toy(name) values ('more two');

insert into girl(name, toy_id) values ('first', 1);
insert into girl(name, toy_id) values ('second', 2);
insert into girl(name, toy_id) values ('third', 3);

-- имя девушки и её игрушки
select girl.name, toy.name from girl, toy where girl.toy_id=toy.id;

-- игрушки без хозяик.
select toy.name from girl
	full outer join toy on girl.toy_id = toy.id
	where girl.name is null;