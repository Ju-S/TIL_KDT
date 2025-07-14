----- DML(Data Manipulation Language)
-- select, insert, update, delete와 같이 데이터의 CRUD
select * from cafe;

insert into cafe values(cafe_seq.nextval, 'Americano', 1800, 'y');
delete from cafe where id = 1009;

----- DDL(Data Definition Language)
-- create user / create sequence
-- create table
-- 컬럼명, 자료형, 제약 조건(primary key, unique, not null)
create table cafe(
    id number primary key,
    name varchar(100) not null,
    price number not null,
    is_ice char(1) check(is_ice in ('y', 'n')) not null
);

drop table cafe;
-- primary key(식별자 컬럼 지정, 중복 방지, null방지)
-- not null(null방지)
-- check - 컬럼에 저장될 수 있는 데이터 필터링
-- unique(중복 방지)

-- Foreign key - 외래키
-- 두 테이블 간 데이터 참조 무결성을 확보
-- A테이블의 특정 값을 B테이블에서 참조할 경우
-- A테이블이 parent, B테이블이 child가 되며 child테이블은 parent테이블에 존재하는 컬럼이 아니면
-- 데이터로 저장할 수 없음.
-- B테이블이 참조할 수 있는 A테이블의 컬럼은 primary key 또는 unique컬럼만 가능.
insert into cafe values(cafe_seq.nextval, 'Cafe Latte', 2500, 'y');
insert into cafe values(cafe_seq.nextval, 'Cafe Mocha', 3500, 'y');

create table sales(
    id number primary key,
    pid references cafe(id) on delete cascade, -- 외래키(다른 테이블의 컬럼을 참조하여 생성): 데이터 참조 무결성을 위해 사용
    sales_date timestamp default sysdate not null
);
-- 외래키의 parent key가 지워진 경우 옵션(on delete (cascade, set null))

drop table sales;

create sequence sales_seq
start with 1
increment by 1
nomaxvalue
nocache
;

insert into sales values(sales_seq.nextval, (select id from cafe where name = 'Cafe Mocha'), default);
insert into sales values(sales_seq.nextval, 1010, default);

select * from sales;

alter user kedu identified by kedu;
alter table cafe drop column is_ice;
desc cafe;
alter table cafe add is_ice char(1) default 'y' not null check(is_ice in ('y', 'n'));

select * from cafe_menu;

-- 테이블 컬럼 이름변경
alter table cafe rename column is_ice to iceable;

-- 테이블 이름변경
alter table cafe rename to cafe_menu;

-- 외래키 child테이블에서 parent테이블 이름은 자동 변경됨.
desc sales;

-- 테이블 컬럼 자료형 변경
alter table cafe_menu modify (iceable char(2));
-- 바꾼 테이블 컬럼의 다른 정보는 그대로 유지된다.
desc cafe_menu;

-- Dictionary: Oracle DBMS 설치 시, 기본적으로 생성되는 내장 테이블
select * from user_tables;

select * from user_sequences;

select * from user_constraints where table_name='CAFE_MENU';
select * from user_constraints where table_name='SALES';

alter table sales drop constraint SYS_C007039;
alter table cafe_menu drop constraint SYS_C007033;

alter table cafe_menu add primary key(id);

----- DCL(Data Control Language): 권한 부여 및 반환
-- grant / revoke
create user tester identified by tester;
grant connect to tester;
grant resource to tester;

grant select on kedu.employee to tester;

revoke select on kedu.employee from tester;
-- resource를 revoke해도 drop table은 가능.
revoke resource from tester;

----- TCL(Transaction Control Language)

----- Object - View

-- 1. 개발팀 전용 계정 생성
-- 계정명 dev
-- connect, resource 권한 부여
create user dev identified by dev;
grant connect, resource to dev;

-- 2. employee 테이블의 부분 데이터 테이블을 생성
create table employee_dev
as
select emp_id, emp_name, phone, email from employee;

select * from employee_dev;

-- 3. dev계정에게 employee_dev 를 select할 수 있는 권한 부여 후 확인
grant select on kedu.employee_dev to dev;

-- 4. employee 테이블에 대한 부분 데이터 view 생성
create view employee_dev_view
as
select emp_id, emp_name, phone, email from employee;
drop view employee_dev_view;
--grant create view to kedu; : 관리자접속에서
select * from employee_dev_view; --view는 원본테이블에서 관리해줘서 동기화가 따로 필요가 없다.
update employee set emp_name='선동이' where emp_id=200;

--5. dev개정에 employee_dev_view를 select할 수 있는권한 부여 후 확인
grant select on kedu.employee_dev_view to dev;
revoke select on kedu.employee_dev_view  from dev;

--6. kedu 에서 employee 테이블에 변화를 주면 dev에서 view를 통해 변화가 실시간 감지되는지 확인.
update employee set employee.emp_name='유재석' where emp_id=204;
SELECT * FROM employee;
select * from employee_dev_view;

--TCL(Transaction Control Languag) : commit / rollback
--Transaction : 오라클에서 작업을 처리하는 단위
commit; --일을 저질러버리다. / Transaction에 누적된 쿼리를 DB에 실제 반영하겠음.
rollback; --Transaction에 누적된 모든 쿼리를 취소


-- 개발자 관점에서 작업의 "원자성" 을 위해 필수적 요소
select * from employee;
delete from employee; --자료를 지워버린경우