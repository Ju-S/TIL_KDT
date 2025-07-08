----------------------------------------------------------------
-- 1. 1998년 4월 13일은 무슨 요일?
select to_char(to_date('19980413', 'YYYY.MM.DD'), 'DY') || '요일' from dual;

-- 2. 1970년대 생이면서 성별이 여성이고, 성이 전씨인 직원들의 사원명, 주민번호, 부서명, 직급명 출력
select
    emp_name 사원명,
    emp_no 주민번호,
    dept_title 부서명,
    job_name 직급명
from employee e
    left join department d on e.dept_code = d.dept_id
    left join job j on e.job_code = j.job_code
where
    substr(emp_no, 1, 8) like ('7%2');

-- 3. 보너스 포인트를 받지 않는 직원들의 사원명, 부서명, 근무지역명 ( LOCAL NAME ) 을 출력
select
    e.emp_name 사원명,
    d.dept_title 부서명,
    l.local_name 근무지역명
from employee e
    left join department d on e.dept_code = d.dept_id
    left join location l on d.location_id = l.local_code
where
    e.bonus is null;
    
select * from location;
select * from national;

-- 4. 한국과 일본에 근무하는 직원들의 사원명, 부서명, 근무국가명을 출력 
select
    e.emp_name 사원명,
    d.dept_title 부서명,
    n.national_name 근무국가명
from employee e
    left join department d on e.dept_code = d.dept_id
    left join location l on d.location_id = l.local_code
    left join national n on l.national_code = n.national_code
where
    n.national_name in ('한국', '일본');
    

-- 5. 년대생 별 최고 급여자와 최소급여자 정보를 출력
--60   1800000   8000000   6200000
--70   1550000   3400000   1850000
--80   2000000   3900000   1900000
--90   1380000   3760000   2380000
select
    case
        when substr(emp_no, 1, 1) = 6 then 60
        when substr(emp_no, 1, 1) = 7 then 70
        when substr(emp_no, 1, 1) = 8 then 80
        when substr(emp_no, 1, 1) = 9 then 90
    end 년대생,
    to_char(max(salary), 'L999,999,999') 최대급여자,
    to_char(min(salary), 'L999,999,999') 최소급여자,
    to_char(max(salary) - min(salary), 'L999,999,999') 차액
from
    employee
group by
    case
        when substr(emp_no, 1, 1) = 6 then 60
        when substr(emp_no, 1, 1) = 7 then 70
        when substr(emp_no, 1, 1) = 8 then 80
        when substr(emp_no, 1, 1) = 9 then 90
    end
order by 1;
select * from employee;

-- 6. 직급명 별 최고 급여자와 최소 급여자 사이의 차이가 200만원 이상 차이나는 직급의 직급명과 최고급여,최소급여, 차이금액을 출력
--부사장   6000000   3700000   2300000
select
    j.job_name 직급명,
    to_char(max(salary), 'L999,999,999') 최대급여자,
    to_char(min(salary), 'L999,999,999') 최소급여자,
    to_char(max(salary) - min(salary), 'L999,999,999') 차액
from
    employee e
    left join job j on e.job_code = j.job_code
group by j.job_name
having max(salary) - min(salary) >= 2000000;

-- 7. 직급별 보너스를 받는 직급명과 사원수를 조회하되, 보너스를 받는 사원이 2명 이상인 경우만 출력
select
    j.job_name 직급명,
    count(*) || '명' "보너스를 받는 사원수"
from
    employee e
    join job j on e.job_code = j.job_code and e.bonus is not null
group by
    j.job_name
having
    count(*) >= 2;