package com.pintoeat.api.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pintoeat.api.pojo.TestEmployeePojo;

@Entity
@Table(name = "employee")
@NamedQuery(name = "TestEmployee.findAll", query = "SELECT e FROM TestEmployee e")
public class TestEmployee {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "employee_no")
	private String employeeNo;

	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "job")
	private String job;
	
	@Column(name = "manager_name")
	private String managerName;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "hire_date")
	private Date hireDate;
	
	@Column(name = "salary") 
	private Integer salary;
	
	@Column(name = "department_no")
	private Integer departmentNo;
	

	public TestEmployee() {
		
	}
	
	public TestEmployee(TestEmployeePojo testEmployeePojo) {
		this.employeeNo = testEmployeePojo.getEmployeeNo();
		this.employeeName = testEmployeePojo.getEmployeeName();
		this.job = testEmployeePojo.getJob();
		this.managerName = testEmployeePojo.getManagerName();
		this.hireDate = testEmployeePojo.getHireDate();
		this.salary = testEmployeePojo.getSalary();
		this.departmentNo = testEmployeePojo.getDepartmentNo();
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(Integer departmentNo) {
		this.departmentNo = departmentNo;
	}
	
	

}
