package com.global.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global.hr.entity.Role;
import com.global.hr.repository.RoleRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepo roleRepo;
	
	public Role findById(Long id) {
		return roleRepo.findById(id) // return optional if Role  not found throw an exception
				.orElseThrow(() -> new EntityNotFoundException("Role not found with id " + id));
	}
	
	public Iterable<Role> findAll() {
		return roleRepo.findAll();
	}

	
	public Role insert(Role  role) {
		return roleRepo.save(role);
	}
	
	public Role update(Role role) {
		Role  current = roleRepo.getReferenceById(role.getId());
		current.setRoleName(role.getRoleName());
		return roleRepo.save(current);
	}
	
	public Role findByName(String roleName) {
		return roleRepo.findByRoleName(roleName);
	}
}
