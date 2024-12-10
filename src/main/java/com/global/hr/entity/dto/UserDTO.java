package com.global.hr.entity.dto;

import java.util.HashSet;
import java.util.Set;


public class UserDTO {

	private Long id;
	
	private String userName;
	
	private String password;
	
	private EmployeeDTO employee;
	
	private Set<RoleDTO> roles = new HashSet<>();

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	

	
	
}
