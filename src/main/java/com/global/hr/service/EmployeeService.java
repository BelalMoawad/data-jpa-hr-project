package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public Employee findById(Long id) {
		return employeeRepo.findById(id) // return optional if employee not found throw an exception
				.orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));
	}
	
	public Employee insert(Employee emp) {
		return employeeRepo.save(emp);
	}
	
	public Iterable<Employee> findAll() {
		return employeeRepo.findAll();
	}
	
	public List<Employee> findByDepartmentIdNative(Long deptId) {
		return employeeRepo.findByDepartmentIdNative(deptId);
	}
	
	public List<Employee> findByDepartmentIdJPQL(Long deptId) {
		return employeeRepo.findByDepartmentIdJPQL(deptId);
	}
	
	public List<Employee> findByDepartmentId(Long deptId) {
		return employeeRepo.findByDepartmentId(deptId);
	}
	
	public Employee update(Employee emp) {
		Employee current = employeeRepo.getReferenceById(emp.getId());
		current.setName(emp.getName());
		current.setSalary(emp.getSalary());
		current.setDepartment(emp.getDepartment());
		return employeeRepo.save(current);
	}
	
	
}
