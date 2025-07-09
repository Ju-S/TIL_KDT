-- group by / having / join / union

-- subquery
-- select * from employee where salary > (select avg(salary) from employee);

-- 급여를 제일 많이 받는 직원의 이름과 급여,제일 적게 받는 지원의 이름과 급여를 출력
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
    
-- D1,D2 부서에서 근무하는 사원 주에 기본급여가 D5부서의 직원들의 평균급여보다 많은 사원들의 부서번호,부서명,사번,사원명,급여    

SELECT
    dept_code 부서번호,
    dept_title 부서명,
    emp_id 사번,
    emp_name 사원명,
    TO_CHAR(salary,'L999,999,999') 급여
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

-- 다중행 서브쿼리

SELECT
    dept_code
FROM
    employee
WHERE
    emp_name IN (
        '송종기','박나라'
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
                '송종기','박나라'
            )
    );
    
-- 차태연,전지연 사원의 급여등급과 같은 사원의 직급명과 사원명을 출력

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
                '차태연','전지연'
            )
    );

-- 직급명이 대표,부사장이 아닌 모든 직원의 사원명,직급코드 출력
--select
--    emp_name,
--    e.job_code
--from
--    employee e join job j on e.job_code = j.job_code
--where
--    j.job_name not in ('대표','부사장');

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
                '대표','부사장'
            )
    );

-- 다중열 서브쿼리

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
    emp_name = '이태림';

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
            emp_name = '이태림'
    );
    
-- 생일이 8월 8일 인 사원들과 같은 부서코드,직급코드를 가진 사원들의 사원명,생일(mmdd),부서코드,부서명을 출력

SELECT
    emp_name 사원명,
    TO_CHAR(
        TO_DATE(
            substr(emp_no,3,4),
            'MMDD'
        ),
        'MM"월"DD"일"'
    ) 생일,
    dept_code 부서코드,
    dept_title 부서명
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
    
-- 각 직급별 급여가 가장 높은 직원의 이름,급여

SELECT
    emp_name 사원명,
    e.job_code 직급코드,
    job_name 직급명,
    TO_CHAR(salary,'L999,999,999') 급여
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

-- inline view : subquery를 from절에서 사용하는 경우

SELECT
    *
FROM
    (
        SELECT
            emp_name
        FROM
            employee
    );

-- 상관 서브 쿼리: main 쿼리의 컬럼이 subquery에 영향을 주는 형태의 서브쿼리

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

-- 랭킹 함수 3가지
-- rank(),dense_rank(),row_number() over(order by 'column name')

SELECT
    emp_name,
    salary,
    DENSE_RANK() OVER(
        ORDER BY salary DESC
    )
FROM
    employee;

-- 급여 기준 순위를 적용했을 때,5 - 10위까지 출력

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

-- 1. 각 직급명 별 가장 오래 근무한 직원의 이름과 입사일을 출력
-- 참고. 날짜도 Max/Min적용 가능

SELECT
    emp_name 사원명,
    job_name 직급명,
    hire_date 입사일
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

-- 2. 근무국가(국가이름) 별 가장 낮은 급여를 받는 직원의 국가명,사원명,급여 출력

SELECT
    nvl(n.national_name,'미상') 국가명,
    e.emp_name 사원명,
    TO_CHAR(e.salary,'L999,999,999') 급여
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

-- 3. 자신이 속한 부서의 평균 급여보다 더 많이 받는 직원의 이름과 급여,부서명,부서급여 평균을 출력

SELECT
    emp_name 사원명,
    TO_CHAR(salary,'L999,999,999') 급여,
    nvl(dept_title,'intern') 부서명,
    TO_CHAR(dept_avg,'L999,999,999') "부서급여 평균"
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
    emp_name 사원명,
    TO_CHAR(salary,'L999,999,999') 급여,
    dept_title 부서명,
    TO_CHAR(dept_avg,'L999,999,999') "부서급여 평균"
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
    
--3번문제 답안

SELECT
    emp_name,
    nvl(dept_code,'intern') 부서코드,
    dept_title 부서명,
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
    ) ) "부서평균급여"
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

-- 4. 부서별 가장 급여를 많이 받는 직원과 가장 적게 받는 직원의 이름,급여,부서명을 출력

SELECT
    emp_name 사원명,
    salary 급여,
    nvl(dept_title, '미배정') 부서명
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

--4번 답안
SELECT
    nvl(dept_code,'intern') 부서코드,
    nvl(dept_title,'미배정') 부서명,
    emp_name 사원명,
    salary 급여
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