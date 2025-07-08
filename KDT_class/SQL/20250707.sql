-- 2025/07/07

-- function - 오라클 내장 메서드

-- length / lengthb
-- length 
select length ('Hello') from dual; I
select length ('한글') from dual; I
--  lengthb (byte)
select lengthb ('Hello') from dual; I
select lengthb ('한글') from dual; I 

-- instr : 찾는 문자가 문자열 내에서 위치하는 인덱스 값을 변환
-- 자바의 index of 와 유사한 기능
select instr('Hello World Hi High','H', 1, 1)  from dual;

-- substr : 문자열 내에서 필요한 만큼의 문자열을 추출하여 반환
select substr('Hello World Hi High',1,2)  from dual;  --  출력 값 :  He
select substr('Hello World Hi High',7,5)  from dual;  --  출력 값 : World

-- Quiz  : employee 테이블에서 남자 사원들의 사원번호, 사원명 , 주민번호 , 연봉을 출력 하세요. ( 단 , like 구문은 사용하지 마세요 )
select *  from employee;

select
emp_name 사원명,
emp_id 사원번호,
emp_no 주민번호,
salary*12 연봉
from
employee
where
substr(emp_no ,8,1) = 1;

-- floor : 소수점 버림 함수
-- round : 숫자 값 반올림 함수 
select round (123.456,3) from dual;
select round (123.456,2) from dual;
select round (123.454,2) from dual;
-- select round (123.소수점 아래로 첫 번째 두번 째 세번 째 , 몇 번째 소수점에서 반올림 할거냐) from dual;
select round (123.454,0) from dual;
select round (126.454,-1) from dual;
--  (123.454,0) = (-1 , 1, 0 . 1,2,3) → 자리 : 소수점 말고도 정수도 반올림 가능

-- ceil : 올림 함수
select ceil (123.456) from dual;

-- sysdate :현재 시간을 얻어 올 수 있는 내장 필드 ( System. curr)
-- 현재 시간을 담고 있는 변수 = 함수처럼 쓸 수 있음
select sysdate from dual;

-- months_between : 두 날짜를 인자로 받아, 두 날짜 사이의 개월 수 차이
select emp_name , floor (months_between(sysdate , hire_date)) from employee;

-- add_months : 특정 날짜로부터 n개월 후의 날짜를 반환
select add_months (sysdate,6)  from dual; -- 6개월 후에 날짜를 알아보기

-- next_day : 첫 번째 파라미터로 받은 날짜로부터, 두 번째 파라미터로 받은 요일에 해당하는 가장 가까운 날짜 값 반환
select next_day(sysdate , '토') from dual; -- 오늘로부터 가장 가까운 토요일 알아보기

-- last_day : 첫 번째 인자로 전달된 날짜가 속한 달의 마지막 날을 반환
select last_day(sysdate) from dual; 
-- Quiz : 다음 달 마지막 날짜는 ? 
select last_day(add_months(sysdate,1)) from dual;

-- extract : 날짜로 부터 , 년/월/일 중 선택하여 반환
select extract(year from sysdate ) from dual; -- 년도만
select extract(month from sysdate ) from dual; -- 월만
select extract(day from sysdate ) from dual; -- 일만 

-- Quiz : 사원의 이름, 입사일 , 연차를 출력 하세요.  (단, 입사일 출력은 YYYY년 M월D일 형태로 출력 하세요.)
select *  from employee;

select 
emp_name 사원명,
extract (year  from hire_date) || '년' ||
extract (month  from hire_date) || '월' ||
extract (day  from hire_date) || '일' || 입사일,
ceil((sysdate-hire_date)/365) 년차
from
empolyee
order by 3;

-- 형변환 함수
-- to_char : 날짜 또는 숫자를 문자열로 변환하는 함수
-- 자바의 SimpleDateFoemat 이 날짜 → 문자열
-- 자바의 String.valueOf 기타 Type → 문자열
select to_char (sysdate, 'YYYY-MM-DD') from dual;
select to_char (sysdate, 'YYYY/MM/DD') from dual;

