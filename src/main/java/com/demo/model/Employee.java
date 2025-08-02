package com.demo.model;

import java.util.Objects;

/**
 * Employee Entity  
 */
public class Employee {
	private int id;
	private String name;
	private int age;
	private double salary;
	
	/**
	 * Used to insert an Employee to the java_db 
	 */
	public Employee (String employeeName, int age, double salary) {
		this.name = employeeName;
		this.age = age;
		this.salary = salary;
	}
	
	/**
	 * Used to hold a full Employee record 
	 */
	public Employee (int employeeId, String employeeName, int age, double salary) {
		this.id = employeeId;
		this.name = employeeName;
		this.age = age;
		this.salary = salary;
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return this.id;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return this.name;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.name = employeeName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return this.salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, name, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Employee other = (Employee) obj;
		return age == other.age && id == other.id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary);
	}

	@Override
	public String toString() {
		return "Employee [id= " + this.id + ", name= " + this.name + ", age= " +
							this.age + ", salary= " + salary + "]";
	}
}
