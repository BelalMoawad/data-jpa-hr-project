package com.global.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global.hr.entity.Department;
import com.global.hr.repository.DepartmentRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	public Department findById(Long id) {
		return departmentRepo.findById(id) // return optional if Department  not found throw an exception
				.orElseThrow(() -> new EntityNotFoundException("Department not found with id " + id));
	}
	
	public Iterable<Department> findAll() {
		return departmentRepo.findAll();
	}

	
	public Department  insert(Department  dept) {
		return departmentRepo.save(dept);
	}
	
	public Department  update(Department  dept) {
		Department  current = departmentRepo.getReferenceById(dept.getId());
		current.setName(dept.getName());
		return departmentRepo.save(current);
	}
	
	public void deleteById(Long id) {
		departmentRepo.deleteById(id);
	}

	public void deleteAll() {
		departmentRepo.deleteAll();
	}
}