select to_char (sysdate, 'YYYY년MM월DD일') from dual; -- 오류남. 년,월.일 구분자로 to_char가 인지 하지 못함. 
select to_char (sysdate, 'YYYY"년"MM"월"DD"일"') from dual; -- "" 를 넣어서 출력하게끔 해줘야 사용 가능

select to_char (sysdate, 'HH:MI:SS')from dual; -- 현재 시간 출력
select to_char (sysdate, 'HH"시"MI"분"SS"초"')from dual; 

select to_char (sysdate, 'day') from dual; -- 현재 요일 출력 (월요일)
select to_char (sysdate, 'dy') from dual; -- (월)

-- Quiz : 사원명, 고용일일 출력 하세요. ( 고용일 출력 방식 : 1990/02/06 화 ) 
select
emp_name 사원명,
to_char(hire_date, 'YYYY/MM/DD(DY)')
from
employee;

 -- 숫자도 원하는 형태로 만들어 줄 수 있음, 실제 숫자보다 출력 형식이 더 길어야 함, 짧으면 오류
select to_char(1000000, 'L9,999,999') from dual;
-- 앞에 L 을 붙여 주면 해당 국가의 통화가 붙음
select
emp_name 사원명,
to_char(hire_date, 'YYYY/MM/DD(DY)') 고용일,
ltrim(to_char(salary, 'L999,999,999') ) 급여 -- ltrim()  컬럼 사이에 공백을 비워주는 함수
from
employee;

-- to_date : 문자열 → 날짜
-- 자바에서 SimpleDateFormat 의 parse기능
select to_date (20000101,'yyyymmdd') from dual;

--Quiz 1 : 08152002 을 2002년08월15일로 출력해 보세요
select to_char(to_date ('08152002', 'mmddyyyy'),'yyyy"년"mm"월"dd"일"') from dual;
-- 앞 숫자가 0이라서 ''  묶어줘야함
-- to_char 로 "년""월""일" 따로  묶어주기

-- Quiz 2 : 올해 크리스마스는 무슨 요일 일까요?
select to_char(to_date(20251225, 'yyyymmdd'),'yyyy"년"mm"월"dd"일"day') from dual;

-- decode : 선택 함수
-- 자바로 치면 삼항 연산자 또는 swich문에 해당

-- 남자 성별을 출력 해보자
select 
    emp_id,
    emp_name,
    decode(substr(emp_no, 8, 1), 1, '남', 2, '여', '외계인') -- 첫 번째 파라미터와 2 번째 파라미터가 같다면 3 번째 파라미터를 출력 해라 and...
from employee;                                                               -- 2개씩 짝만 지으면 비교식은 계속 넣을 수 있음.  = 가변 인자 함수
                                                                                                -- 짝이 지어지지 않은  ' ' 은 else 느낌
                                                                                                --  swich문이랑 비슷  < > 비교식 못함 = 만 가능
                                                
-- case : 선택 함수
-- 자바로 치면 if문에 해당 : 비교식 가능

select
    emp_id,
    emp_name,
    case
        when substr(emp_no, 8, 1) in (1, 3) then '남'
        when substr(emp_no, 8, 1) in (2, 4) then '여'
        else 'none'
    end 성별
from employee;

-- 직원중 60년대 새을에 대해서만
-- 65년 보다 작으면 60년생 초반
-- 65년 보다 크거나 같으면 60년생 후반 으로 출력
-- 이름과 초반인지 후반인지만 출력

select * from employee;

select
    emp_name,
    case
        when substr(emp_no, 1, 2) < 65 then '60년생 초반'
        when substr(emp_no, 1, 2) >= 65 then '60년생 후반'
    end 초반후반
from employee
where substr(emp_no, 1, 1) = 6;

select nvl(bonus, 0) from employee;

-----------------------------------------------------↑↑↑단일 행 함수↑↑↑
-----------------------------------------------------↓↓↓그룹 함수↓↓↓
-- 그룹화된 데이터 혹은 그룹함수끼리만 출력 가능
-- emp_name과 같은 단일 컬럼은 출력 불가

