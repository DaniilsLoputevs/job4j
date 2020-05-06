-- Описание: 3 состояния у оповищения.
-- Да            = Согласие.
-- Нет           = Отказ.
-- Не прочитано. = Игнорированно.

drop table if exists meetings, users, attends;
--truncate table attends;

CREATE TABLE IF NOT EXISTS meetings (
	id serial PRIMARY KEY,
	name VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS users (
	id serial PRIMARY KEY,
	name VARCHAR(100)
);

-- TASK
-- таблица уведомлений.
create table if not exists attends (
	id            serial PRIMARY KEY,
	user_id       int references users(id),
	meeting_id    int references meetings(id),
	status   varchar(50) default('Не прочитано')
);

insert into meetings(name) values('в клуб');
insert into meetings(name) values('сборы на шторы');
insert into meetings(name) values('в кино');
insert into meetings(name) values('тусовка в субботу');

insert into users(name) values('Вася');
insert into users(name) values('Артём');
insert into users(name) values('Катя');

insert into attends(user_id, meeting_id) values(1, 1); -- Да
insert into attends(user_id, meeting_id) values(1, 2); -- Нет
insert into attends(user_id, meeting_id) values(1, 3); -- Не прочитано
insert into attends(user_id, meeting_id) values(1, 4); -- Да

insert into attends(user_id, meeting_id) values(2, 1); -- Нет
insert into attends(user_id, meeting_id) values(2, 3); -- Не прочитано

insert into attends(user_id, meeting_id) values(3, 2); -- Не прочитано
insert into attends(user_id, meeting_id) values(3, 4); -- Да


update attends set status = 'Да' where id=1;
update attends set status = 'Нет' where id=2;
update attends set status = 'Да' where id=4;
update attends set status = 'Нет' where id=5;
update attends set status = 'Да' where id=8;


-- Поиск Человек, Задача, ответ.
select users.name, meetings.name, m_u.status from attends as m_u
	join users        on m_u.user_id = users.id
	join meetings     on m_u.meeting_id = meetings.id
order by users.name;

-- Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.
-- * на будущие, что бы, понять что тут ты хотел сказать, нужно проивить смекалку. 
-- Лучше расписать немного подробнее этот пункт.
-- задача, кол-во 'Да' - давших согласие. Если задачи нет в Итоге, то значит все (Нет/Не прочитано)
select meetings.name, COUNT(attends.status) AS count_will_be
from attends join meetings on attends.meeting_id = meetings.id
where attends.status = 'Да'
group by meetings.name, attends.status;


-- НИЖЕ РАЗРАБОТКА
-- Нужно получить все совещания, где не было ни одной заявки на посещения
-- * То есть ни одного "Да"
select aa, more_one from (select meetings.name as aa, count(attends.meeting_id) as more_one
	from meetings left outer join attends on meetings.id = attends.meeting_id
	group by meetings.name) as OneTable 
	
left outer join (select meetings.name as bb, count(attends.meeting_id)
	from meetings left outer join attends on meetings.id = attends.meeting_id
	where attends.status = 'Да'
	group by meetings.name) as TwoTable
on aa=bb where bb is null;