-- group by / having / join / union

-- subquery
-- select * from employee where salary > (select avg(salary) from employee);

-- �޿��� ���� ���� �޴� ������ �̸��� �޿�, ���� ���� �޴� ������ �̸��� �޿��� ���
select
    emp_name,
    salary
from
    employee
where
    salary = (select max(salary) from employee) or
    salary = (select min(salary) from employee);
    
-- D1, D2 �μ����� �ٹ��ϴ� ��� �ֿ� �⺻�޿��� D5�μ��� �������� ��ձ޿����� ���� ������� �μ���ȣ, �μ���, ���, �����, �޿�    
select
    dept_code �μ���ȣ,
    dept_title �μ���,
    emp_id ���,
    emp_name �����,
    to_char(salary, 'L999,999,999') �޿�
from
    employee join department on dept_code = dept_id
where
    dept_code in ('D1', 'D2') and
    salary > (select avg(salary) from employee where dept_code in ('D5'));

-- ������ ��������
select dept_code from employee where emp_name in ('������', '�ڳ���');

select
    dept_code,
    emp_name
from
    employee
where
    dept_code in (select dept_code from employee where emp_name in ('������', '�ڳ���'));
    
-- ���¿�, ������ ����� �޿���ް� ���� ����� ���޸�� ������� ���
select
    job_name,
    emp_name
from
    employee e join job j on e.job_code = j.job_code
where
    sal_level in (select sal_level from employee where emp_name in ('���¿�', '������'));

-- ���޸��� ��ǥ, �λ����� �ƴ� ��� ������ �����, �����ڵ� ���
--select
--    emp_name,
--    e.job_code
--from
--    employee e join job j on e.job_code = j.job_code
--where
--    j.job_name not in ('��ǥ', '�λ���');

select
    emp_name,
    job_code
from
    employee
where
    job_code in (select job_code from job where job_name not in ('��ǥ', '�λ���'));

-- ���߿� ��������
select * from employee;
select dept_code, job_code from employee where emp_name = '���¸�';

select
    emp_name,
    dept_code,
    job_code
from
    employee
where
    (dept_code, job_code) = (select dept_code, job_code from employee where emp_name = '���¸�');
    
-- ������ 8�� 8�� �� ������ ���� �μ��ڵ�, �����ڵ带 ���� ������� �����, ����(mmdd), �μ��ڵ�, �μ����� ���
select
    emp_name �����,
    to_char(to_date(substr(emp_no, 3, 4), 'MMDD'), 'MM"��"DD"��"') ����,
    dept_code �μ��ڵ�,
    dept_title �μ���
from
    employee left join department on dept_code = dept_id
where
    (dept_code, job_code) in (select dept_code, job_code from employee where substr(emp_no, 3, 4) = '0808') and substr(emp_no, 3, 4) != '0808';
    
-- �� ���޺� �޿��� ���� ���� ������ �̸�, �޿�
select
    emp_name �����,
    e.job_code �����ڵ�,
    job_name ���޸�,
    to_char(salary, 'L999,999,999') �޿�
from
    employee e join job j on e.job_code = j.job_code
where
    (e.job_code, salary) in (select job_code, max(salary) from employee group by job_code)
order by e.job_code;

-- inline view : subquery�� from������ ����ϴ� ���
select * from (select emp_name from employee);

-- ��� ���� ����: main ������ �÷��� subquery�� ������ �ִ� ������ ��������
select emp_name, (select dept_title from department where dept_id = dept_code) from employee;

------------------------------------------------------------------------------------------------------------------

-- ��ŷ �Լ� 3����
-- rank(), dense_rank(), row_number() over(order by 'column name')
select
    emp_name,
    salary,
    dense_rank() over(order by salary desc)
from employee;

-- �޿� ���� ������ �������� ��, 5 - 10������ ���
select
    *
from
    (select
        emp_id,
        emp_name,
        salary,
        rank() over(order by salary desc) rank
    from employee)
where 5 <= rank and rank <= 10;

-- 1. �� ���޸� �� ���� ���� �ٹ��� ������ �̸��� �Ի����� ���
-- ����. ��¥�� Max/Min���� ����
select
    emp_name �����,
    job_name ���޸�,
    hire_date �Ի���
from
    employee e join job j on e.job_code = j.job_code
where
    (e.job_code, hire_date) in (select job_code, min(hire_date) from employee group by job_code)
order by e.job_code;

-- 2. �ٹ�����(�����̸�) �� ���� ���� �޿��� �޴� ������ ������, �����, �޿� ���
select
    nvl(n.national_name, '�̻�') ������,
    e.emp_name �����,
    to_char(e.salary, 'L999,999,999') �޿�
from
    employee e left join department d on e.dept_code = d.dept_id
    left join location l on d.location_id = l.local_code
    left join national n on l.national_code = n.national_code
where
    (nvl(n.national_code, 0), e.salary) in
    (select
        nvl(n2.national_code, 0),
        min(e2.salary)
    from
        employee e2 left join department d2 on e2.dept_code = d2.dept_id
        left join location l2 on d2.location_id = l2.local_code
        left join national n2 on l2.national_code = n2.national_code
    group by
        nvl(n2.national_code, 0))
order by salary;

-- 3. 