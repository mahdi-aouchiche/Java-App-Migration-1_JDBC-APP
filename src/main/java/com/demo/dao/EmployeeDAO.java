package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.demo.model.Employee;
import com.demo.utility.JDBC_Connection;


public class EmployeeDAO {
	private JDBC_Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = null;
	
	public EmployeeDAO() {
		this.connection = new JDBC_Connection();
		this.conn = this.connection.getJDBC_Connection();
	}
	
	
	/** 1
	 * Get all employees associated to a department
	 * @return a list of employees
	 */
	public List<Employee> getEmployeesOfAllDepartments() {
		List<Employee> employees = new ArrayList<Employee>();
		String query = "SELECT * FROM employee WHERE id IN (SELECT eId FROM emp_dept);";
		
		try {
			this.ps = this.conn.prepareStatement(query);
			rs = ps.executeQuery();
					
			while(rs.next()) {
				employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}	

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

	
	/** 2
	 * Get all employees not associated to a department
	 * @return a list of employees
	 */
	public List<Employee> getAllEmployeesWithoutDepartments() {
		List<Employee> employees = new ArrayList<Employee>();
		String query = "SELECT * FROM employee WHERE id NOT IN (SELECT eId FROM emp_dept);";
		
		try {
			this.ps = this.conn.prepareStatement(query);
			rs = ps.executeQuery();
					
			while(rs.next()) {
				employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}	

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}
	

	/** 4
	 * Find the average salary of all employees
	 * @return average salary
	 */
	public double getEmployeesAverageSalary() {
		double avgSalary = 0;
		String query = "SELECT AVG(salary) FROM employee;";
		try {
			this.ps = this.conn.prepareStatement(query); 
			rs = ps.executeQuery(query);
			
			if(rs.next()) {
				avgSalary = rs.getDouble(1) ;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return avgSalary;
	}

	
	/** 5.0
	 * Find the maximum salary in the employee table
	 * @return maximum salary
	 */
	public double getMaxSalary() {
		double maxSalary = 0;
		String query = "SELECT MAX(salary) FROM employee;";
		try {
			this.ps = this.conn.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			maxSalary = rs.getDouble(1);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return maxSalary;
	}
	
	
	/** 5.1
	 * Find the minimum salary in the employee table
	 * @return minimum salary
	 */
	public double getMinSalary() {
		double minSalary = 0;
		String query = "SELECT MIN(salary) FROM employee;";
		try {
			this.ps = this.conn.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			minSalary = rs.getDouble(1);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return minSalary;
	}

	
	/** 6
	 * Get the second maximum salary of employees
	 * @return second max salary
	 */
	public double getSecondMaxSalary() {
		double secondMaxSalary = 0.0;
		String query = "SELECT MAX(salary) FROM employee WHERE salary < (SELECT MAX(salary) FROM employee);";
		try {
			this.ps = this.conn.prepareStatement(query);
			rs = ps.executeQuery();
			rs.next();
			secondMaxSalary = rs.getDouble(1);		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return secondMaxSalary;
	}
	
	
	/** 7
	 * Get the average salary of the given department
	 * @param departmentId
	 * @return average salary 
	 */
	public double getAvgSalaryByDepartmentId(int departmentId) {
		double avgSalary = 0;
		
		String query = "SELECT AVG(employee.salary) " +
					   "FROM employee INNER JOIN emp_dept " + 
					   "ON employee.id = emp_dept.eId " +
					   "WHERE emp_dept.dId = ?;";
		try {
			this.ps = this.conn.prepareStatement(query);
			ps.setInt(1, departmentId);
			rs = ps.executeQuery();
			rs.next();
			avgSalary = rs.getDouble(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return avgSalary;
	}

	
	/** 9
	 * Get the employee list of employees earning the second maximum salary
	 * @return list of employees
	 */
	public List<Employee> getEmployeesEarningSecondMaxSalary() {
		List<Employee> list = new ArrayList<Employee>();
		
		String query = "Select * FROM employee WHERE employee.salary = " +
				"(SELECT MAX(salary) FROM employee WHERE salary < (SELECT MAX(salary) FROM employee));";
		try {			
			this.ps = this.conn.prepareStatement(query);
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				list.add(new Employee(this.rs.getInt(1), this.rs.getString(2), this.rs.getInt(3), this.rs.getDouble(4)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}


	/** 12
	 * Add an employee to the employee table
	 * @param fullName
	 * @param age
	 * @param salary
	 * @return 1 if employee added successfully, 0 otherwise.
	 */
	public int addEmployee(String fullName, int age, double salary) {
		String query = "INSERT INTO employee(name, age, salary) Values(?,?,?);";
		try {
			this.ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			this.ps.setString(1, fullName);
			this.ps.setInt(2, age);
			this.ps.setDouble(3, salary);
			
			return this.ps.executeUpdate();	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	
	/** 14
	 * Add an employee to a department
	 * @param departmentName 
	 * @param employeeName 
	 * @return 1 if the employee added to a department successfully, 0 otherwise.
	 */
	public int addEmployeeToDepartment(String employeeName, String departmentName) {
		String query = "SELECT e.id, d.id " +
				       "FROM employee AS e, department AS d " +
				       "WHERE e.name = ? && d.name = ?;";
		
		try {
			this.ps = this.conn.prepareStatement(query);
			ps.setString(1, employeeName);
			ps.setString(2, departmentName);
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				return 0;
			};
			
			int employeeId = rs.getInt(1);
			int departmentId = rs.getInt(2);
			
			query = "INSERT INTO emp_dept(eId, dId) VALUES(?,?);";
			this.ps = this.conn.prepareStatement(query);
			ps.setInt(1, employeeId);
			ps.setInt(2, departmentId);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	
	
}
