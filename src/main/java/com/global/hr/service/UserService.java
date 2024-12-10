package com.global.hr.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.hr.entity.Role;
import com.global.hr.entity.User;
import com.global.hr.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleService roleService;
	
	public User findById(Long id) {
		return userRepo.findById(id) // return optional if User  not found throw an exception
				.orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
	}
	
	public ArrayList<User> findAll() {
		return (ArrayList<User>) userRepo.findAll();
	}

	
	public User insert(User  user) {
		return userRepo.save(user);
	}
	
	public User update(User  user) {
		User  current = userRepo.getReferenceById(user.getId());
		current.setUserName(user.getUserName());
		current.setPassword(user.getPassword());
		return userRepo.save(current);
	}
	
	@Transactional
	public void addRoleForAllUsers(String roleName) {
		Role role = roleService.findByName(roleName);
		
		userRepo.findAll().forEach( user -> {
			user.addRole(role);
			userRepo.save(user);
		});
	}
	
	
	
	
	
	public void deleteById(Long id) {
		userRepo.deleteById(id);
	}

	public void deleteAll() {
		userRepo.deleteAll();
	}
}
