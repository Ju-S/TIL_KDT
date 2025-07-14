-- ���׿��� ���� Ȥ�� �Ұ��� ǥ���� ���´ٸ� �ѱ��
-- basic select
-- 1.
select
    department_name "�а� ��",
    category "�迭"
from tb_department;

-- 2.
select
    department_name || '�� ������' || capacity || ' �� �Դϴ�.' "�а��� ����"
from tb_department;

-- 3.
select
    student_name
from
    tb_student stu join tb_department dept on stu.department_no = dept.department_no
where
    department_name like ('������а�') and
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
    student_address like ('%����%') and
    absence_yn = 'N';

-- additional select    
-- 1.
select
    student_no �й�,
    student_name �̸�,
    to_char(entrance_date, 'RRRR-MM-DD') ���г⵵
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
    professor_name �����̸�,
    floor((sysdate - to_date('19' || substr(professor_ssn, 1, 6), 'YYYYMMDD')) / 365) ����
from
    tb_professor
where
    substr(professor_ssn, 8, 1) = 1
order by 2 asc;

-- 4.
select
    substr(professor_name, -2, 2) �̸�
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
--            end ���д�ó���,
            extract(year from to_date(substr(entrance_date, 1, 8), 'RR/MM/DD')) - extract(year from to_date(substr(student_ssn, 1, 6), 'RRMMDD')) ���д�ó���,
            student_ssn �ֹι�ȣ
        from
            tb_student
    )
    on student_ssn = �ֹι�ȣ
where
    ���д�ó��� > 19
order by 2;

-- 6.
select to_char(to_date('2020/12/25', 'YYYY/MM/DD'), 'DY') from dual;

-- 7.
select extract(year from to_date('20/10/11', 'RR/MM/DD')) from dual;
-- YY�� 2099/10/11, 2049/10/11, RR�� 1999/10/11, 1949/10/00 �� ����

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
    round(avg(point), 1) ����
from
    tb_grade
group by
    student_no
having
    student_no = 'A517178';
    
-- 10.
select
    department_no �а���ȣ,
    count(*) "�л���(��)"
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
    substr(term_no, 1, 4) �⵵,
    round(avg(point), 1) "�⵵ �� ����"
from
    tb_grade
where
    student_no = 'A112113'
group by
    substr(term_no, 1, 4)
order by 1;

-- 13.  0���� �а� ��� ���ذ�
select
    department_no �а��ڵ��,
    count(*) "���л� ��"
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
    student_name �����̸�,
    count(*) "������ ��"
from
    tb_student
group by
    student_name
having
    count(*) > 1
order by student_name;

-- 15.  �⵵ ����̶� ��ġ�°� ���ذ�
select
    substr(term_no, 1, 4) �⵵,
    substr(term_no, 5, 2) �б�,
    round(avg(point), 1) ����
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
    student_name "�л� �̸�",
    student_address "�ּ���"
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
    student_name �л��̸�,
    student_no �й�,
    student_address "������ �ּ�"
from
    tb_student
where
    (student_address like ('������%') or
    student_address like ('��⵵%')) and
    student_no not like ('A%');
    
-- 4.
select
    professor_name,
    professor_ssn
from
    tb_professor pro join tb_department dept on pro.department_no = dept.department_no
where
    department_name = '���а�'
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
    dept.category = '�ι���ȸ'
order by professor_name;

-- 10.
select
    stu.student_no �й�,
    student_name "�л� �̸�",
    round(avg(point), 1) "��ü ����"
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
            department_name = '�����а�'
    )
group by
    stu.student_no,
    student_name
order by 1;

-- 11.
select
    department_name �а��̸�,
    student_name �л��̸�,
    professor_name ���������̸�
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
    cls.class_name = '�ΰ������' and
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
    dept.category = '��ü��'
order by 2;

-- 14.
select
    student_name �л��̸�,
    nvl(professor_name, '�������� ������') ��������
from
    tb_student stu left join tb_professor pro on stu.coach_professor_no = pro.professor_no
    join tb_department dept on stu.department_no = dept.department_no
where
    dept.department_name = '���ݾƾ��а�';
    
-- 15.
select
    student_no �й�,
    student_name �̸�,
    department_name "�а� �̸�",
    ���� ����
from 
    tb_student stu join tb_department dept on stu.department_no = dept.department_no
    join 
    (
        select
            avg(point) ����,
            student_no �й�
        from
            tb_grade
        group by student_no
    ) on stu.student_no = �й�
where
    stu.absence_yn = 'N' and
    ���� >= 4.0;
    
-- 16.
select
    cls.class_no,
    class_name,
    avg(point) "AVG(POINT)"
from
    tb_grade grade join tb_class cls on grade.class_no = cls.class_no
    join tb_department dept on dept.department_no = cls.department_no
where
    dept.department_name = 'ȯ�������а�' and
    cls.class_type like '����%'
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
            student_name = '�ְ���'
    );
    
-- 18.
select
    �й�,
    �л��̸�
from
    (
        select
            avg(point) ����,
            stu.student_no �й�,
            department_name �а���,
            student_name �л��̸�
        from
            tb_grade grade join tb_student stu on grade.student_no = stu.student_no
            join tb_department dept on stu.department_no = dept.department_no
        group by
            department_name, stu.student_no, student_name
        having
            department_name = '������а�'
    )
    join
    (
        select
            max(����) ��������
        from
            (
                select
                    avg(point) ����
                from
                    tb_grade grade join tb_student stu on grade.student_no = stu.student_no
                    join tb_department dept on stu.department_no = dept.department_no
                group by
                    department_name, stu.student_no, student_name
                having
                    department_name = '������а�'
            )
    ) on ���� = ��������;
    
-- 19.
select
    department_name �迭�а���,
    round(avg(point), 1) ��������
from
    tb_student stu join tb_grade grade on stu.student_no = grade.student_no
    join tb_department dept on stu.department_no = dept.department_no
    join tb_class cls on cls.class_no = grade.class_no
where
    class_type like ('����%') and
    category =
    (
        select
            category
        from
            tb_department
        where
            department_name = 'ȯ�������а�'
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
insert into tb_category values('����', 'Y');
insert into tb_category values('�ڿ�����', 'Y');
insert into tb_category values('����', 'Y');
insert into tb_category values('��ü��', 'Y');
insert into tb_category values('�ι���ȸ', 'Y');
commit;
select * from tb_category;

-- 9.
alter table tb_department add (fk_department_category references tb_category(pk_category_name));
select * from tb_department;