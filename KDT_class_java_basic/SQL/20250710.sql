----- DML(Data Manipulation Language)
-- select, insert, update, delete�� ���� �������� CRUD
select * from cafe;

insert into cafe values(cafe_seq.nextval, 'Americano', 1800, 'y');
delete from cafe where id = 1009;

----- DDL(Data Definition Language)
-- create user / create sequence
-- create table
-- �÷���, �ڷ���, ���� ����(primary key, unique, not null)
create table cafe(
    id number primary key,
    name varchar(100) not null,
    price number not null,
    is_ice char(1) check(is_ice in ('y', 'n')) not null
);

drop table cafe;
-- primary key(�ĺ��� �÷� ����, �ߺ� ����, null����)
-- not null(null����)
-- check - �÷��� ����� �� �ִ� ������ ���͸�
-- unique(�ߺ� ����)

-- Foreign key - �ܷ�Ű
-- �� ���̺� �� ������ ���� ���Ἲ�� Ȯ��
-- A���̺��� Ư�� ���� B���̺��� ������ ���
-- A���̺��� parent, B���̺��� child�� �Ǹ� child���̺��� parent���̺� �����ϴ� �÷��� �ƴϸ�
-- �����ͷ� ������ �� ����.
-- B���̺��� ������ �� �ִ� A���̺��� �÷��� primary key �Ǵ� unique�÷��� ����.
insert into cafe values(cafe_seq.nextval, 'Cafe Latte', 2500, 'y');
insert into cafe values(cafe_seq.nextval, 'Cafe Mocha', 3500, 'y');

create table sales(
    id number primary key,
    pid references cafe(id) on delete cascade, -- �ܷ�Ű(�ٸ� ���̺��� �÷��� �����Ͽ� ����): ������ ���� ���Ἲ�� ���� ���
    sales_date timestamp default sysdate not null
);
-- �ܷ�Ű�� parent key�� ������ ��� �ɼ�(on delete (cascade, set null))

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

-- ���̺� �÷� �̸�����
alter table cafe rename column is_ice to iceable;

-- ���̺� �̸�����
alter table cafe rename to cafe_menu;

-- �ܷ�Ű child���̺��� parent���̺� �̸��� �ڵ� �����.
desc sales;

-- ���̺� �÷� �ڷ��� ����
alter table cafe_menu modify (iceable char(2));
-- �ٲ� ���̺� �÷��� �ٸ� ������ �״�� �����ȴ�.
desc cafe_menu;

-- Dictionary: Oracle DBMS ��ġ ��, �⺻������ �����Ǵ� ���� ���̺�
select * from user_tables;

select * from user_sequences;

select * from user_constraints where table_name='CAFE_MENU';
select * from user_constraints where table_name='SALES';

alter table sales drop constraint SYS_C007039;
alter table cafe_menu drop constraint SYS_C007033;

alter table cafe_menu add primary key(id);

----- DCL(Data Control Language): ���� �ο� �� ��ȯ
-- grant / revoke
create user tester identified by tester;
grant connect to tester;
grant resource to tester;

grant select on kedu.employee to tester;

revoke select on kedu.employee from tester;
-- resource�� revoke�ص� drop table�� ����.
revoke resource from tester;

----- TCL(Transaction Control Language)

----- Object - View

-- 1. ������ ���� ���� ����
-- ������ dev
-- connect, resource ���� �ο�
create user dev identified by dev;
grant connect, resource to dev;

-- 2. employee ���̺��� �κ� ������ ���̺��� ����
create table employee_dev
as
select emp_id, emp_name, phone, email from employee;

select * from employee_dev;

-- 3. dev�������� employee_dev �� select�� �� �ִ� ���� �ο� �� Ȯ��
grant select on kedu.employee_dev to dev;

-- 4. employee ���̺� ���� �κ� ������ view ����
create view employee_dev_view
as
select emp_id, emp_name, phone, email from employee;
drop view employee_dev_view;
--grant create view to kedu; : ���������ӿ���
select * from employee_dev_view; --view�� �������̺��� �������༭ ����ȭ�� ���� �ʿ䰡 ����.
update employee set emp_name='������' where emp_id=200;

--5. dev������ employee_dev_view�� select�� �� �ִ±��� �ο� �� Ȯ��
grant select on kedu.employee_dev_view to dev;
revoke select on kedu.employee_dev_view  from dev;

--6. kedu ���� employee ���̺� ��ȭ�� �ָ� dev���� view�� ���� ��ȭ�� �ǽð� �����Ǵ��� Ȯ��.
update employee set employee.emp_name='���缮' where emp_id=204;
SELECT * FROM employee;
select * from employee_dev_view;

--TCL(Transaction Control Languag) : commit / rollback
--Transaction : ����Ŭ���� �۾��� ó���ϴ� ����
commit; --���� ������������. / Transaction�� ������ ������ DB�� ���� �ݿ��ϰ���.
rollback; --Transaction�� ������ ��� ������ ���


-- ������ �������� �۾��� "���ڼ�" �� ���� �ʼ��� ���
select * from employee;
delete from employee; --�ڷḦ �����������