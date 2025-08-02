package com.demo.service;

import java.util.List;
import java.util.Scanner;

import com.demo.dao.EmployeeDAO;
import com.demo.model.*;
import com.demo.main.*;

public class EmployeeService {
	EmployeeDAO employeeDAO = null;
	Scanner scanner = new Scanner(System.in);

	/**
	 * 1 Fetch employee details (id & name) who belong to any department
	 */
	public void getEmployeesDetailsFromAllDepartments() {
		employeeDAO = new EmployeeDAO();
		List<Employee> employeesList = employeeDAO.getEmployeesOfAllDepartments();

		System.out.println("IDs and Names of employees who are associated to any department: ");
		for (Employee employee : employeesList) {
			System.out.println("ID: " + employee.getEmployeeId() + ", Name: " + employee.getEmployeeName());
		}
	}

	/**
	 * 2 Fetch employees who DO NOT belong to any department
	 */
	public void getEmployeesWhoDoNotBelongToAnyDepartment() {
		employeeDAO = new EmployeeDAO();
		List<Employee> employeesList = employeeDAO.getAllEmployeesWithoutDepartments();

		System.out.println("IDs and Names of employees who are associated to any department: ");
		for (Employee employee : employeesList) {
			System.out.println("ID: " + employee.getEmployeeId() + ", Name: " + employee.getEmployeeName());
		}
	}

	/**
	 * 4 Find the average salary of all employees
	 */
	public void getAverageSalaryOfAllEmployees() {
		employeeDAO = new EmployeeDAO();
		double avgSalary = this.employeeDAO.getEmployeesAverageSalary();
		System.out.printf("The average salary of all employees = $%,.2f%n", avgSalary);
	}

	/**
	 * 5 Get the maximum and the minimum salaries
	 */
	public void getMinAndMaxSalaries() {
		employeeDAO = new EmployeeDAO();
		double max = this.employeeDAO.getMaxSalary();
		double min = this.employeeDAO.getMinSalary();

		System.out.printf("Max salary = $%,9.2f%n", max);
		System.out.printf("Min salary = $%,9.2f%n", min);
	}

	/**
	 * 6 Display the second max salary
	 */
	public void getSecondMaxSalaryAmongEmployees() {
		employeeDAO = new EmployeeDAO();
		double secondMaxSalary = this.employeeDAO.getSecondMaxSalary();
		System.out.printf("Second Max salary = $%,9.2f%n", secondMaxSalary);
	}

	/**
	 * 7 Get an average salary of a department
	 */
	public void getAverageSalaryByDepartment() {
		employeeDAO = new EmployeeDAO();
		System.out.print("Select a department ID: ");

		int departmentId = App.getUserIntegerInput(scanner);
		double avgSalary = this.employeeDAO.getAvgSalaryByDepartmentId(departmentId);
		System.out.printf("Average salary of department id = %d is $%,9.2f%n", departmentId, avgSalary);
	}

	/**
	 * 9 Get the details of employees earning the second max Salary
	 */
	public void getEmployeeDetailsWhoEarnsSecondMaxSalary() {
		employeeDAO = new EmployeeDAO();
		System.out.println("Employees earning second max salary are:");
		for (Employee e : this.employeeDAO.getEmployeesEarningSecondMaxSalary()) {
			System.out.printf("Id = %d, Name = %s, Age = %d, Salary = $%,9.2f%n", e.getEmployeeId(),
					e.getEmployeeName(), e.getAge(), e.getSalary());
		}
	}

	/**
	 * 12 Add a new employee
	 */
	public void addNewEmployee() {
		employeeDAO = new EmployeeDAO();

		System.out.print("Enter the employee first and last names: ");
		String fullName = scanner.nextLine();
		while ((fullName.split(" ")).length < 2) {
			System.out.print("Enter both the employee first name and last name: ");
			fullName = scanner.nextLine();
		}

		System.out.print("Enter the employee's age: ");
		int age = App.getUserIntegerInput(scanner);

		System.out.print("Enter the employee's yearly salary: ");
		double salary = App.getUserDoubleInput(scanner);

		int numEmployees = employeeDAO.addEmployee(fullName, age, salary);
		if (numEmployees == 0) {
			System.out.println("Adding an employee is unsuccessful!!!");
		} else {
			System.out.println("Employee added successfully!");
		}
	}

	/**
	 * 14 Assign an existing employee to an existing department
	 */
	public void assignEmployeeToDepartment() {
		employeeDAO = new EmployeeDAO();

		System.out.print("Enter the employee's firstname and lastname: ");
		String employeeName = scanner.nextLine();
		while ((employeeName.split(" ")).length < 2) {
			System.out.print("Invalid. Enter both the employee first name and last name: ");
			employeeName = scanner.nextLine();
		}

		System.out.print("Enter the department's name: ");
		String departmentName = scanner.nextLine();
		while (departmentName.length() < 1) {
			System.out.print("Department's name field cannot be empty or invalid name! Enter a name: ");
			departmentName = scanner.nextLine();
		}

		int assigned = employeeDAO.addEmployeeToDepartment(employeeName, departmentName);
		if (assigned == 0) {
			System.out.println("Assigning an employee to a department is unsuccessful!!!");
		} else {
			System.out.println("Employee assigned to a department successfully!");
		}
	}
}
