-- Lab 19 --

-- Task 1 --
SELECT employee_name, department_name FROM 
employee e INNER JOIN department d ON 
e.department = d.department_id ORDER BY 
department_name ASC;

-- Task 2 --
SELECT employee_name, department_name FROM
employee e RIGHT JOIN department d ON
e.department = d.department_id ORDER BY 
department_name ASC;

-- Task 3 --
SELECT employee_name, city FROM 
employee e LEFT JOIN location l ON
e.location = l.location_id WHERE 
location IS NOT NULL;

-- Task 4 --
SELECT * FROM employee WHERE department = 1
UNION
SELECT * FROM employee WHERE location = 1;

-- Task 5 --
SELECT * FROM employee WHERE department = 1
EXCEPT 
SELECT * FROM employee WHERE location = 1;

-- Task 6 --
SELECT * FROM employee WHERE department = 1
INTERSECT  
SELECT * FROM employee WHERE location = 1;
