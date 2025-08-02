package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.demo.model.Department;
import com.demo.utility.JDBC_Connection;

public class DepartmentDAO {
	
	private JDBC_Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = null;
	
	public DepartmentDAO() {
		this.connection = new JDBC_Connection();
		this.conn = this.connection.getJDBC_Connection();
	}

	
	/** 3
	 * Get the count of employees of a given department ID
	 * @param departmentId 
	 * @return count of employees in a department
	 */
	public int numberOfemployeesInDepartment(int departmentID) {
		int employeeCount = 0;
		String query = "SELECT COUNT(*) FROM emp_dept WHERE dId = ?;";
		try {
			this.ps = this.conn.prepareStatement(query);
			this.ps.setInt(1, departmentID);
			this.rs = this.ps.executeQuery();
			this.rs.next();
			employeeCount = this.rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return employeeCount;
	}

	
	/** 8
	 * Get the average salary of each department
	 * @return list of pairs <department, average salary>
 	 */
	public HashMap<String, Double> avgSalaryofEachDepartments() {
		HashMap<String, Double> deptAvgSalaries = new HashMap<>();
		 		
		String query = "SELECT d.name, AVG(e.salary) " +
					   "FROM department d LEFT JOIN emp_dept ed ON d.id = ed.dId " +
					   "LEFT JOIN employee e ON ed.eid = e.id " +
					   "GROUP BY d.name;";
			
		try {			
			this.ps = this.conn.prepareStatement(query);
			this.rs = this.ps.executeQuery();
						
			while(this.rs.next()) {				
				deptAvgSalaries.put(this.rs.getString(1), this.rs.getDouble(2));
			}	

		} catch (SQLException e) {
				e.printStackTrace();
		}
		 
		return deptAvgSalaries;
	}

	
	/** 10 & 11
	 * Find all departments which have more than or equal to x amount of employees
	 * @param numEmployees the least number of employees to consider
	 * @return list of departments and the employee count
	 */
	public LinkedHashMap<Department, Integer> getDepartmentsHavingAtLeastTheNumberOfEmployees(int numEmployees) {
		LinkedHashMap<Department, Integer> list = new LinkedHashMap<>();
		String query = "SELECT id, name, Count(dId) " +
					   "FROM department LEFT JOIN emp_dept ON id = dId " +
					   "GROUP BY id " +
				 	   "HAVING COUNT(dId) >= ? " +
				 	   "ORDER BY id;"; 
		try {
			this.ps = this.conn.prepareStatement(query);
			ps.setInt(1, numEmployees);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.put((new Department(this.rs.getInt(1), this.rs.getString(2))), this.rs.getInt(3));
			}
		
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	/** 13
	 * Adding a new Department
	 * @param departmentName
	 * @return 1 if the new department added successfully, 0 otherwise.
	 */
	public int addNewDepartment(String departmentName) {
		String query = "INSERT INTO department(name) Values(?);";
		try {
			this.ps = this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			this.ps.setString(1, departmentName);
				
			return this.ps.executeUpdate();	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
}
