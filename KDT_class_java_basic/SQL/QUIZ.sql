-- 문항에서 집계 혹은 소계라는 표현이 나온다면 넘기기
-- basic select
-- 1.
select
    department_name "학과 명",
    category "계열"
from tb_department;

-- 2.
select
    department_name || '의 정원은' || capacity || ' 명 입니다.' "학과별 정원"
from tb_department;

-- 3.
select
    student_name
from
    tb_student stu join tb_department dept on stu.department_no = dept.department_no
where
    department_name like ('국어국문학과') and
    absence_yn = 'Y' and
    substr(student_ssn, 8, 1) = 2;
    
-- 4.
select
    student_name
from
    tb_student
where
    student_no in ('A513079', 'A513090', 'A513091', 'A513110', 'A513119')
order by 1 desc;

-- 5.
select
    department_name,
    category
from
    tb_department
where
    capacity between 20 and 30;
    
-- 6.
select
    professor_name
from
    tb_professor
where
    department_no is null;
    
-- 7.
select
    student_no,
    student_name,
    student_ssn
from
    tb_student
where
    department_no is null;
    
-- 8.
select
    class_no
from
    tb_class
where
    preattending_class_no is not null;
    
-- 9.
select
    category
from
    tb_department
group by
    category
order by category;

-- 10.
select
    student_no,
    student_name,
    student_ssn
from
    tb_student
where
    student_no like ('A2%') and
    student_address like ('%전주%') and
    absence_yn = 'N';

-- additional select    
-- 1.
select
    student_no 학번,
    student_name 이름,
    to_char(entrance_date, 'RRRR-MM-DD') 입학년도
from
    tb_student
where
    department_no = '002'
order by entrance_date asc;

-- 2.
select
    professor_name,
    professor_ssn
from
    tb_professor
where
    professor_name not like ('___');
    
select sysdate from dual;
-- 3.
select
    professor_name 교수이름,
    floor((sysdate - to_date('19' || substr(professor_ssn, 1, 6), 'YYYYMMDD')) / 365) 나이
from
    tb_professor
where
    substr(professor_ssn, 8, 1) = 1
order by 2 asc;

-- 4.
select
    substr(professor_name, -2, 2) 이름
from
    tb_professor;
    
-- 5.
select
    student_no,
    student_name
from
    tb_student join
    (
        select
--            case
--                when substr(entrance_date, 1, 1) = 0 then ('20' || substr(entrance_date, 1, 2)) - ('19' || substr(student_ssn, 1, 2))
--                when substr(entrance_date, 1, 1) != 0 then ('19' || substr(entrance_date, 1, 2)) - ('19' || substr(student_ssn, 1, 2))
--            end 입학당시나이,
            extract(year from to_date(substr(entrance_date, 1, 8), 'RR/MM/DD')) - extract(year from to_date(substr(student_ssn, 1, 6), 'RRMMDD')) 입학당시나이,
            student_ssn 주민번호
        from
            tb_student
    )
    on student_ssn = 주민번호
where
    입학당시나이 > 19
order by 2;

-- 6.
select to_char(to_date('2020/12/25', 'YYYY/MM/DD'), 'DY') from dual;

-- 7.
select extract(year from to_date('20/10/11', 'RR/MM/DD')) from dual;
-- YY는 2099/10/11, 2049/10/11, RR은 1999/10/11, 1949/10/00 을 뜻함

-- 8.
select
    student_no,
    student_name
from
    tb_student
where
    student_no not like ('A%');
    
-- 9.
select
    round(avg(point), 1) 평점
from
    tb_grade
group by
    student_no
having
    student_no = 'A517178';
    
-- 10.
select
    department_no 학과번호,
    count(*) "학생수(명)"
from
    tb_student
group by
    department_no
order by
    department_no asc;

-- 11.
select
    count(*)
from tb_student
group by coach_professor_no
having coach_professor_no is null;

-- 12.
select * from tb_grade;
select
    substr(term_no, 1, 4) 년도,
    round(avg(point), 1) "년도 별 평점"
