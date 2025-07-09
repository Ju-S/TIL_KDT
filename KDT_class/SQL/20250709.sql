-- group by / having / join / union

-- subquery
-- select * from employee where salary > (select avg(salary) from employee);

-- 급여를 제일 많이 받는 직원의 이름과 급여, 제일 적게 받는 지원의 이름과 급여를 출력
select
    emp_name,
    salary
from
    employee
where
    salary = (select max(salary) from employee) or
    salary = (select min(salary) from employee);
    
-- D1, D2 부서에서 근무하는 사원 주에 기본급여가 D5부서의 직원들의 평균급여보다 많은 사원들의 부서번호, 부서명, 사번, 사원명, 급여    
select
    dept_code 부서번호,
    dept_title 부서명,
    emp_id 사번,
    emp_name 사원명,
    to_char(salary, 'L999,999,999') 급여
from
    employee join department on dept_code = dept_id
where
    dept_code in ('D1', 'D2') and
    salary > (select avg(salary) from employee where dept_code in ('D5'));

-- 다중행 서브쿼리
select dept_code from employee where emp_name in ('송종기', '박나라');

select
    dept_code,
    emp_name
from
    employee
where
    dept_code in (select dept_code from employee where emp_name in ('송종기', '박나라'));
    
-- 차태연, 전지연 사원의 급여등급과 같은 사원의 직급명과 사원명을 출력
select
    job_name,
    emp_name
from
    employee e join job j on e.job_code = j.job_code
where
    sal_level in (select sal_level from employee where emp_name in ('차태연', '전지연'));

-- 직급명이 대표, 부사장이 아닌 모든 직원의 사원명, 직급코드 출력
--select
--    emp_name,
--    e.job_code
--from
--    employee e join job j on e.job_code = j.job_code
--where
--    j.job_name not in ('대표', '부사장');

select
    emp_name,
    job_code
from
    employee
where
    job_code in (select job_code from job where job_name not in ('대표', '부사장'));

-- 다중열 서브쿼리
select * from employee;
select dept_code, job_code from employee where emp_name = '이태림';

select
    emp_name,
    dept_code,
    job_code
from
    employee
where
    (dept_code, job_code) = (select dept_code, job_code from employee where emp_name = '이태림');
    
-- 생일이 8월 8일 인 사원들과 같은 부서코드, 직급코드를 가진 사원들의 사원명, 생일(mmdd), 부서코드, 부서명을 출력
select
    emp_name 사원명,
    to_char(to_date(substr(emp_no, 3, 4), 'MMDD'), 'MM"월"DD"일"') 생일,
    dept_code 부서코드,
    dept_title 부서명
from
    employee left join department on dept_code = dept_id
where
    (dept_code, job_code) in (select dept_code, job_code from employee where substr(emp_no, 3, 4) = '0808') and substr(emp_no, 3, 4) != '0808';
    
-- 각 직급별 급여가 가장 높은 직원의 이름, 급여
select
    emp_name 사원명,
    e.job_code 직급코드,
    job_name 직급명,
    to_char(salary, 'L999,999,999') 급여
from
    employee e join job j on e.job_code = j.job_code
where
    (e.job_code, salary) in (select job_code, max(salary) from employee group by job_code)
order by e.job_code;

-- inline view : subquery를 from절에서 사용하는 경우
select * from (select emp_name from employee);

-- 상관 서브 쿼리: main 쿼리의 컬럼이 subquery에 영향을 주는 형태의 서브쿼리
select emp_name, (select dept_title from department where dept_id = dept_code) from employee;

------------------------------------------------------------------------------------------------------------------

-- 랭킹 함수 3가지
-- rank(), dense_rank(), row_number() over(order by 'column name')
select
    emp_name,
    salary,
    dense_rank() over(order by salary desc)
from employee;

-- 급여 기준 순위를 적용했을 때, 5 - 10위까지 출력
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

-- 1. 각 직급명 별 가장 오래 근무한 직원의 이름과 입사일을 출력
-- 참고. 날짜도 Max/Min적용 가능
select
    emp_name 사원명,
    job_name 직급명,
    hire_date 입사일
from
    employee e join job j on e.job_code = j.job_code
where
    (e.job_code, hire_date) in (select job_code, min(hire_date) from employee group by job_code)
order by e.job_code;

-- 2. 근무국가(국가이름) 별 가장 낮은 급여를 받는 직원의 국가명, 사원명, 급여 출력
select
    nvl(n.national_name, '미상') 국가명,
    e.emp_name 사원명,
    to_char(e.salary, 'L999,999,999') 급여
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