-- select
-- from. where. group by,having,select,order by
-- length,instr,substr,round,floor,ceil
-- sysdate,add_months,months_between,next_day,last_day
-- to_date,to_char,sum,avg,max,min,count
-- nvl,decode,case where-then else end

-- group by: ������ �׷�ȭ
SELECT
    dept_code,
    AVG(salary)
FROM
    employee
GROUP BY
    dept_code
ORDER BY dept_code;

-- having: ������ groupȭ�� ���� ����Ǵ� ����

SELECT
    dept_code,
    AVG(salary)
FROM
    employee
GROUP BY
    dept_code
HAVING
    AVG(salary) <= 2605000
ORDER BY 1;

-- where���� �׷�ȭ�� �����͸� ����������� having���� ������ �׷�ȭ�� �����͸� ��밡���ϴ�.

-- �μ� �� �ο����� �ľ����� �� 3�� �̸��� �μ� �ڵ� �� �ο� ���� ���

SELECT
    dept_code �μ��ڵ�,
    COUNT(*) �ο���
FROM
    employee
GROUP BY
    dept_code
HAVING
    COUNT(*) < 3
ORDER BY 1;

-- �μ� �� ��ձ޿��� 250���� �̸��� �μ��� ���������� �Ϸ��� �Ѵ�(D2�μ� ����)
-- ����� �Ǵ� �μ��ڵ�,�޿� ���,��� �� �� ���(������ �μ��ڵ�� �������� / ��ձ޿��� ����ó��)

SELECT
    dept_code �μ��ڵ�,
    TO_CHAR(
        floor(AVG(salary) ),
        'L999,999,999'
    ) ��ձ޿�,
    COUNT(*) �ο���
FROM
    employee
WHERE
    dept_code NOT IN 'D2'
GROUP BY
    dept_code
--having avg(salary) < 2500000
ORDER BY dept_code ASC;

-- �μ� ���� ������� 3�� �̸��� �μ��� �μ��ڵ� �� ������� �ο����� ���

SELECT
    nvl(dept_code,'intern') �μ��ڵ�,
    DECODE(
        substr(emp_no,8,1),
        1,
        '��',
        2,
        '��'
    ) ����,
    COUNT(*) �ο���
FROM
    employee
WHERE
    substr(emp_no,8,1) = 2
GROUP BY
    nvl(dept_code,'intern'),
    DECODE(
        substr(emp_no,8,1),
        1,
        '��',
        2,
        '��'
    )
HAVING
    COUNT(*) < 3
ORDER BY 1;

-- JOIN: �ϳ� �̻��� ���̺���� ���ڵ带 �����ؼ� �ǹ��ִ� �����͸� �����ϴ� ����
-- �ڹ��� ���� �ݺ����� ���� ��Ŀ����

-- cross join (��ȣ join) - join���� ���� ����� ���� ���������� ���� ���� ����

SELECT
    *
FROM
    location
    CROSS JOIN employee;

-- inner join

SELECT
    *
FROM
    employee;

SELECT
    *
FROM
    department;

SELECT
    emp_id,
    emp_name,
    dept_title
FROM
    employee
    INNER JOIN department ON dept_code = dept_id;

SELECT
    *
FROM
    job;

SELECT
    *
FROM
    employee
    INNER JOIN job ON job_code = job_code;

SELECT
    emp_name,
    job_name
FROM
    employee e
    INNER JOIN job j ON e.job_code = j.job_code;

-- inner join

SELECT
    e.emp_name,
    j.job_name
FROM
    employee e
    JOIN job j ON e.job_code = j.job_code;

--// employee���̺��� �� ��
--for(Employee e : employees) { 
--    // job���̺��� �� ��
--    for(Job j : jobs) { 
--        // ���õ� ����� job_code�� ���õ� job���� job_code�� ���ٸ� (on ���� ���ǹ��� �ش�)
--        if(e.job_code.equals(j.job_code)) {
--            sysout(e.emp_name + j.job_name);
--        }
--    }
--}
----------------------------------------------------------------------
-- �μ��� �ѹ��� �Ǵ� ȸ��������� �������� ������ �μ���,�μ��ڵ带 ���

SELECT
    *
FROM
    department;

SELECT
    emp_name �����,
    dept_title �μ���,
    dept_id �μ��ڵ�
FROM
    employee
    INNER JOIN department ON
        dept_code = dept_id
    AND (
            dept_title = '�ѹ���'
        OR
            dept_title = 'ȸ�������'
    );

-- ������ �븮�� ����� �����,�����ڵ�,���޸�,�μ��ڵ�,�޿��� ���
-- �޿� ������������ ���

