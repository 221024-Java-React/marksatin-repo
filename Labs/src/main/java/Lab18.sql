-- Lab 18 --

-- Task 1 --
SELECT COUNT(*) FROM employee;


-- Task 2 --
SELECT department_id, ROUND(AVG(monthly_salary))
FROM department d INNER JOIN employee e ON d.department_id = e.department 
GROUP BY department_id ORDER BY department_id ASC;
0

-- Task 3 --
SELECT * FROM employee WHERE monthly_salary = 
(SELECT MAX(monthly_salary) FROM employee);0



-- SETUP --
CREATE TABLE department (
	department_id int PRIMARY KEY,
	department_name varchar(50),
	monthly_budget NUMERIC(7,2)
);

CREATE TABLE location (
	location_id int PRIMARY KEY,
	street varchar(25),
	city varchar(25),
	state varchar(25),
	zipcode int
);

CREATE TABLE employee (
	employee_id int PRIMARY KEY,
	employee_name varchar(64),
	birthday date,
	monthly_salary numeric(7,2) NOT NULL,
	department int REFERENCES department(department_id) NOT NULL,
	hire_date date,
	position varchar(20),
	manager int,
	location int REFERENCES location(location_id)
);