-- 2025/07/07

-- function - ����Ŭ ���� �޼���

-- length / lengthb
-- length 
select length ('Hello') from dual; I
select length ('�ѱ�') from dual; I
--  lengthb (byte)
select lengthb ('Hello') from dual; I
select lengthb ('�ѱ�') from dual; I 

-- instr : ã�� ���ڰ� ���ڿ� ������ ��ġ�ϴ� �ε��� ���� ��ȯ
-- �ڹ��� index of �� ������ ���
select instr('Hello World Hi High','H', 1, 1)  from dual;

-- substr : ���ڿ� ������ �ʿ��� ��ŭ�� ���ڿ��� �����Ͽ� ��ȯ
select substr('Hello World Hi High',1,2)  from dual;  --  ��� �� :  He
select substr('Hello World Hi High',7,5)  from dual;  --  ��� �� : World

-- Quiz  : employee ���̺��� ���� ������� �����ȣ, ����� , �ֹι�ȣ , ������ ��� �ϼ���. ( �� , like ������ ������� ������ )
select *  from employee;

select
emp_name �����,
emp_id �����ȣ,
emp_no �ֹι�ȣ,
salary*12 ����
from
employee
where
substr(emp_no ,8,1) = 1;

-- floor : �Ҽ��� ���� �Լ�
-- round : ���� �� �ݿø� �Լ� 
select round (123.456,3) from dual;
select round (123.456,2) from dual;
select round (123.454,2) from dual;
-- select round (123.�Ҽ��� �Ʒ��� ù ��° �ι� ° ���� ° , �� ��° �Ҽ������� �ݿø� �Ұų�) from dual;
select round (123.454,0) from dual;
select round (126.454,-1) from dual;
--  (123.454,0) = (-1 , 1, 0 . 1,2,3) �� �ڸ� : �Ҽ��� ���� ������ �ݿø� ����

-- ceil : �ø� �Լ�
select ceil (123.456) from dual;

-- sysdate :���� �ð��� ��� �� �� �ִ� ���� �ʵ� ( System. curr)
-- ���� �ð��� ��� �ִ� ���� = �Լ�ó�� �� �� ����
select sysdate from dual;

-- months_between : �� ��¥�� ���ڷ� �޾�, �� ��¥ ������ ���� �� ����
select emp_name , floor (months_between(sysdate , hire_date)) from employee;

-- add_months : Ư�� ��¥�κ��� n���� ���� ��¥�� ��ȯ
select add_months (sysdate,6)  from dual; -- 6���� �Ŀ� ��¥�� �˾ƺ���

-- next_day : ù ��° �Ķ���ͷ� ���� ��¥�κ���, �� ��° �Ķ���ͷ� ���� ���Ͽ� �ش��ϴ� ���� ����� ��¥ �� ��ȯ
select next_day(sysdate , '��') from dual; -- ���÷κ��� ���� ����� ����� �˾ƺ���

-- last_day : ù ��° ���ڷ� ���޵� ��¥�� ���� ���� ������ ���� ��ȯ
select last_day(sysdate) from dual; 
-- Quiz : ���� �� ������ ��¥�� ? 
select last_day(add_months(sysdate,1)) from dual;

-- extract : ��¥�� ���� , ��/��/�� �� �����Ͽ� ��ȯ
select extract(year from sysdate ) from dual; -- �⵵��
select extract(month from sysdate ) from dual; -- ����
select extract(day from sysdate ) from dual; -- �ϸ� 

-- Quiz : ����� �̸�, �Ի��� , ������ ��� �ϼ���.  (��, �Ի��� ����� YYYY�� M��D�� ���·� ��� �ϼ���.)
select *  from employee;

select 
emp_name �����,
extract (year  from hire_date) || '��' ||
extract (month  from hire_date) || '��' ||
extract (day  from hire_date) || '��' || �Ի���,
ceil((sysdate-hire_date)/365) ����
from
empolyee
order by 3;

-- ����ȯ �Լ�
-- to_char : ��¥ �Ǵ� ���ڸ� ���ڿ��� ��ȯ�ϴ� �Լ�
-- �ڹ��� SimpleDateFoemat �� ��¥ �� ���ڿ�
-- �ڹ��� String.valueOf ��Ÿ Type �� ���ڿ�
select to_char (sysdate, 'YYYY-MM-DD') from dual;
select to_char (sysdate, 'YYYY/MM/DD') from dual;

select to_char (sysdate, 'YYYY��MM��DD��') from dual; -- ������. ��,��.�� �����ڷ� to_char�� ���� ���� ����. 
select to_char (sysdate, 'YYYY"��"MM"��"DD"��"') from dual; -- "" �� �־ ����ϰԲ� ����� ��� ����

select to_char (sysdate, 'HH:MI:SS')from dual; -- ���� �ð� ���
select to_char (sysdate, 'HH"��"MI"��"SS"��"')from dual; 

