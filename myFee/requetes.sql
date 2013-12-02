-- ************
-- CREATE TABLE
-- ************
create table category (id integer primary key, label TEXT, color TEXT, budget NUMERIC);
create table fee (id integer primary key, date DATE, amount NUMERIC, label TEXT, categoryid NUMERIC);

-- ************
-- INSERT
-- ************
insert into category values(1,'Restaurant','#ffffff',100);
insert into category values(2,'Courses','#3c3c3c',200.5);
insert into fee values(1,'2013-11-28',15.25,'Kebab',1);
insert into fee values(2,'2013-11-27',7,'Subway',1);
insert into fee values(3,'2013-11-26',8.9,'McDo',1);
insert into fee values(4,'2013-11-25',15.25,'Simply',2);
insert into fee values(5,'2013-11-24',15.25,'Paris Store',2);
insert into fee values(6,'2013-11-23',15.25,'Brice',2);
insert into fee values(7,'2013-11-22',15.25,'Fnac',2);


-- VUE D'ENSEMBLE
	-- total des depenses pour le mois actuel et libellé
select sum(amount) as total, case strftime('%m', date('now')) when '01' then 'Janvier' when '02' then 'Fevrier' when '03' then 'Mars' when '04' then 'Avril' when '05' then 'Mai' when '06' then 'Juin' when '07' then 'Juillet' when '08' then 'Aout' when '09' then 'Septembre' when '10' then 'Octobre' when '11' then 'Novembre' when '12' then 'Decembre' else '' end
as label
from fee
where strftime('%m%Y', date) =  strftime('%m%Y', date('now'));

	-- liste les categories "utilisées" le mois actuel avec leur total et pourcentage
select c.label, sum(f.amount) as total, sum(f.amount)/tmp.totmonth as percent
from fee f, category c, (select sum(amount) as totmonth
						from fee
						where strftime('%m%Y', date) =  strftime('%m%Y', date('now'))) as tmp
where c.id = f.categoryid 
	and strftime('%m%Y', f.date) =  strftime('%m%Y', date('now'))
group by c.id
order by total desc;

	-- liste des depenses pour le mois actuel trié par categorie
select f.id, f.date, f.amount, f.label, c.label as catlab
from fee f, category c 
where c.id = f.categoryid 
	and strftime('%m%Y', f.date) =  strftime('%m%Y', date('now'))
order by f.date desc;

-- CATEGORIE
	-- liste des categories
select id, label, color, budget from category;

	-- details d'une categorie
select id, label, color, budget from category where label = '';

-- HISTORIQUE
	-- liste des libellé des categories
select label from category;

	-- list des mois avec leur total
select sum(amount) as total,  strftime('%m', date) month
from fee
where strftime('%Y', date) =  strftime('%Y', date('now'))
group by strftime("%m-%Y", date);


select sum(amount) as total,  case strftime('%m', date) when '01' then 'Janvier' when '02' then 'Fevrier' when '03' then 'Mars' when '04' then 'Avril' when '05' then 'Mai' when '06' then 'Juin' when '07' then 'Juillet' when '08' then 'Aout' when '09' then 'Septembre' when '10' then 'Octobre' when '11' then 'Novembre' when '12' then 'Decembre' else '' end
as label
from fee
where strftime('%Y', date) =  strftime('%Y', date('now'))
group by strftime("%m-%Y", date);


	-- list des depenses de l'année
select f.id, f.date, f.amount, f.label, c.color, c.label, case strftime('%m', date) when '01' then 'Janvier' when '02' then 'Fevrier' when '03' then 'Mars' when '04' then 'Avril' when '05' then 'Mai' when '06' then 'Juin' when '07' then 'Juillet' when '08' then 'Aout' when '09' then 'Septembre' when '10' then 'Octobre' when '11' then 'Novembre' when '12' then 'Decembre' else '' end
as label
from fee f, category c 
where c.id = f.categoryid 
	and strftime('%Y', f.date) =  strftime('%Y', date('now'))
order by f.date desc;

-- BUDGET
	-- liste des details des budgets
select c.label, c.color, c.budget as goal, sum(f.amount) as total, sum(f.amount)/c.budget*100 as percent, c.budget- sum(f.amount) as diff
from fee f, category c
where c.id = f.categoryid 
	and strftime('%m%Y', f.date) =  strftime('%m%Y', date('now'))
group by c.id
order by total desc;
