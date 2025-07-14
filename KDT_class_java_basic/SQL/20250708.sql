-- select
-- from. where. group by,having,select,order by
-- length,instr,substr,round,floor,ceil
-- sysdate,add_months,months_between,next_day,last_day
-- to_date,to_char,sum,avg,max,min,count
-- nvl,decode,case where-then else end

-- group by: 데이터 그룹화
SELECT
    dept_code,
    AVG(salary)
FROM
    employee
GROUP BY
    dept_code
ORDER BY dept_code;

-- having: 순서상 group화된 다음 실행되는 조건

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

-- where문은 그룹화된 데이터를 못사용하지만 having문은 역으로 그룹화된 데이터만 사용가능하다.

-- 부서 별 인원수를 파악했을 때 3인 미만의 부서 코드 및 인원 수를 출력

SELECT
    dept_code 부서코드,
    COUNT(*) 인원수
FROM
    employee
GROUP BY
    dept_code
HAVING
    COUNT(*) < 3
ORDER BY 1;

-- 부서 별 평균급여가 250만원 미만인 부서는 연봉협상을 하려고 한다(D2부서 제외)
-- 대상이 되는 부서코드,급여 평균,사원 수 를 출력(정렬은 부서코드로 오름차순 / 평균급여는 정수처리)

SELECT
    dept_code 부서코드,
    TO_CHAR(
        floor(AVG(salary) ),
        'L999,999,999'
    ) 평균급여,
    COUNT(*) 인원수
FROM
    employee
WHERE
    dept_code NOT IN 'D2'
GROUP BY
    dept_code
--having avg(salary) < 2500000
ORDER BY dept_code ASC;

-- 부서 별로 여사원이 3명 미만인 부서의 부서코드 및 여사원의 인원수를 출력

SELECT
    nvl(dept_code,'intern') 부서코드,
    DECODE(
        substr(emp_no,8,1),
        1,
        '남',
        2,
        '여'
    ) 성별,
    COUNT(*) 인원수
FROM
    employee
WHERE
    substr(emp_no,8,1) = 2
GROUP BY
    nvl(dept_code,'intern'),
    DECODE(
        substr(emp_no,8,1),
        1,
        '남',
        2,
        '여'
    )
HAVING
    COUNT(*) < 3
ORDER BY 1;

-- JOIN: 하나 이상의 테이블들의 레코드를 조합해서 의미있는 데이터를 생성하는 문법
-- 자바의 다중 반복문과 같은 매커니즘

-- cross join (상호 join) - join문의 동작 방식을 가장 가시적으로 보기 좋은 문법

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

--// employee테이블의 각 행
--for(Employee e : employees) { 
--    // job테이블의 각 행
--    for(Job j : jobs) { 
--        // 선택된 사원의 job_code와 선택된 job행의 job_code가 같다면 (on 뒤의 조건문에 해당)
--        if(e.job_code.equals(j.job_code)) {
--            sysout(e.emp_name + j.job_name);
--        }
--    }
--}
----------------------------------------------------------------------
-- 부서가 총무부 또는 회계관리부인 직원들의 사원명과 부서명,부서코드를 출력

SELECT
    *
FROM
    department;

SELECT
    emp_name 사원명,
    dept_title 부서명,
    dept_id 부서코드
FROM
    employee
    INNER JOIN department ON
        dept_code = dept_id
    AND (
            dept_title = '총무부'
        OR
            dept_title = '회계관리부'
    );

-- 직급이 대리인 사원의 사원명,직급코드,직급명,부서코드,급여를 출력
-- 급여 내림차순으로 출력

SELECT
    emp_name 사원명,
    j.job_code 직급코드,
    j.job_name 직급명,
    dept_code 부서코드,
    TO_CHAR(salary,'L999,999,999') 급여
FROM
    employee e
    INNER JOIN job j ON
        e.job_code = j.job_code
    AND
        job_name = '대리'
ORDER BY salary DESC;

-- outer join: inner join 과 같이 조건에 의한 join을 수행하지만 null 값에 의한 데이터 탈락을 방지하는 문법
-- null값이 존재할 수 있는 테이블의 방향을 설정해야함 e.g,) left outer join(좌측),right outer join(우측),full outer join(양방향)

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

-- self join: 하나의 테이블이 자기 자신과 join 하는 기법

SELECT
    e2.emp_name 사원명,
    e1.emp_name 매니저명
FROM
    employee e1
    RIGHT OUTER JOIN employee e2 ON e1.emp_id = e2.manager_id;

-- 다중 join: 셋 이상의 테이블이 join에 참여

SELECT
    emp_id,
    emp_name,
    dept_title,
    job_name
FROM
    employee
    LEFT JOIN department ON dept_code = dept_id
    JOIN job ON employee.job_code = job.job_code;

-- 사원번호 사원명 National_code를 출력

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
    emp_id 사원번호,
    emp_name 사원명,
    l.national_code 국가코드,
    national_name 국가명
