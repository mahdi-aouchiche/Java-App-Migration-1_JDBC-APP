package com.demo.main;

import java.util.Scanner;
import com.demo.service.*;

public class App {

	private EmployeeService employeeService = null;
	private DepartmentService departmentService = null;

	public App() {
		employeeService = new EmployeeService();
		departmentService = new DepartmentService();
	}

	public static void main(String[] args) {
		App app = new App();
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		int menuOption = 0;

		do {
			menuOption = app.getUserOptionFromMenu(scanner);

			switch (menuOption) {
			case 1: // Fetch employee details (id & name) who belong to any department
				app.employeeService.getEmployeesDetailsFromAllDepartments();
				break;

			case 2: // Fetch employees who DO NOT belong to any department
				app.employeeService.getEmployeesWhoDoNotBelongToAnyDepartment();
				break;

			case 3: // Number of employees in a specific department
				app.departmentService.numberOfEmployeesInADepartment();
				break;

			case 4: // Fetch the average salary of employees
				app.employeeService.getAverageSalaryOfAllEmployees();
				break;

			case 5: // Fetch the MIN & MAX salaries of employees
				app.employeeService.getMinAndMaxSalaries();
				break;

			case 6: // Fetch the 2nd maximum salary among employees
				app.employeeService.getSecondMaxSalaryAmongEmployees();
				break;

			case 7: // Fetch the AVG salary of a department
				app.employeeService.getAverageSalaryByDepartment();
				break;

			case 8: // Fetch the AVG salary of all the departments
				app.departmentService.getAvgSalaryOfEachDepartment();
				break;

			case 9: // Fetch the employee details who has 2nd MAX salary
				app.employeeService.getEmployeeDetailsWhoEarnsSecondMaxSalary();
				break;

			case 10: // Fetch the department IDs which are having at least 4 employees
				app.departmentService.getDepartmentIdsWhichHaveAtLeastANumberOfEmployees();
				break;

			case 11: // Fetch the department name which are having at least 4 employees
				app.departmentService.getDepartmentNamesWhichHaveAtLeastNumberOfEmployees();
				break;

			case 12: // Add a new Employee
				app.employeeService.addNewEmployee();
				break;

			case 13: // Create a new Department
				app.departmentService.createNewDepartment();
				break;

			case 14: // Add an existing employee to a department
				app.employeeService.assignEmployeeToDepartment();
				break;

			case 15: // Exit
				exit = true;
				System.out.println("Exiting Application ...");
				break;

			default:
				System.out.println("Invalid option!!!");
			}

			System.out.println();
		} while (!exit);

		scanner.close();
		System.out.println("Application closed!");
	}

	/* Helper functions */

	/**
	 * Display a menu and get the user input
	 * 
	 * @param sc scanner to get user input
	 * @return user input from the selection
	 */
	private int getUserOptionFromMenu(Scanner scanner) {
		this.menu();
		System.out.print("Enter an option from the menu: ");
		return getUserIntegerInput(scanner);
	}

	/**
	 * Displays available options for user selection
	 */
	private void menu() {
		String menu = "\n";
		menu += "- 01 - Fetch employee details (id & name) who belong to any department.\n";
		menu += "- 02 - Fetch employees who DO NOT belong to any department.\n";
		menu += "- 03 - Count of employees for a department.\n";
		menu += "- 04 - Fetch the average salary of employees.\n";
		menu += "- 05 - Fetch the MIN & MAX salaries of employees.\n";
		menu += "- 06 - Fetch the 2nd maximum salary among employees.\n";
		menu += "- 07 - Fetch the AVG salary of a department.\n";
		menu += "- 08 - Fetch the AVG salary of all the departments.\n";
		menu += "- 09 - Fetch the employee details who has 2nd MAX salary.\n";
		menu += "- 10 - Fetch the department IDs which are having at least x employees.\n";
		menu += "- 11 - Fetch the department name which are having at least x employees.\n";
		menu += "- 12 - Add a new Employee.\n";
		menu += "- 13 - Create a new Department.\n";
		menu += "- 14 - Add an existing employee to a department.\n";
		menu += "- 15 - Exit!!!";

		System.out.println(menu);
	}

	/**
	 * Get a valid integer as a user input
	 * 
	 * @param scanner a scanner to read input from System.in
	 * @return the user input as an integer
	 */
	public static int getUserIntegerInput(Scanner scanner) {
		int integer = 0;
		boolean input;
		do {
			String userInput = scanner.nextLine();

			try {
				integer = Integer.parseInt(userInput);
				input = true;
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter an integer: ");
				input = false;
			}

		} while (!input);

		return integer;
	}

	/**
	 * Get a valid double number as a user input
	 * 
	 * @param scanner a scanner to read input from System.in
	 * @return the user input as a double
	 */
	public static double getUserDoubleInput(Scanner scanner) {
		double d = 0;
		boolean input;
		do {
			String userInput = scanner.nextLine();

			try {
				d = Double.parseDouble(userInput);
				input = true;
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter an integer: ");
				input = false;
			}

		} while (!input);

		return d;
	}
}