SELECT
    emp_name �����,
    j.job_code �����ڵ�,
    j.job_name ���޸�,
    dept_code �μ��ڵ�,
    TO_CHAR(salary,'L999,999,999') �޿�
FROM
    employee e
    INNER JOIN job j ON
        e.job_code = j.job_code
    AND
        job_name = '�븮'
ORDER BY salary DESC;

-- outer join: inner join �� ���� ���ǿ� ���� join�� ���������� null ���� ���� ������ Ż���� �����ϴ� ����
-- null���� ������ �� �ִ� ���̺��� ������ �����ؾ��� e.g,) left outer join(����),right outer join(����),full outer join(�����)

SELECT
    emp_name,
    dept_code
FROM
    employee;

SELECT
    emp_id,
    emp_name,
    dept_title
FROM
    employee e
    FULL OUTER JOIN department d ON e.dept_code = d.dept_id;

-- self join: �ϳ��� ���̺��� �ڱ� �ڽŰ� join �ϴ� ���

SELECT
    e2.emp_name �����,
    e1.emp_name �Ŵ�����
FROM
    employee e1
    RIGHT OUTER JOIN employee e2 ON e1.emp_id = e2.manager_id;

-- ���� join: �� �̻��� ���̺��� join�� ����

SELECT
    emp_id,
    emp_name,
    dept_title,
    job_name
FROM
    employee
    LEFT JOIN department ON dept_code = dept_id
    JOIN job ON employee.job_code = job.job_code;

-- �����ȣ ����� National_code�� ���

SELECT
    *
FROM
    location;

SELECT
    *
FROM
    department;

SELECT
    *
FROM
    employee;

SELECT
    emp_id �����ȣ,
    emp_name �����,
    l.national_code �����ڵ�,
    national_name ������
FROM
    employee
    LEFT JOIN department ON dept_code = dept_id
    LEFT JOIN location l ON location_id = l.local_code
    LEFT JOIN national n ON n.national_code = l.national_code;
    
----------------------------------------------------------------
-- 1. 1998�� 4�� 13���� ���� ����?

SELECT
    TO_CHAR(
        TO_DATE('19980413','YYYY.MM.DD'),
        'DY'
    )
     || '����'
FROM
    dual;

-- 2. 1970��� ���̸鼭 ������ �����̰�,���� ������ �������� �����,�ֹι�ȣ,�μ���,���޸� ���

SELECT
    emp_name �����,
    emp_no �ֹι�ȣ,
    dept_title �μ���,
    job_name ���޸�
FROM
    employee e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN job j ON e.job_code = j.job_code
WHERE
        substr(emp_no,1,8) LIKE ( '7%2' )
    AND
        emp_name LIKE ( '��%' );

-- 3. ���ʽ� ����Ʈ�� ���� �ʴ� �������� �����,�μ���,�ٹ������� ( LOCAL NAME ) �� ���

SELECT
    e.emp_name �����,
    d.dept_title �μ���,
    l.local_name �ٹ�������
FROM
    employee e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN location l ON d.location_id = l.local_code
WHERE
    e.bonus IS NULL;

SELECT
    *
FROM
    location;

SELECT
    *
FROM
    national;

-- 4. �ѱ��� �Ϻ��� �ٹ��ϴ� �������� �����,�μ���,�ٹ��������� ��� 

SELECT
    e.emp_name �����,
    d.dept_title �μ���,
    n.national_name �ٹ�������
FROM
    employee e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN location l ON d.location_id = l.local_code
    LEFT JOIN national n ON l.national_code = n.national_code
WHERE
    n.national_name IN (
        '�ѱ�','�Ϻ�'
    );
    

-- 5. ���� �� �ְ� �޿��ڿ� �ּұ޿��� ������ ���
--60   1800000   8000000   6200000
--70   1550000   3400000   1850000
--80   2000000   3900000   1900000
--90   1380000   3760000   2380000

SELECT
        CASE
            WHEN substr(emp_no,1,1) = 6 THEN 60
            WHEN substr(emp_no,1,1) = 7 THEN 70
            WHEN substr(emp_no,1,1) = 8 THEN 80
            WHEN substr(emp_no,1,1) = 9 THEN 90
        END
    ����,
    TO_CHAR(MAX(salary),'L999,999,999') �ִ�޿���,
    TO_CHAR(MIN(salary),'L999,999,999') �ּұ޿���,
    TO_CHAR(
        MAX(salary) - MIN(salary),
        'L999,999,999'
    ) ����
FROM
    employee
