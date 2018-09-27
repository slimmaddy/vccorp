CREATE DEFINER=`root`@`localhost` PROCEDURE `bai4`(in emp_name varchar(30), in deptname varchar(40), in new_title varchar(50))
BEGIN
	declare id_emp int;
    declare id_dept char(4);
    set id_emp = (select distinct emp_no from employees
    where concat(first_name," ", last_name) = emp_name
    limit 1);
    
    set id_dept = (select distinct dept_no from departments as d
    where dept_name = deptname
    limit 1);
    
	UPDATE dept_emp
	SET to_date = curdate()
	WHERE emp_no = id_emp
    and to_date = '9999-1-1';
    
    UPDATE titles
	SET to_date = curdate()
	WHERE emp_no = id_emp
    and to_date = '9999-1-1';
    
    INSERT INTO dept_emp (emp_no, dept_no, from_date, to_date)
	VALUES (id_emp, id_dept, curdate(), '9999-1-1');
    
    INSERT INTO titles (emp_no, title, from_date, to_date)
	VALUES (id_emp, new_title, curdate(), '9999-1-1');
    
    select e.emp_no as id, concat(e.first_name,' ',e.last_name) as fullname, gender, t.title, d.dept_name
    from employees as e, departments as d, dept_emp as de, titles as t
    where e.emp_no = de.emp_no
    and d.dept_no = de.dept_no
    and e.emp_no = t.emp_no
    and de.to_date = '9999-1-1'
    and t.to_date = '9999-1-1'
    and e.emp_no = id_emp;
END