FROM
    employee
    LEFT JOIN department ON dept_code = dept_id
    LEFT JOIN location l ON location_id = l.local_code
    LEFT JOIN national n ON n.national_code = l.national_code;
    
----------------------------------------------------------------
-- 1. 1998년 4월 13일은 무슨 요일?

SELECT
    TO_CHAR(
        TO_DATE('19980413','YYYY.MM.DD'),
        'DY'
    )
     || '요일'
FROM
    dual;

-- 2. 1970년대 생이면서 성별이 여성이고,성이 전씨인 직원들의 사원명,주민번호,부서명,직급명 출력

SELECT
    emp_name 사원명,
    emp_no 주민번호,
    dept_title 부서명,
    job_name 직급명
FROM
    employee e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN job j ON e.job_code = j.job_code
WHERE
        substr(emp_no,1,8) LIKE ( '7%2' )
    AND
        emp_name LIKE ( '전%' );

-- 3. 보너스 포인트를 받지 않는 직원들의 사원명,부서명,근무지역명 ( LOCAL NAME ) 을 출력

SELECT
    e.emp_name 사원명,
    d.dept_title 부서명,
    l.local_name 근무지역명
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

-- 4. 한국과 일본에 근무하는 직원들의 사원명,부서명,근무국가명을 출력 

SELECT
    e.emp_name 사원명,
    d.dept_title 부서명,
    n.national_name 근무국가명
FROM
    employee e
    LEFT JOIN department d ON e.dept_code = d.dept_id
    LEFT JOIN location l ON d.location_id = l.local_code
    LEFT JOIN national n ON l.national_code = n.national_code
WHERE
    n.national_name IN (
        '한국','일본'
    );
    

-- 5. 년대생 별 최고 급여자와 최소급여자 정보를 출력
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
    년대생,
    TO_CHAR(MAX(salary),'L999,999,999') 최대급여자,
    TO_CHAR(MIN(salary),'L999,999,999') 최소급여자,
    TO_CHAR(
        MAX(salary) - MIN(salary),
        'L999,999,999'
    ) 차액
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

-- 6. 직급명 별 최고 급여자와 최소 급여자 사이의 차이가 200만원 이상 차이나는 직급의 직급명과 최고급여,최소급여,차이금액을 출력
--부사장   6000000   3700000   2300000

SELECT
    j.job_name 직급명,
    TO_CHAR(MAX(salary),'L999,999,999') 최대급여자,
    TO_CHAR(MIN(salary),'L999,999,999') 최소급여자,
    TO_CHAR(
        MAX(salary) - MIN(salary),
        'L999,999,999'
    ) 차액
FROM
    employee e
    LEFT JOIN job j ON e.job_code = j.job_code
GROUP BY
    j.job_name
HAVING
    MAX(salary) - MIN(salary) >= 2000000;

-- 7. 직급별 보너스를 받는 직급명과 사원수를 조회하되,보너스를 받는 사원이 2명 이상인 경우만 출력

SELECT
    j.job_name 직급명,
    COUNT(*) || '명' "보너스를 받는 사원수"
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
-- set operator: UNION - 하나 이상의 테이블을 합쳐서 새로운 데이터를 생성.

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

-- UNION: 합집합,중복제거o

SELECT
    *
FROM
    a
UNION
SELECT
    *
FROM
    b;

-- UNION: 합집합,중복제거x

SELECT
    *
FROM
    a
UNION ALL
SELECT
    *
FROM
    b;

-- INTERSECT: 교집합

SELECT
    *
FROM
    a
INTERSECT SELECT
    *
FROM
    b;

-- MINUS: 차집합

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
 -- 쿼리 내에서 또 다른 쿼리문을 사요하는 문법
 -- 주 쿼리(메인쿼리)가 서브쿼리를 포함하는 종속적 관계

SELECT
    manager_id
FROM
    employee
WHERE
    emp_name = '차태연';

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
            emp_name = '차태연'
    );

-- 전직원의 평균급여보다 급여를 많이 받는 직원들의 사번,이름,급여를 출력

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

-- 단일행 단일열 서브쿼리
-- 다중행 단일열 서브쿼리
-- 단일행 다중열 서브쿼리
-- 다중행 다중열 서브쿼리
-- 상(호연)관 서브쿼리

-- 사원중에 윤은해 사원과 급여가 같은 사원들의 사번,사원명,급여를 출력 (단,윤은해는 출력하지 않음)

SELECT
    emp_id 사번,
    emp_name 사원명,
    TO_CHAR(salary,'L999,999,999') 급여
FROM
    employee
WHERE
        salary = (
            SELECT
                salary
            FROM
                employee
            WHERE
                emp_name = '윤은해'
        )
    AND
        emp_name != '윤은해';
        
-- 급여가 제일 많은 사람과, 제일 적은 사람의 사번, 사원명, 급여를 출력
-- 사번 사원명 기본급
select max(salary), min(salary) from employee;
select
    emp_id,
    emp_name,
    salary
from employee
where salary in (select max(salary) from employee) or salary in (select min(salary) from employee);