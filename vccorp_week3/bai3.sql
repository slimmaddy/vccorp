CREATE DEFINER=`root`@`localhost` PROCEDURE `bai3`(in name varchar(16))
BEGIN	
	DROP TABLE if exists myTable;
	CREATE TEMPORARY TABLE myTable (id int, fullname varchar(30), title varchar(50),dept_name varchar(40));
	insert into myTable
    select e.emp_no, concat(e.first_name," ",e.last_name) as fullname,
    t.title,d.dept_name
    from employees as e, departments as d, titles as t, dept_emp as de
    where e.emp_no = de.emp_no
    and de.dept_no = de.dept_no
    and e.emp_no = t.emp_no
    and (e.first_name like concat('%',name,'%') or e.last_name like concat('%',name,'%'));
    select * from myTable;
    
    select e.emp_no, concat(e.first_name," ",e.last_name) as fullname, sum(s.salary) as salary_total
    from employees as e, salaries as s
    where e.emp_no = s.emp_no
    and e.emp_no in (select distinct id from myTable)
    and s.to_date < curdate()
    group by e.emp_no;
    
END