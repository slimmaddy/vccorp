use employees;
set @dept_id = cast((select substring_index(dept_no,'d',-1) from departments
order by dept_no desc
limit 1)as signed);

INSERT INTO departments (dept_no, dept_name)
VALUES (concat('d',@dept_id+1),'BigData & ML1'); 

INSERT INTO dept_manager (dept_no, emp_no, from_date, to_date)
VALUES (concat('d',@dept_id+1),10173,curdate(),'9999-1-1'); 