from
    tb_grade
where
    student_no = 'A112113'
group by
    substr(term_no, 1, 4)
order by 1;

-- 13.  0명인 학과 출력 미해결
select
    department_no 학과코드명,
    count(*) "휴학생 수"
from
    tb_student
group by
    department_no,
    absence_yn
having
    absence_yn = 'Y'
order by
    department_no;
    
-- 14.
select
    student_name 동일이름,
    count(*) "동명인 수"
from
    tb_student
group by
    student_name
having
    count(*) > 1
order by student_name;

-- 15.  년도 평균이랑 합치는거 미해결
select
    substr(term_no, 1, 4) 년도,
    substr(term_no, 5, 2) 학기,
    round(avg(point), 1) 평점
from
    tb_grade
where
    student_no = 'A112113'
group by
    substr(term_no, 1, 4),
    substr(term_no, 5, 2)
order by 1;

-- additional option
-- 1.
select
    student_name "학생 이름",
    student_address "주소지"
from
    tb_student
order by
    student_name asc;

-- 2.
select
    student_name,
    student_ssn
from
    tb_student
where
    absence_yn = 'Y'
order by student_ssn desc;

-- 3.
select
    student_name 학생이름,
    student_no 학번,
    student_address "거주지 주소"
from
    tb_student
where
    (student_address like ('강원도%') or
    student_address like ('경기도%')) and
    student_no not like ('A%');
    
-- 4.
select
    professor_name,
    professor_ssn
from
    tb_professor pro join tb_department dept on pro.department_no = dept.department_no
where
    department_name = '법학과'
order by 2;

-- 5.
select
    student_no,
    point
from
    tb_grade
where
    term_no = '200402' and
    class_no = 'C3118100'
order by
    point desc, student_no;
    
-- 6.
select
    student_no,
    student_name,
    department_name
from
    tb_student stu join tb_department dept on stu.department_no = dept.department_no
order by
    student_name asc;
    
-- 7.
select
    class_name,
    department_name
from
    tb_class cls join tb_department dept on cls.department_no = dept.department_no;

-- 8.
select
    class_name,
    professor_name
from
    tb_class cls join tb_class_professor cls_pro on cls.class_no = cls_pro.class_no
    join tb_professor pro on cls_pro.professor_no = pro.professor_no
order by professor_name;

-- 9.
select
    class_name,
    professor_name
from
    tb_class cls join tb_class_professor cls_pro on cls.class_no = cls_pro.class_no
    join tb_professor pro on cls_pro.professor_no = pro.professor_no
    join tb_department dept on pro.department_no = dept.department_no
where
    dept.category = '인문사회'
order by professor_name;

-- 10.
select
    stu.student_no 학번,
    student_name "학생 이름",
    round(avg(point), 1) "전체 평점"
from
    tb_student stu join tb_grade grade on stu.student_no = grade.student_no
where
    stu.department_no =
    (
        select
            department_no
        from
            tb_department
        where
            department_name = '음악학과'
    )
group by
    stu.student_no,
    student_name
order by 1;

-- 11.
select
    department_name 학과이름,
    student_name 학생이름,
    professor_name 지도교수이름
from
    tb_student stu join tb_department dept on stu.department_no = dept.department_no
    join tb_professor pro on stu.coach_professor_no = pro.professor_no
where
    student_no = 'A313047';
    
-- 12.
select
    student_name,
    term_no "term_name"
from
    tb_student stu join tb_grade grade on stu.student_no = grade.student_no
    join tb_class cls on grade.class_no = cls.class_no
where
    cls.class_name = '인간관계론' and
    substr(grade.term_no, 1, 4) = '2007'
order by term_no, student_name;

-- 13.
select
    class_name,
    department_name
from
    tb_class cls full join tb_class_professor cls_pro on cls.class_no = cls_pro.class_no
    join tb_department dept on cls.department_no = dept.department_no
where
    cls_pro.professor_no is null and
    dept.category = '예체능'
order by 2;

