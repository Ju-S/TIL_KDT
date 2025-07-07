select
    emp_name 사원명,
    salary * 12 연봉,
    '원' 단위
from employee;

-- 모든 직원의 이름, 연봉, 월실수령액을 출력하세요
-- 실 수령액은 (월급 - (월급의 3%))

SELECT
    EMP_NAME 이름,
    SALARY*12 연봉,
    SALARY - (SALARY*0.03) "월 실수령액"
FROM EMPLOYEE;

desc EMPLOYEE;
-- 테이블 속성 출력

-- 날짜 데이터에 대해서도 산술연산이 가능함
-- + 또는 - 만 가능
-- 더한 숫자만큼 일수로 더하거나 빼기
select HIRE_DATE, HIRE_DATE + 1 from EMPLOYEE;

-- 1개의 행을 가진 dummy열을 가진 dual테이블
select * from dual;
-- sysdate는 날짜
-- 날짜 - 날짜는 두 날의 차이 일수를 출력
select sysdate - hire_date from EMPLOYEE;

-- 오라클에서 '||' 연산자는 문자열 연결 기능을 수행한다.
select 
    EMP_NAME 사원명,
    floor((sysdate - hire_date)/ 365) || '년' 근속년수
from EMPLOYEE;

-- employee 테이블에서 20년 이상 근속한 근속자의 이름, 급여, 보너스율, 근속년수 출력
select
    emp_name 이름,
    salary || '원' 급여,
    nvl(bonus, 0.0) 보너스율,
    floor((sysdate - hire_date)/ 365) || '년' 근속년수
from employee
where floor((sysdate - hire_date)/ 365) >= 20;

select * from employee where bonus is null;
select * from employee where bonus is not null;

-- where (열) not in (조건)
-- 조건에 부합하지 않은 열만 출력

-- where, between and, and, or in, not in, is null,  is not null, alias - as, ||
-- like, not like

select * from employee
    where emp_name like '%이%';
-- '%': regex *과 같은 의미 뒤에 n개의 글자

select * from employee
    where emp_name like '전__';
-- '_': 언더바의 개수만큼 있는 것만 출력

------------

-- 이름이 연으로 끝나는 사원의 이름만 출력
select
    emp_name 사원명
from employee
where emp_name like '%연';

-- 날짜 데이터도 '>', '<', between and, '<=', '>=' 등의 연산자를 적용할 수 있음.
select * from employee where hire_date between '00/01/01'and '10/12/31';

-- 직원 중 메일주소에 's' 가 들어가면서, dept_code는 D9 or D6이고,
-- 고용일이 90/01/01부터 05/12/01사이 이면서, 급여가 270만원 이상인 직원의 전체 정보 출력

select * from employee
    where
        email like '%s%'
        and hire_date between '90/01/01' and '05/12/01'
        and dept_code in ('D9', 'D6')
        and salary >= 2700000;
    
-- order by (기준 열) (desc: descending, asc: ascending)
select * from employee order by salary desc;
select * from employee order by salary asc;

-- 열의 n번째로도 정렬 가능
select * from employee order by 7;

select * from employee order by dept_code desc, job_code asc, 9 desc;
------------------------------------------------
-- 문제 1.
-- 근속년수가 5년 이상, 10년 이하인 직원의 이름, 주민번호, 급여, 입사일을 검색
select
    emp_name 사원명,
    emp_no 주민번호,
    salary 급여,
    hire_date 입사일
from employee
where 5 <= ((sysdate - hire_date) / 365) and ((sysdate - hire_date) / 365) <= 10;

-- 문제 2.
-- 재직중이 아닌 직원의 이름, 부서코드를 검색(퇴사 여부: ENT_YN)
select
    emp_name 사원명,
    nvl(dept_code, '없음') 부서코드
from employee
where ENT_YN like 'Y';

-- 문제 3.
-- 근속년수가 10년 이상인 직원들을 검색
-- 출력 결과: 이름, 급여, 근속년수(소수점x)를 근속년수가 오름차순으로 정렬
-- 단, 급여는 50%인상된 급여로 출력
select
    emp_name 사원명,
    salary * 1.5 급여,
    floor((sysdate - hire_date) / 365) || '년' 근속년수
from employee
where floor((sysdate - hire_date) / 365) <= 10
order by floor((sysdate - hire_date) / 365) asc;

-- 문제 4.
-- 입사일이 99/01/01 ~ 10/01/01인 사람 중에서 급여가 2000000원 이하인 사람의
-- 이름, 주민번호, 이메일, 폰번호, 급여 검색 출력
select
    emp_name 이름,
    emp_no 주민번호,
    email 이메일,
    phone 전화번호,
    salary 급여
from employee
where
    hire_date between '99/01/01' and '10/01/01' and
    salary <= 2000000;

-- 문제 5.
-- 급여가 2000000원 ~ 3000000원인 여직원 중에서 4월 생일자를 검색하여 출력
-- 단, 이름 ,주민번호, 급여, 부서코드를 주민번호 순으로(내림차순) 출력하세요.
-- 단, 부서코드가 null인 사람은 부서코드가 '없음'으로 출력
select
    emp_name 이름,
    emp_no 주민번호,
    salary 급여,
    nvl(dept_code, '없음') 부서코드
from employee
where
    2000000 <= salary and salary <= 3000000 and
    emp_no like '__04__-2______'
order by emp_no desc;

-- 문제 6.
-- 남자 사원 중 보너스가 없는 사원의 오늘까지 근무일을 측정하여
-- 1000일마다 (소수점 제외)
-- 급여의 10% 보너스를 계산하여 이름, 특별 보너스(계산 금액) 결과를 출력
-- 단, 이름 순으로 오름 차순 정렬하여 출력
select
    emp_name 이름,
    salary * (floor((sysdate - hire_date) / 1000) * 0.1 + 1) 특별보너스
from employee
where
    bonus is null and
    emp_no like '______-1______'
order by emp_name;
