use employees;
DELETE FROM dept_emp
WHERE dept_no = (select dept_no from departments
where dept_name='Production');

DELETE FROM dept_manager
WHERE dept_no = (select dept_no from departments
where dept_name='Production');

DELETE FROM departments
where dept_name='Production';
