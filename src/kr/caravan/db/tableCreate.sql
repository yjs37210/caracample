-- drop --
drop table cars cascade constraints;
drop table customer cascade constraints;
drop table consult cascade constraints;
drop table review cascade constraints;

-- sequence --
drop sequence customer_num_seq;
create sequence customer_num_seq
increment by 1
start with 1
maxvalue 999;

drop sequence con_num_seq;
create sequence con_num_seq
increment by 1
start with 1
maxvalue 999;

-- table --
create table cars(      
   car_name varchar2(20),
   pw varchar2(30) NOT NULL,
   constraint cars_carName_pk primary key(car_name)
);

insert into cars(car_name, pw)
values('CaravanA','1234');
insert into cars(car_name, pw)
values('CaravanB','1234');
insert into cars(car_name, pw)
values('CaravanC','1234');
insert into cars(car_name, pw)
values('CaravanD','1234');
insert into cars(car_name, pw)
values('CaravanE','1234');
insert into cars(car_name, pw)
values('CaravanF','1234');
insert into cars(car_name, pw)
values('CaravanG','1234');
insert into cars(car_name, pw)
values('CaravanH','1234');
insert into cars(car_name, pw)
values('CaravanI','1234');
insert into cars(car_name, pw)
values('CaravanAdmin','1234');

create table customer(
   num number ,
   name varchar2(20) not null,
   tel varchar2(30) not null,
   car_name varchar2(20) references cars(car_name),
   male number(2) default 0,
   female number(2) default 0,
   check_in timestamp(0) default sysdate,
   check_out timestamp(0) default trunc(sysdate,'dd') +1+1/2,
   able varchar2(2) default '1',
   constraint customer_num_pk primary key(num)
);

insert into customer(num,name,tel,car_name,female)
values(customer_num_seq.nextval,'오유정','01000000001','CaravanA',4);
insert into customer(num,name,tel,car_name,male,female)
values(customer_num_seq.nextval,'양기영','01000000002','CaravanB',3,2);
insert into customer(num,name,tel,car_name,male,female)
values(customer_num_seq.nextval,'신은정','01000000003','CaravanC',1,3);
insert into customer(num,name,tel,car_name,male,female)
values(customer_num_seq.nextval,'최광','01000000004','CaravanE',3,1);
insert into customer(num,name,tel,car_name,male,female)
values(customer_num_seq.nextval,'김재휘','01000000005','CaravanH',2,3);

create table consult (
   con_num number,
   num number references customer(num),
   cmt varchar2(100),
   time timestamp(0) default sysdate,
   complete varchar2(2) default '0',
   constraint consult_con_num_pk primary key(con_num)
);

create table review(
   num number references customer(num),
   score number(2),
   cmt_review varchar2(1000),
   time timestamp(0) default sysdate
);

insert into review(num,score,cmt_review)
values(1,5,'너무 좋았어요');
insert into review(num,score,cmt_review)
values(2,3,'청결에 조금 더 신경써주셨으면 좋겠습니다.');


-- view --
create or replace view month_view AS
select * from(
   select extract(month from check_in) as month,sum(male) as male,sum(female) as female
   from customer
   group by extract(month from check_in)
   order by extract(month from check_in)
);



create or replace view review_view AS
select * from(
   select r.num as num, c.name as name, c.car_name as car_name
   ,r.time as write_time,r.score as score
   from review r,customer c
   where r.num=c.num
);
