
--계정생성 (명령프롬프트 또는 system계정에서 가능)
CREATE USER phonedb IDENTIFIED BY phonedb;
grant resource, connect to phonedb;

--테이블 / 시퀀스 삭제
drop table person;
drop SEQUENCE seq_person_id;

commit;
rollback;

--테이블 생성
create table person(
    person_id NUMBER(5),
    name VARCHAR2(30) not null,
    hp VARCHAR2(20),
    company VARCHAR2(20),
    PRIMARY KEY (person_id)
);
--시퀀스 생성
create SEQUENCE seq_person_id
  INCREMENT BY 1
  start with 1;
  
---------------------

--inset문
insert into person
values(seq_person_id.nextval, '이효리', '010-1111-1111', '02-1111-1111');

insert into person
values(seq_person_id.nextval, '정우성', '010-2222-2222', '02-2222-2222');

insert into person
values(seq_person_id.nextval, '유재석', '010-3333-3333', '02-3333-3333');

insert into person
values(seq_person_id.nextval, '이정재', '010-4444-4444', '02-4444-4444');

insert into person
values(seq_person_id.nextval, '서장훈', '010-5555-5555', '02-5555-5555');

--select문
SELECT  person_id,
        name,
        hp,
        company
FROM person;

--update문
update person
set hp = '02-9999-9999',
    company = '02-9999-9999'
where person_id = 4;
    
--delete문
delete from person
where person_id = 5;

--검색문
SELECT  person_id,
        name,
        hp,
        company
FROM person
where person_id like '%7%'
or name like '%이%'
or hp like '%3%'
or company like '%3%';