-- sum : 합계 함수
select sum(salary) from employee;

-- avg: 평균 함수
select to_char(avg(salary), 'L999,999,999') from employee;

-- max: 최대값 함수
select max(salary) from employee;

-- min: 최소값 함수
select min(salary) from employee;

-- count: 개수 카운팅 함수
select count(*) from employee;

select sum(salary), avg(salary), max(salary), min(salary), count(*) from employee;

-- D9 부서의 직원 인원은 총 몇명일까요?
select count(*) from employee where dept_code in ('D9');

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
    count(*) || '명'
from employee
where dept_code in ('D5', 'D9') and extract(year from hire_date) = 2004;


select
    emp_id 사번,
    emp_name 사원명,
    dept_code 부서코드,
    hire_date 입사일
from employee
where dept_code in ('D5', 'D9') and substr(hire_date, 1, 2) = 04;
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

-- 12.
select
    emp_name 사원명,
    floor((sysdate - hire_date) / 365) + 1 년차,
    case
        when floor((sysdate - hire_date) / 365) + 1 < 10 then 'Junior'
        when floor((sysdate - hire_date) / 365) + 1 < 20 then 'Intermediate'
        when floor((sysdate - hire_date) / 365) + 1 >= 20 then 'Senior'
    end 직급레벨
from employee
order by floor((sysdate - hire_date) / 365) + 1;

-- group by -> 데이터 그룹화 문법
select
    nvl(dept_code, '인턴') 부서코드,
    to_char(sum(salary), 'L999,999,999') "총 급여"
from employee
group by nvl(dept_code, '인턴');

select
    nvl(job_code, '인턴') 직급코드,
    to_char(avg(salary), 'L999,999,999') "평균 급여"
from employee
group by nvl(job_code, '인턴')
order by nvl(job_code, '인턴') asc;

-- select 쿼리의 실행 순서
-- select       -- 5
-- from         -- 1
-- where        -- 2
-- group ny     -- 3
-- having       -- 4
-- order by     -- 6

select
    nvl(dept_code, '인턴') 부서코드,
    count(*) || '명'
from employee
where bonus is null
group by nvl(dept_code, '인턴')
order by 1; 

-- 직원들의 성별 별 급여 평균, 급여 합계, 인원수를 출력
select
    decode(substr(emp_no, 8, 1), 1, '남', 3, '남', 2, '여', 4, '여') 성별,
    to_char(avg(salary), 'L999,999,999') "급여 평균",
    to_char(sum(salary), 'L999,999,999') "급여 합계",
    count(*) "인원 수"
from employee
group by decode(substr(emp_no, 8, 1), 1, '남', 3, '남', 2, '여', 4, '여')
order by 1;

-- 두 개 이상의 group by가 가능하다
select
    dept_code,
    job_code,
    to_char(sum(salary), 'L999,999,999') "급여 합계"
from employee
group by dept_code, job_code
order by 1, 2;

-------- 각 부서 내 성별 별 인원 수를 출력
select
    nvl(dept_code, '인턴') 부서코드,
    decode(substr(emp_no, 8, 1), 1, '남', 2, '여') 성별,
    count(*) || '명' 인원수
from employee
group by nvl(dept_code, '인턴'), decode(substr(emp_no, 8, 1), 1, '남', 2, '여')
order by 1;

-------- 각 직급 내 년대생 별 인원 수를 출력
select
    nvl(job_code, '인턴') 부서코드,
    case
        when substr(emp_no, 1, 1) = 6 then '60년대생'
        when substr(emp_no, 1, 1) = 7 then '70년대생'
        when substr(emp_no, 1, 1) = 8 then '80년대생'
        else 'etc'
    end 년대생,
    count(*) || '명' 인원수
from employee
group by
    nvl(job_code, '인턴'),
    case
        when substr(emp_no, 1, 1) = 6 then '60년대생'
        when substr(emp_no, 1, 1) = 7 then '70년대생'
        when substr(emp_no, 1, 1) = 8 then '80년대생'
        else 'etc'
    end
order by 1;


