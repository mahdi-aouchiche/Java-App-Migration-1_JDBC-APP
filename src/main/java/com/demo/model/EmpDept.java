package com.demo.model;

import java.util.Objects;

/**
 * EmployeeDepartment Entity 
 */
public class EmpDept {
	
	private int eId;
	private int dId;	

	public EmpDept(int employeeId, int departmentId) {
		this.eId = employeeId;
		this.dId = departmentId;
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return this.eId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.eId = employeeId;
	}

	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return this.dId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.dId = departmentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.eId, this.dId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		EmpDept other = (EmpDept) obj;
		return this.dId == other.dId && this.eId == other.eId;
	}

	@Override
	public String toString() {
		return "Employee Department [Employee ID = " + this.eId + ", Department ID = " + this.dId + "]";
	}
}