-- 14.
select
    student_name 학생이름,
    nvl(professor_name, '지도교수 미지정') 지도교수
from
    tb_student stu left join tb_professor pro on stu.coach_professor_no = pro.professor_no
    join tb_department dept on stu.department_no = dept.department_no
where
    dept.department_name = '서반아어학과';
    
-- 15.
select
    student_no 학번,
    student_name 이름,
    department_name "학과 이름",
    평점 평점
from 
    tb_student stu join tb_department dept on stu.department_no = dept.department_no
    join 
    (
        select
            avg(point) 평점,
            student_no 학번
        from
            tb_grade
        group by student_no
    ) on stu.student_no = 학번
where
    stu.absence_yn = 'N' and
    평점 >= 4.0;
    
-- 16.
select
    cls.class_no,
    class_name,
    avg(point) "AVG(POINT)"
from
    tb_grade grade join tb_class cls on grade.class_no = cls.class_no
    join tb_department dept on dept.department_no = cls.department_no
where
    dept.department_name = '환경조경학과' and
    cls.class_type like '전공%'
group by
    cls.class_no,
    class_name
order by 1;
    
-- 17.
select
    student_name,
    student_address
from
    tb_student
where
    department_no =
    (
        select
            department_no
        from
            tb_student
        where
            student_name = '최경희'
    );
    
-- 18.
select
    학번,
    학생이름
from
    (
        select
            avg(point) 평점,
            stu.student_no 학번,
            department_name 학과명,
            student_name 학생이름
        from
            tb_grade grade join tb_student stu on grade.student_no = stu.student_no
            join tb_department dept on stu.department_no = dept.department_no
        group by
            department_name, stu.student_no, student_name
        having
            department_name = '국어국문학과'
    )
    join
    (
        select
            max(평점) 수석점수
        from
            (
                select
                    avg(point) 평점
                from
                    tb_grade grade join tb_student stu on grade.student_no = stu.student_no
                    join tb_department dept on stu.department_no = dept.department_no
                group by
                    department_name, stu.student_no, student_name
                having
                    department_name = '국어국문학과'
            )
    ) on 평점 = 수석점수;
    
-- 19.
select
    department_name 계열학과명,
    round(avg(point), 1) 전공평점
from
    tb_student stu join tb_grade grade on stu.student_no = grade.student_no
    join tb_department dept on stu.department_no = dept.department_no
    join tb_class cls on cls.class_no = grade.class_no
where
    class_type like ('전공%') and
    category =
    (
        select
            category
        from
            tb_department
        where
            department_name = '환경조경학과'
    )
group by
    department_name
order by 1;

-- DDL
-- 1.
create table tb_category(
    name varchar2(10),
    use_yn char(1) default 'Y'
);
desc tb_category;
-- 2.
create table tb_class_type(
    no varchar2(5) primary key,
    name varchar2(10)
);
desc tb_class_type;
-- 3.
alter table tb_category add primary key(name);

-- 4.
alter table tb_class_type modify(name not null);

-- 5.
alter table tb_class_type modify(no varchar2(10), name varchar2(20));
alter table tb_category modify(name varchar2(20));

-- 6.
alter table tb_class_type rename column no to class_type_no;
alter table tb_class_type rename column name to class_type_name;
alter table tb_category rename column name to category_name;
alter table tb_category rename column use_yn to category_use_yn;

-- 7.
select * from user_constraints where table_name='TB_CLASS_TYPE';
select * from user_constraints where table_name='TB_CATEGORY';
alter table tb_category rename column category_name to pk_category_name;
alter table tb_class_type rename column class_type_no to pk_class_type_no;

-- 8.
insert into tb_category values('공학', 'Y');
insert into tb_category values('자연과학', 'Y');
insert into tb_category values('의학', 'Y');
insert into tb_category values('예체능', 'Y');
insert into tb_category values('인문사회', 'Y');
commit;
select * from tb_category;

-- 9.
alter table tb_department add (fk_department_category references tb_category(pk_category_name));
select * from tb_department;