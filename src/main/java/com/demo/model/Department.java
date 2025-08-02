package com.demo.model;

import java.util.Objects;

/**
 * Department Entity
 */
public class Department {
	private int id;
	private String name;
	
	public Department (int departmentId, String departmentName) {
		this.id = departmentId;
		this.name = departmentName;
	}

	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return this.id;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.id = departmentId;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return this.name;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.name = departmentName;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Department other = (Department) obj;
		return this.id == other.id && Objects.equals(this.name, other.name);
	}

	@Override
	public String toString() {
		return "Department [id= " + this.id + ", name= " + this.name + "]";
	}
}

