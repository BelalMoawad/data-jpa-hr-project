package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Employee;
import com.global.hr.service.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("")
	public Iterable<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/department/native/{deptId}")
	public List<Employee> findByDepartmentIdNative(@PathVariable Long deptId) {
		return employeeService.findByDepartmentIdNative(deptId);
	}
	
	@GetMapping("/department/jpql/{deptId}")
	public List<Employee> findByDepartmentIdJPQL(@PathVariable Long deptId) {
		return employeeService.findByDepartmentIdJPQL(deptId);
	}
	
	@GetMapping("/department/{deptId}")
	public List<Employee> findByDepartmentId(@PathVariable Long deptId) {
		return employeeService.findByDepartmentId(deptId);
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {
		return employeeService.findById(id);
	}
	
	@PostMapping("")
	public Employee insert(@RequestBody Employee emp) {
		return employeeService.insert(emp);
	}
	
	@PutMapping("")
	public Employee update(@RequestBody Employee emp) {
		return employeeService.update(emp);
	}
}
