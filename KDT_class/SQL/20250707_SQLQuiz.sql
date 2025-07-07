-----Quiz
select * from employee;

-- 1.
select
    emp_name 사원명,
    email 이메일,
    length(email) "이메일 길이"
from employee;

-- 2.
select
    emp_name 사원명,
    substr(email, 1, instr(email, '@') - 1) 아이디
from employee;

-- 3.
select
    emp_name 사원명,
    substr(emp_no, 1, 2) 년생,
    nvl(bonus * 100, 0) || '%' "보너스 율"
from employee
where substr(emp_no, 1, 1) = 6;

-- 4.
select count(*) || '명' from employee where substr(phone, 1, 3) != '010';

-- 5.
select
    emp_name 사원명,
    to_char(hire_date, 'yyyy"년"MM"월"') 입사년월
from employee;

-- 6.
select
    emp_name 사원명,
    substr(emp_no, 1, 8) || '******' 주민번호
from employee;

-- 7.
select
    emp_name 사원명,
    job_code 직급코드,
    case
        when bonus is null then to_char(salary * 12, 'L999,999,999')
        else to_char(salary * (bonus + 1) * 12, 'L999,999,999')
    end 연봉
from employee;

-- 8.
select
    emp_id 사번,
    emp_name 사원명,
    dept_code 부서코드,
    hire_date 입사일
from employee
where dept_code in ('D5', 'D9') and extract(year from hire_date) = 2004;

-- 9.
select
    emp_name 사원명,
    hire_date 입사일,
    floor(sysdate - hire_date) "근무일수(주말포함)"
from employee;

-- 10.
select
    emp_name 사원명,
    dept_code 부서코드,
    case
        when dept_code in ('D5', 'D6', 'D9') then '야근'
        else '야근없음'
    end 야근유무
from employee
order by dept_code asc;

-- 11.
select
    nvl(dept_code, '인턴') 부서코드,
    emp_name 사원명,
    case
        when dept_code in ('D5') then '10%'
        when dept_code in ('D2') then '20%'
        when dept_code in ('D9') then '30%'
        else '없음'
    end 보너스율,
    case
        when dept_code in ('D5') then salary * 0.1
        when dept_code in ('D2') then salary * 0.2
        when dept_code in ('D9') then salary * 0.3
        else 0
    end "특별 보너스액"
from employee;