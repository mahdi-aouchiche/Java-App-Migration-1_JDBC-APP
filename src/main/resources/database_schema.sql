--Create company_hr_db database and create schema:
  -- department table.
  -- employee table.
  -- emp_dept relationship table 
  
CREATE DATABASE company_hr_db;

USE company_hr_db;

-- Create an employee table
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(30),
	age INT,
	salary DOUBLE
	-- If we have a many to one relationship we can assign each employee to a department using a fk
	-- CONSTRAINT fk_dId INT FOREIGN KEY (dId) REFERENCES department(id)
);

-- Create a department table
DROP TABLE IF EXISTS department;
CREATE TABLE department (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(30)	
);

-- Create an employee-department relationship table
DROP TABLE IF EXISTS emp_dept;
CREATE TABLE emp_dept (
	eId INT,
	dId INT,
	CONSTRAINT fk_eId FOREIGN KEY (eId) REFERENCES employee(id),
	CONSTRAINT fk_dId FOREIGN KEY (dId) REFERENCES department(id)
);
