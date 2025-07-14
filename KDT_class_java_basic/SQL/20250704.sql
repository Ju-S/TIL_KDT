select
    emp_name �����,
    salary * 12 ����,
    '��' ����
from employee;

-- ��� ������ �̸�, ����, ���Ǽ��ɾ��� ����ϼ���
-- �� ���ɾ��� (���� - (������ 3%))

SELECT
    EMP_NAME �̸�,
    SALARY*12 ����,
    SALARY - (SALARY*0.03) "�� �Ǽ��ɾ�"
FROM EMPLOYEE;

desc EMPLOYEE;
-- ���̺� �Ӽ� ���

-- ��¥ �����Ϳ� ���ؼ��� ��������� ������
-- + �Ǵ� - �� ����
-- ���� ���ڸ�ŭ �ϼ��� ���ϰų� ����
select HIRE_DATE, HIRE_DATE + 1 from EMPLOYEE;

-- 1���� ���� ���� dummy���� ���� dual���̺�
select * from dual;
-- sysdate�� ��¥
-- ��¥ - ��¥�� �� ���� ���� �ϼ��� ���
select sysdate - hire_date from EMPLOYEE;

-- ����Ŭ���� '||' �����ڴ� ���ڿ� ���� ����� �����Ѵ�.
select 
    EMP_NAME �����,
    floor((sysdate - hire_date)/ 365) || '��' �ټӳ��
from EMPLOYEE;

-- employee ���̺��� 20�� �̻� �ټ��� �ټ����� �̸�, �޿�, ���ʽ���, �ټӳ�� ���
select
    emp_name �̸�,
    salary || '��' �޿�,
    nvl(bonus, 0.0) ���ʽ���,
    floor((sysdate - hire_date)/ 365) || '��' �ټӳ��
from employee
where floor((sysdate - hire_date)/ 365) >= 20;

select * from employee where bonus is null;
select * from employee where bonus is not null;

-- where (��) not in (����)
-- ���ǿ� �������� ���� ���� ���

-- where, between and, and, or in, not in, is null,  is not null, alias - as, ||
-- like, not like

select * from employee
    where emp_name like '%��%';
-- '%': regex *�� ���� �ǹ� �ڿ� n���� ����

select * from employee
    where emp_name like '��__';
-- '_': ������� ������ŭ �ִ� �͸� ���

------------

-- �̸��� ������ ������ ����� �̸��� ���
select
    emp_name �����
from employee
where emp_name like '%��';

-- ��¥ �����͵� '>', '<', between and, '<=', '>=' ���� �����ڸ� ������ �� ����.
select * from employee where hire_date between '00/01/01'and '10/12/31';

-- ���� �� �����ּҿ� 's' �� ���鼭, dept_code�� D9 or D6�̰�,
-- ������� 90/01/01���� 05/12/01���� �̸鼭, �޿��� 270���� �̻��� ������ ��ü ���� ���

select * from employee
    where
        email like '%s%'
        and hire_date between '90/01/01' and '05/12/01'
        and dept_code in ('D9', 'D6')
        and salary >= 2700000;
    
-- order by (���� ��) (desc: descending, asc: ascending)
select * from employee order by salary desc;
select * from employee order by salary asc;

-- ���� n��°�ε� ���� ����
select * from employee order by 7;

select * from employee order by dept_code desc, job_code asc, 9 desc;
------------------------------------------------
-- ���� 1.
-- �ټӳ���� 5�� �̻�, 10�� ������ ������ �̸�, �ֹι�ȣ, �޿�, �Ի����� �˻�
select
    emp_name �����,
    emp_no �ֹι�ȣ,
    salary �޿�,
    hire_date �Ի���
from employee
where 5 <= ((sysdate - hire_date) / 365) and ((sysdate - hire_date) / 365) <= 10;

-- ���� 2.
-- �������� �ƴ� ������ �̸�, �μ��ڵ带 �˻�(��� ����: ENT_YN)
select
    emp_name �����,
    nvl(dept_code, '����') �μ��ڵ�
from employee
where ENT_YN like 'Y';

-- ���� 3.
-- �ټӳ���� 10�� �̻��� �������� �˻�
-- ��� ���: �̸�, �޿�, �ټӳ��(�Ҽ���x)�� �ټӳ���� ������������ ����
-- ��, �޿��� 50%�λ�� �޿��� ���
select
    emp_name �����,
    salary * 1.5 �޿�,
    floor((sysdate - hire_date) / 365) || '��' �ټӳ��
from employee
where floor((sysdate - hire_date) / 365) <= 10
order by floor((sysdate - hire_date) / 365) asc;

-- ���� 4.
-- �Ի����� 99/01/01 ~ 10/01/01�� ��� �߿��� �޿��� 2000000�� ������ �����
-- �̸�, �ֹι�ȣ, �̸���, ����ȣ, �޿� �˻� ���
select
    emp_name �̸�,
    emp_no �ֹι�ȣ,
    email �̸���,
    phone ��ȭ��ȣ,
    salary �޿�
from employee
where
    hire_date between '99/01/01' and '10/01/01' and
    salary <= 2000000;

-- ���� 5.
-- �޿��� 2000000�� ~ 3000000���� ������ �߿��� 4�� �����ڸ� �˻��Ͽ� ���
-- ��, �̸� ,�ֹι�ȣ, �޿�, �μ��ڵ带 �ֹι�ȣ ������(��������) ����ϼ���.
-- ��, �μ��ڵ尡 null�� ����� �μ��ڵ尡 '����'���� ���
select
    emp_name �̸�,
    emp_no �ֹι�ȣ,
    salary �޿�,
    nvl(dept_code, '����') �μ��ڵ�
from employee
where
    2000000 <= salary and salary <= 3000000 and
    emp_no like '__04__-2______'
order by emp_no desc;

-- ���� 6.
-- ���� ��� �� ���ʽ��� ���� ����� ���ñ��� �ٹ����� �����Ͽ�
-- 1000�ϸ��� (�Ҽ��� ����)
-- �޿��� 10% ���ʽ��� ����Ͽ� �̸�, Ư�� ���ʽ�(��� �ݾ�) ����� ���
-- ��, �̸� ������ ���� ���� �����Ͽ� ���
select
    emp_name �̸�,
    salary * (floor((sysdate - hire_date) / 1000) * 0.1 + 1) Ư�����ʽ�
from employee
where
    bonus is null and
    emp_no like '______-1______'
order by emp_name;
