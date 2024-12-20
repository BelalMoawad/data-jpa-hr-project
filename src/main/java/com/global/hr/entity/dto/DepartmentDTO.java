package com.global.hr.entity.dto;

import java.util.List;

public class DepartmentDTO {
	
	private Long id;
	
	private String name;	
	
	private List<EmployeeDTO> employees;

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
