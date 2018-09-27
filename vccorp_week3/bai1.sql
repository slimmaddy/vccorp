use employees;
/*cau 1.1*/
select * from employees
where year(hire_date) >=1999
limit 10;

/*cau 1.2*/
select count(*) from employees
where (year(birth_date) between 1950 and 1960)
and last_name like "Mon%";
/*cau 1.3*/
select first_name, last_name, hire_date, sum(s.salary) as salary_total
from employees as e, salaries as s, titles as t
where e.emp_no = s.emp_no 
and e.emp_no = t.emp_no
and e.emp_no = 10005
and t.title = 'Staff'
and s.from_date >= t.from_date
and s.to_date <= t.to_date;

/*cau 1.4*/
select count(e1.emp_no) from employees as e1, employees as e2, dept_emp as de,
dept_manager as dm
where e1.emp_no = de.emp_no
and e2.emp_no = dm.emp_no
and concat(e2.first_name," ",e2.last_name) = "Margareta Markovitch"
and de.from_date >= dm.from_date
and de.to_date <= dm.to_date;

/*cai 1.5*/
select d.dept_name, sum(s.salary) as dept_salary
from employees as e, salaries as s, departments as d,
dept_emp as de
where e.emp_no = de.emp_no
and d.dept_no = de.dept_no
and e.emp_no = s.emp_no
and s.from_date >= '1988-06-25'
and s.to_date <= '1989-06-25'
group by d.dept_name
having dept_salary > 3000000;