GROUP BY
    CASE
        WHEN substr(emp_no,1,1) = 6 THEN 60
        WHEN substr(emp_no,1,1) = 7 THEN 70
        WHEN substr(emp_no,1,1) = 8 THEN 80
        WHEN substr(emp_no,1,1) = 9 THEN 90
    END
ORDER BY 1;

SELECT
    *
FROM
    employee;

-- 6. ���޸� �� �ְ� �޿��ڿ� �ּ� �޿��� ������ ���̰� 200���� �̻� ���̳��� ������ ���޸�� �ְ�޿�,�ּұ޿�,���̱ݾ��� ���
--�λ���   6000000   3700000   2300000

SELECT
    j.job_name ���޸�,
    TO_CHAR(MAX(salary),'L999,999,999') �ִ�޿���,
    TO_CHAR(MIN(salary),'L999,999,999') �ּұ޿���,
    TO_CHAR(
        MAX(salary) - MIN(salary),
        'L999,999,999'
    ) ����
FROM
    employee e
    LEFT JOIN job j ON e.job_code = j.job_code
GROUP BY
    j.job_name
HAVING
    MAX(salary) - MIN(salary) >= 2000000;

-- 7. ���޺� ���ʽ��� �޴� ���޸�� ������� ��ȸ�ϵ�,���ʽ��� �޴� ����� 2�� �̻��� ��츸 ���

SELECT
    j.job_name ���޸�,
    COUNT(*) || '��' "���ʽ��� �޴� �����"
FROM
    employee e
    JOIN job j ON
        e.job_code = j.job_code
    AND
        e.bonus IS NOT NULL
GROUP BY
    j.job_name
HAVING
    COUNT(*) >= 2;
-------------------------------------------------------------------------------------------------------------
-- set operator: UNION - �ϳ� �̻��� ���̺��� ���ļ� ���ο� �����͸� ����.

CREATE TABLE a (
    data   VARCHAR(10)
);

CREATE TABLE b (
    data   VARCHAR(10)
);

DROP TABLE a;

DROP TABLE b;

SELECT
    *
FROM
    a
ORDER BY 1;

SELECT
    *
FROM
    b
ORDER BY 1;

INSERT INTO a VALUES ( 'A' );

INSERT INTO a VALUES ( 'B' );

INSERT INTO a VALUES ( 'C' );

INSERT INTO b VALUES ( 'B' );

INSERT INTO b VALUES ( 'C' );

INSERT INTO b VALUES ( 'D' );                          

-- UNION: ������,�ߺ�����o

SELECT
    *
FROM
    a
UNION
SELECT
    *
FROM
    b;

-- UNION: ������,�ߺ�����x

SELECT
    *
FROM
    a
UNION ALL
SELECT
    *
FROM
    b;

-- INTERSECT: ������

SELECT
    *
FROM
    a
INTERSECT SELECT
    *
FROM
    b;

-- MINUS: ������

SELECT
    *
FROM
    a
MINUS
SELECT
    *
FROM
    b;
---------------------------------------------------------
 -- SubQuery
 -- ���� ������ �� �ٸ� �������� ����ϴ� ����
 -- �� ����(��������)�� ���������� �����ϴ� ������ ����

SELECT
    manager_id
FROM
    employee
WHERE
    emp_name = '���¿�';

SELECT
    emp_name
FROM
    employee
WHERE
    emp_id = 214;

SELECT
    emp_name
FROM
    employee
WHERE
    emp_id = (
        SELECT
            manager_id
        FROM
            employee
        WHERE
            emp_name = '���¿�'
    );

-- �������� ��ձ޿����� �޿��� ���� �޴� �������� ���,�̸�,�޿��� ���

SELECT
    emp_id,
    emp_name,
    salary
FROM
    employee
WHERE
    salary > (
        SELECT
            AVG(salary)
        FROM
            employee
    );

-- ������ ���Ͽ� ��������
-- ������ ���Ͽ� ��������
-- ������ ���߿� ��������
-- ������ ���߿� ��������
-- ��(ȣ��)�� ��������

-- ����߿� ������ ����� �޿��� ���� ������� ���,�����,�޿��� ��� (��,�����ش� ������� ����)

SELECT
    emp_id ���,
    emp_name �����,
    TO_CHAR(salary,'L999,999,999') �޿�
FROM
    employee
WHERE
        salary = (
            SELECT
                salary
            FROM
                employee
            WHERE
                emp_name = '������'
        )
    AND
        emp_name != '������';
        
-- �޿��� ���� ���� �����, ���� ���� ����� ���, �����, �޿��� ���
-- ��� ����� �⺻��
select max(salary), min(salary) from employee;
select
    emp_id,
    emp_name,
    salary
from employee
where salary in (select max(salary) from employee) or salary in (select min(salary) from employee);