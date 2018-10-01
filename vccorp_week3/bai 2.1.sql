use employees;

UPDATE titles
SET to_date = curdate()
WHERE emp_no=10002;

INSERT INTO titles (emp_no, title, from_date, to_date)
VALUES (10002, 'Senior Staff', curdate(), '9999-1-1'); 
