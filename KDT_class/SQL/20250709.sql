-- group by / having / join / union

-- subquery
-- select * from employee where salary > (select avg(salary) from employee);

-- �޿��� ���� ���� �޴� ������ �̸��� �޿�,���� ���� �޴� ������ �̸��� �޿��� ���
SELECT
    emp_name,
    salary
FROM
    employee
WHERE
        salary = (
            SELECT
                MAX(salary)
            FROM
                employee
        )
    OR
        salary = (
            SELECT
                MIN(salary)
            FROM
                employee
        );
    
-- D1,D2 �μ����� �ٹ��ϴ� ��� �ֿ� �⺻�޿��� D5�μ��� �������� ��ձ޿����� ���� ������� �μ���ȣ,�μ���,���,�����,�޿�    

SELECT
    dept_code �μ���ȣ,
    dept_title �μ���,
    emp_id ���,
    emp_name �����,
    TO_CHAR(salary,'L999,999,999') �޿�
FROM
    employee
    JOIN department ON dept_code = dept_id
WHERE
        dept_code IN (
            'D1','D2'
        )
    AND
        salary > (
            SELECT
                AVG(salary)
            FROM
                employee
            WHERE
                dept_code IN (
                    'D5'
                )
        );

-- ������ ��������

SELECT
    dept_code
FROM
    employee
WHERE
    emp_name IN (
        '������','�ڳ���'
    );

SELECT
    dept_code,
    emp_name
FROM
    employee
WHERE
    dept_code IN (
        SELECT
            dept_code
        FROM
            employee
        WHERE
            emp_name IN (
                '������','�ڳ���'
            )
    );
    
-- ���¿�,������ ����� �޿���ް� ���� ����� ���޸�� ������� ���

SELECT
    job_name,
    emp_name
FROM
    employee e
    JOIN job j ON e.job_code = j.job_code
WHERE
    sal_level IN (
        SELECT
            sal_level
        FROM
            employee
        WHERE
            emp_name IN (
                '���¿�','������'
            )
    );

-- ���޸��� ��ǥ,�λ����� �ƴ� ��� ������ �����,�����ڵ� ���
--select
--    emp_name,
--    e.job_code
--from
--    employee e join job j on e.job_code = j.job_code
--where
--    j.job_name not in ('��ǥ','�λ���');

SELECT
    emp_name,
    job_code
FROM
    employee
WHERE
    job_code IN (
        SELECT
            job_code
        FROM
            job
        WHERE
            job_name NOT IN (
                '��ǥ','�λ���'
            )
    );

-- ���߿� ��������

SELECT
    *
FROM
    employee;

SELECT
    dept_code,
    job_code
FROM
    employee
WHERE
    emp_name = '���¸�';

SELECT
    emp_name,
    dept_code,
    job_code
FROM
    employee
WHERE
    ( dept_code,job_code ) = (
        SELECT
            dept_code,
            job_code
        FROM
            employee
        WHERE
            emp_name = '���¸�'
    );
    
-- ������ 8�� 8�� �� ������ ���� �μ��ڵ�,�����ڵ带 ���� ������� �����,����(mmdd),�μ��ڵ�,�μ����� ���

SELECT
    emp_name �����,
    TO_CHAR(
        TO_DATE(
            substr(emp_no,3,4),
            'MMDD'
        ),
        'MM"��"DD"��"'
    ) ����,
    dept_code �μ��ڵ�,
    dept_title �μ���
FROM
    employee
    LEFT JOIN department ON dept_code = dept_id
WHERE
        ( dept_code,job_code ) IN (
            SELECT
                dept_code,
                job_code
            FROM
                employee
            WHERE
                substr(emp_no,3,4) = '0808'
        )
    AND
        substr(emp_no,3,4) != '0808';
    
-- �� ���޺� �޿��� ���� ���� ������ �̸�,�޿�

SELECT
    emp_name �����,
    e.job_code �����ڵ�,
    job_name ���޸�,
    TO_CHAR(salary,'L999,999,999') �޿�
FROM
    employee e
    JOIN job j ON e.job_code = j.job_code
WHERE
    ( e.job_code,salary ) IN (
        SELECT
            job_code,
            MAX(salary)
        FROM
            employee
        GROUP BY
            job_code
    )
ORDER BY e.job_code;

-- inline view : subquery�� from������ ����ϴ� ���

SELECT
    *
FROM
    (
        SELECT
            emp_name
        FROM
            employee
    );

-- ��� ���� ����: main ������ �÷��� subquery�� ������ �ִ� ������ ��������

SELECT
    emp_name,
    (
        SELECT
            dept_title
        FROM
            department
        WHERE
            dept_id = dept_code
    )
FROM
    employee;

------------------------------------------------------------------------------------------------------------------

-- ��ŷ �Լ� 3����
-- rank(),dense_rank(),row_number() over(order by 'column name')

SELECT
    emp_name,
    salary,
    DENSE_RANK() OVER(
        ORDER BY salary DESC
    )
FROM
    employee;

-- �޿� ���� ������ �������� ��,5 - 10������ ���

SELECT
    *
FROM
    (
        SELECT
            emp_id,
            emp_name,
            salary,
            RANK() OVER(
                ORDER BY salary DESC
            ) rank
        FROM
            employee
    )
WHERE
        5 <= rank
    AND
        rank <= 10;

