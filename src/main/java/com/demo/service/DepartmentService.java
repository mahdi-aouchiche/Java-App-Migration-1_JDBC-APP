package com.demo.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

import com.demo.dao.DepartmentDAO;
import com.demo.main.App;
import com.demo.model.Department;


public class DepartmentService {
	
	DepartmentDAO departmentDAO = null;
	Scanner scanner = new Scanner(System.in);
	
	
	/** 3
	 * Get the number of employees in a given department ID
	 */
	public void numberOfEmployeesInADepartment() {
		this.departmentDAO = new DepartmentDAO();	
		System.out.print("Select a department ID: ");
		int departmentID = App.getUserIntegerInput(this.scanner);
		int num = this.departmentDAO.numberOfemployeesInDepartment(departmentID);
		System.out.println("There are " + num + " employees");
	}
	
	
	/** 8
	 * Display all average salaries of each department
	 */ 
	public void getAvgSalaryOfEachDepartment() {
		this.departmentDAO = new DepartmentDAO();
		HashMap<String, Double> list = this.departmentDAO.avgSalaryofEachDepartments();
		for(String dept : list.keySet()) {
			System.out.printf("%s: %n\tAverage Salary = $%,9.2f%n", dept, list.get(dept));
		}	
	}
	
	
	/** 10
	 * Find all department id's which have more than x amount of employees.
	 */
	public void getDepartmentIdsWhichHaveAtLeastANumberOfEmployees() {
		this.departmentDAO = new DepartmentDAO();
		System.out.print("Enter the least number of employees per department: ");
		int numEmployees = App.getUserIntegerInput(scanner);
		
		LinkedHashMap<Department, Integer> 
			list = this.departmentDAO.getDepartmentsHavingAtLeastTheNumberOfEmployees(numEmployees);
		
		if(list.isEmpty()) {
			System.out.printf("No department has %d or more employees.%n", numEmployees);
			return;
		}
		for(Department dept : list.keySet()) {
			System.out.printf("Department id = %d, number of employees %d%n",
							dept.getDepartmentId(), list.get(dept) );
		}	
	}
	
	
	/** 11
	 * Find all department names which have more than x amount of employees.
	 */
	public void getDepartmentNamesWhichHaveAtLeastNumberOfEmployees() {
		this.departmentDAO = new DepartmentDAO();
		System.out.print("Enter the least number of employees per department: ");
		int numEmployees = App.getUserIntegerInput(scanner);
		
		LinkedHashMap<Department, Integer> list = this.departmentDAO.getDepartmentsHavingAtLeastTheNumberOfEmployees(numEmployees);
		if(list.isEmpty()) {
			System.out.printf("No department has %d or more employees.%n", numEmployees);
			return;
		}
		for(Department dept : list.keySet()) {
			System.out.printf("Department name = %s, number of employees %d%n",
								dept.getDepartmentName(), list.get(dept) );
		}	
	}

	
	/** 12
	 * Add a new Department
	 */
	public void createNewDepartment() {
		this.departmentDAO = new DepartmentDAO();
		
		System.out.print("Enter the department's name: "); 
		String departmentName = scanner.nextLine();
		while(departmentName.length() < 1) {
			System.out.print("Department's name field cannot be empty or invalid name! Enter a name: "); 
			departmentName = scanner.nextLine();
		}
		
		int numNewDepartment = departmentDAO.addNewDepartment(departmentName);
		if(numNewDepartment == 0) {
			System.out.println("Adding a department is unsuccessful!!!");
		} else {
			System.out.println("Department added successfully!");
		}
	}
}
