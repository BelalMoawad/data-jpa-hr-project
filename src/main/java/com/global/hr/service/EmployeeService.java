package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.HRStatisticsProjection;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private UserService userService;
	
	public Employee findById(Long id) {
		return employeeRepo.findById(id) // return optional if employee not found throw an exception
				.orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));
	}
	
	public Employee insert(Employee emp) {
		/*
		 * if the department is detached then we can give persisted department not the
		 * detached one to the employee to be managed in application context  
		 */
		if(emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			emp.setDepartment(departmentService.findById(emp.getDepartment().getId()));
		}
		
		if(emp.getUser() != null && emp.getUser().getId() != null) {
			emp.setUser(userService.findById(emp.getUser().getId()));
		}
		 
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
		current.setUser(emp.getUser());
		return employeeRepo.save(current);
	}
	
	public void deleteById(Long id) {
		employeeRepo.deleteById(id);
	}

	public void deleteAll() {
		employeeRepo.deleteAll();
	}
	
	public List<Employee> findByEmpDept(String empName, String deptName) {
		return employeeRepo.findByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	public Long countByEmpDept(String empName, String deptName) {
		return employeeRepo.countByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	public Long deleteByEmpDept(String empName, String deptName) {
		return employeeRepo.deleteByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	public List<Employee> findBySalary(Double empSalary) {
		return employeeRepo.findBySalary(empSalary);
	}
	
	public HRStatisticsProjection getHRStatistics() {
		return employeeRepo.getHRStatistics();
	}

}
