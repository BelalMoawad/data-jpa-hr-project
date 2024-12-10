package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<?> findByDepartmentIdJPQL(@PathVariable Long deptId, @RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String sortCol, @RequestParam Boolean isAsc) {
		return ResponseEntity.ok(employeeService.findByDepartmentIdJPQL(deptId, pageNum, pageSize, sortCol, isAsc));
	}
	
	@GetMapping("/department/{deptId}")
	public List<Employee> findByDepartmentId(@PathVariable Long deptId) {
		return employeeService.findByDepartmentId(deptId);
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {
		return employeeService.findById(id);
/*	
		// Using Data Transfer Object to prevent lazy-loading issues
		// Map Employee to EmployeeDTO
		Employee employee = employeeService.findById(id);
		EmployeeDTO response = new EmployeeDTO();
		response.setId(employee.getId());
		response.setName(employee.getName());
		response.setSalary(employee.getSalary());
		
		// Map Department to DepartmentDTO
	    Department department = employee.getDepartment();
	    if (department != null) {
	        DepartmentDTO departmentDto = new DepartmentDTO();
	        departmentDto.setId(department.getId());
	        departmentDto.setName(department.getName());
	        response.setDepartmentDTO(departmentDto);
	    }
	    
	 // Map User to UserDTO
	    User user = employee.getUser();
	    if (user != null) {
	        UserDTO userDto = new UserDTO();
	        userDto.setId(user.getId());
	        userDto.setUserName(user.getUserName());
	        userDto.setPassword(user.getPassword());
	        response.setUserDTO(userDto);
	    }
	    
		return response;
*/
	}
	
	@PostMapping("")
	public Employee insert(@RequestBody Employee emp) {
		return employeeService.insert(emp);
	}
	
	@PutMapping("")
	public Employee update(@RequestBody Employee emp) {
		return employeeService.update(emp);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		employeeService.deleteById(id);
	}
	
	@DeleteMapping("")
	public void deleteAll() {
		employeeService.deleteAll();
	}
	
	@GetMapping("/emp_dept")
	public List<Employee> findByEmpDept(@RequestParam String empName, @RequestParam String deptName) {
		return employeeService.findByEmpDept(empName, deptName);
	}
	
	@GetMapping("/count_emp_dept")
	public ResponseEntity<?> countByEmpDept(@RequestParam String empName, @RequestParam String deptName) {
		return ResponseEntity.ok(employeeService.countByEmpDept(empName, deptName));
	}
	
	@DeleteMapping("/emp_dept")
	public ResponseEntity<?> deleteByEmpDept(@RequestParam String empName, @RequestParam String deptName) {
		return ResponseEntity.ok(employeeService.deleteByEmpDept(empName, deptName));
	}
	
	@GetMapping("/salary")
	public ResponseEntity<?> findBySalary(@RequestParam Double empSalary) {
		return ResponseEntity.ok(employeeService.findBySalary(empSalary));
	}
	
	@GetMapping("/hr_statistics")
	public ResponseEntity<?> getHRStatistics() {
		return ResponseEntity.ok(employeeService.getHRStatistics());
	}
}