select to_char (sysdate, 'day') from dual; -- ���� ���� ��� (������)
select to_char (sysdate, 'dy') from dual; -- (��)

-- Quiz : �����, ������� ��� �ϼ���. ( ����� ��� ��� : 1990/02/06 ȭ ) 
select
emp_name �����,
to_char(hire_date, 'YYYY/MM/DD(DY)')
from
employee;

 -- ���ڵ� ���ϴ� ���·� ����� �� �� ����, ���� ���ں��� ��� ������ �� ���� ��, ª���� ����
select to_char(1000000, 'L9,999,999') from dual;
-- �տ� L �� �ٿ� �ָ� �ش� ������ ��ȭ�� ����
select
emp_name �����,
to_char(hire_date, 'YYYY/MM/DD(DY)') �����,
ltrim(to_char(salary, 'L999,999,999') ) �޿� -- ltrim()  �÷� ���̿� ������ ����ִ� �Լ�
from
employee;

-- to_date : ���ڿ� �� ��¥
-- �ڹٿ��� SimpleDateFormat �� parse���
select to_date (20000101,'yyyymmdd') from dual;

--Quiz 1 : 08152002 �� 2002��08��15�Ϸ� ����� ������
select to_char(to_date ('08152002', 'mmddyyyy'),'yyyy"��"mm"��"dd"��"') from dual;
-- �� ���ڰ� 0�̶� ''  ���������
-- to_char �� "��""��""��" ����  �����ֱ�

-- Quiz 2 : ���� ũ���������� ���� ���� �ϱ��?
select to_char(to_date(20251225, 'yyyymmdd'),'yyyy"��"mm"��"dd"��"day') from dual;

-- decode : ���� �Լ�
-- �ڹٷ� ġ�� ���� ������ �Ǵ� swich���� �ش�

-- ���� ������ ��� �غ���
select 
    emp_id,
    emp_name,
    decode(substr(emp_no, 8, 1), 1, '��', 2, '��', '�ܰ���') -- ù ��° �Ķ���Ϳ� 2 ��° �Ķ���Ͱ� ���ٸ� 3 ��° �Ķ���͸� ��� �ض� and...
from employee;                                                               -- 2���� ¦�� ������ �񱳽��� ��� ���� �� ����.  = ���� ���� �Լ�
                                                                                                -- ¦�� �������� ����  ' ' �� else ����
                                                                                                --  swich���̶� ���  < > �񱳽� ���� = �� ����
                                                
-- case : ���� �Լ�
-- �ڹٷ� ġ�� if���� �ش� : �񱳽� ����

select
    emp_id,
    emp_name,
    case
        when substr(emp_no, 8, 1) in (1, 3) then '��'
        when substr(emp_no, 8, 1) in (2, 4) then '��'
        else 'none'
    end ����
from employee;

-- ������ 60��� ������ ���ؼ���
-- 65�� ���� ������ 60��� �ʹ�
-- 65�� ���� ũ�ų� ������ 60��� �Ĺ� ���� ���
-- �̸��� �ʹ����� �Ĺ������� ���

select * from employee;

select
    emp_name,
    case
        when substr(emp_no, 1, 2) < 65 then '60��� �ʹ�'
        when substr(emp_no, 1, 2) >= 65 then '60��� �Ĺ�'
    end �ʹ��Ĺ�
from employee
where substr(emp_no, 1, 1) = 6;

select nvl(bonus, 0) from employee;

-----------------------------------------------------������� �� �Լ�����
-----------------------------------------------------����׷� �Լ�����
-- �׷�ȭ�� ������ Ȥ�� �׷��Լ������� ��� ����
-- emp_name�� ���� ���� �÷��� ��� �Ұ�

-- sum : �հ� �Լ�
select sum(salary) from employee;

-- avg: ��� �Լ�
select to_char(avg(salary), 'L999,999,999') from employee;

-- max: �ִ밪 �Լ�
select max(salary) from employee;

-- min: �ּҰ� �Լ�
select min(salary) from employee;

-- count: ���� ī���� �Լ�
select count(*) from employee;

select sum(salary), avg(salary), max(salary), min(salary), count(*) from employee;

-- D9 �μ��� ���� �ο��� �� ����ϱ��?
select count(*) from employee where dept_code in ('D9');

-----Quiz
select * from employee;

-- 1.
select
    emp_name �����,
    email �̸���,
    length(email) "�̸��� ����"
from employee;

-- 2.
select
    emp_name �����,
    substr(email, 1, instr(email, '@') - 1) ���̵�
from employee;

-- 3.
select
    emp_name �����,
    substr(emp_no, 1, 2) ���,
    nvl(bonus * 100, 0) || '%' "���ʽ� ��"
from employee
where substr(emp_no, 1, 1) = 6;

-- 4.
select count(*) || '��' from employee where substr(phone, 1, 3) != '010';