-- 1. �� ���޸� �� ���� ���� �ٹ��� ������ �̸��� �Ի����� ���
-- ����. ��¥�� Max/Min���� ����

SELECT
    emp_name �����,
    job_name ���޸�,
    hire_date �Ի���
FROM
    employee e
    JOIN job j ON e.job_code = j.job_code
WHERE
    ( e.job_code,hire_date ) IN (
        SELECT
            job_code,
            MIN(hire_date)
        FROM
            employee
        GROUP BY
            job_code
    )
ORDER BY e.job_code;

-- 2. �ٹ�����(�����̸�) �� ���� ���� �޿��� �޴� ������ ������,�����,�޿� ���

SELECT
    nvl(n.national_name,'�̻�') ������,
    e.emp_name �����,
    TO_CHAR(e.salary,'L999,999,999') �޿�
FROM
    employee e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN location l ON d.location_id = l.local_code
    LEFT JOIN national n ON l.national_code = n.national_code
WHERE
    ( nvl(n.national_code,0),e.salary ) IN (
        SELECT
            nvl(n2.national_code,0),
            MIN(e2.salary)
        FROM
            employee e2
            LEFT JOIN department d2 ON e2.dept_code = d2.dept_id
            LEFT JOIN location l2 ON d2.location_id = l2.local_code
            LEFT JOIN national n2 ON l2.national_code = n2.national_code
        GROUP BY
            nvl(n2.national_code,0)
    )
ORDER BY salary;

-- 3. �ڽ��� ���� �μ��� ��� �޿����� �� ���� �޴� ������ �̸��� �޿�,�μ���,�μ��޿� ����� ���

SELECT
    emp_name �����,
    TO_CHAR(salary,'L999,999,999') �޿�,
    nvl(dept_title,'intern') �μ���,
    TO_CHAR(dept_avg,'L999,999,999') "�μ��޿� ���"
FROM
    employee e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    JOIN (
        SELECT
            nvl(dept_code,'intern') dc,
            AVG(salary) dept_avg
        FROM
            employee
        GROUP BY
            nvl(dept_code,'intern')
    ) ON
        e.salary > dept_avg
    AND
        nvl(e.dept_code,'intern') = dc
ORDER BY salary DESC;

SELECT
    emp_name �����,
    TO_CHAR(salary,'L999,999,999') �޿�,
    dept_title �μ���,
    TO_CHAR(dept_avg,'L999,999,999') "�μ��޿� ���"
FROM
    employee e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN (
        SELECT
            dept_code dc,
            AVG(salary) dept_avg
        FROM
            employee
        GROUP BY
            dept_code
    ) ON
        e.salary > dept_avg
    AND
        e.dept_code = dc
WHERE
    dept_avg IS NOT NULL
ORDER BY salary DESC;

SELECT
    emp_name,
    dept_code,
    salary
FROM
    employee;
    
--3������ ���

SELECT
    emp_name,
    nvl(dept_code,'intern') �μ��ڵ�,
    dept_title �μ���,
    salary,
    floor( (
        SELECT
            AVG(salary)
        FROM
            employee
        WHERE
            dept_code = e.dept_code
        GROUP BY
            nvl(dept_code,'intern')
    ) ) "�μ���ձ޿�"
FROM
    employee e
    JOIN department ON dept_code = dept_id
WHERE
    salary > (
        SELECT
            AVG(salary)
        FROM
            employee
        WHERE
            dept_code = e.dept_code
    )
ORDER BY 2,3 DESC;

-- 4. �μ��� ���� �޿��� ���� �޴� ������ ���� ���� �޴� ������ �̸�,�޿�,�μ����� ���

SELECT
    emp_name �����,
    salary �޿�,
    nvl(dept_title, '�̹���') �μ���
FROM
    (
        SELECT
            emp_name,
            nvl(dept_code, 'intern'),
            salary
        FROM
            employee
        WHERE
                ( nvl(dept_code, 'intern'),salary ) IN (
                    SELECT
                        nvl(dept_code, 'intern'),
                        MIN(salary)
                    FROM
                        employee
                    GROUP BY
                        nvl(dept_code, 'intern')
                )
            OR
                ( nvl(dept_code, 'intern'),salary ) IN (
                    SELECT
                        nvl(dept_code, 'intern'),
                        MAX(salary)
                    FROM
                        employee
                    GROUP BY
                        nvl(dept_code, 'intern')
                )
    )
    JOIN department ON dept_code = dept_id
ORDER BY nvl(dept_code, 'intern') ASC, salary DESC;

--4�� ���
SELECT
    nvl(dept_code,'intern') �μ��ڵ�,
    nvl(dept_title,'�̹���') �μ���,
    emp_name �����,
    salary �޿�
FROM
    employee
    LEFT JOIN department ON dept_code = dept_id
WHERE
        ( nvl(dept_code,'intern'),salary ) IN (
            SELECT
                nvl(dept_code,'intern'),
                MAX(salary)
            FROM
                employee
            GROUP BY
                nvl(dept_code,'intern')
        )
    OR
        ( nvl(dept_code,'intern'),salary ) IN (
            SELECT
                nvl(dept_code,'intern'),
                MIN(salary)
            FROM
                employee
            GROUP BY
                nvl(dept_code,'intern')
        )
ORDER BY 1,4 DESC;