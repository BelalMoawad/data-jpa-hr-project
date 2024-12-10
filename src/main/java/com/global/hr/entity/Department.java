package com.global.hr.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "hr_department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_gen")
//	@SequenceGenerator(name = "sequence_gen", sequenceName = "department_seq", initialValue = 10)
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "table_gen")
//	@TableGenerator(name = "table_gen", table = "dept_table", allocationSize = 1, initialValue = 20)
	@Column(name = "dept_id")
	private Long id;
	
	@Column(name = "dept_name")
	private String name;	
	
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
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