-- 5.
select
    emp_name �����,
    to_char(hire_date, 'yyyy"��"MM"��"') �Ի���
from employee;

-- 6.
select
    emp_name �����,
    substr(emp_no, 1, 8) || '******' �ֹι�ȣ
from employee;

-- 7.
select
    emp_name �����,
    job_code �����ڵ�,
    case
        when bonus is null then to_char(salary * 12, 'L999,999,999')
        else to_char(salary * (bonus + 1) * 12, 'L999,999,999')
    end ����
from employee;

-- 8.
select
    count(*) || '��'
from employee
where dept_code in ('D5', 'D9') and extract(year from hire_date) = 2004;


select
    emp_id ���,
    emp_name �����,
    dept_code �μ��ڵ�,
    hire_date �Ի���
from employee
where dept_code in ('D5', 'D9') and substr(hire_date, 1, 2) = 04;
-- 9.
select
    emp_name �����,
    hire_date �Ի���,
    floor(sysdate - hire_date) "�ٹ��ϼ�(�ָ�����)"
from employee;

-- 10.
select
    emp_name �����,
    dept_code �μ��ڵ�,
    case
        when dept_code in ('D5', 'D6', 'D9') then '�߱�'
        else '�߱پ���'
    end �߱�����
from employee
order by dept_code asc;

-- 11.
select
    nvl(dept_code, '����') �μ��ڵ�,
    emp_name �����,
    case
        when dept_code in ('D5') then '10%'
        when dept_code in ('D2') then '20%'
        when dept_code in ('D9') then '30%'
        else '����'
    end ���ʽ���,
    case
        when dept_code in ('D5') then salary * 0.1
        when dept_code in ('D2') then salary * 0.2
        when dept_code in ('D9') then salary * 0.3
        else 0
    end "Ư�� ���ʽ���"
from employee;

-- 12.
select
    emp_name �����,
    floor((sysdate - hire_date) / 365) + 1 ����,
    case
        when floor((sysdate - hire_date) / 365) + 1 < 10 then 'Junior'
        when floor((sysdate - hire_date) / 365) + 1 < 20 then 'Intermediate'
        when floor((sysdate - hire_date) / 365) + 1 >= 20 then 'Senior'
    end ���޷���
from employee
order by floor((sysdate - hire_date) / 365) + 1;

-- group by -> ������ �׷�ȭ ����
select
    nvl(dept_code, '����') �μ��ڵ�,
    to_char(sum(salary), 'L999,999,999') "�� �޿�"
from employee
group by nvl(dept_code, '����');

select
    nvl(job_code, '����') �����ڵ�,
    to_char(avg(salary), 'L999,999,999') "��� �޿�"
from employee
group by nvl(job_code, '����')
order by nvl(job_code, '����') asc;

-- select ������ ���� ����
-- select       -- 5
-- from         -- 1
-- where        -- 2
-- group ny     -- 3
-- having       -- 4
-- order by     -- 6

select
    nvl(dept_code, '����') �μ��ڵ�,
    count(*) || '��'
from employee
where bonus is null
group by nvl(dept_code, '����')
order by 1; 

-- �������� ���� �� �޿� ���, �޿� �հ�, �ο����� ���
select
    decode(substr(emp_no, 8, 1), 1, '��', 3, '��', 2, '��', 4, '��') ����,
    to_char(avg(salary), 'L999,999,999') "�޿� ���",
    to_char(sum(salary), 'L999,999,999') "�޿� �հ�",
    count(*) "�ο� ��"
from employee
group by decode(substr(emp_no, 8, 1), 1, '��', 3, '��', 2, '��', 4, '��')
order by 1;

-- �� �� �̻��� group by�� �����ϴ�
select
    dept_code,
    job_code,
    to_char(sum(salary), 'L999,999,999') "�޿� �հ�"
from employee
group by dept_code, job_code
order by 1, 2;

-------- �� �μ� �� ���� �� �ο� ���� ���
select
    nvl(dept_code, '����') �μ��ڵ�,
    decode(substr(emp_no, 8, 1), 1, '��', 2, '��') ����,
    count(*) || '��' �ο���
from employee
group by nvl(dept_code, '����'), decode(substr(emp_no, 8, 1), 1, '��', 2, '��')
order by 1;

-------- �� ���� �� ���� �� �ο� ���� ���
select
    nvl(job_code, '����') �μ��ڵ�,
    case
        when substr(emp_no, 1, 1) = 6 then '60����'
        when substr(emp_no, 1, 1) = 7 then '70����'
        when substr(emp_no, 1, 1) = 8 then '80����'
        else 'etc'
    end ����,
    count(*) || '��' �ο���
from employee
group by
    nvl(job_code, '����'),
    case
        when substr(emp_no, 1, 1) = 6 then '60����'
        when substr(emp_no, 1, 1) = 7 then '70����'
        when substr(emp_no, 1, 1) = 8 then '80����'
        else 'etc'
    end
order by 1;


