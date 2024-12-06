package com.global.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global.hr.entity.User;
import com.global.hr.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public User findById(Long id) {
		return userRepo.findById(id) // return optional if User  not found throw an exception
				.orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
	}
	
	public Iterable<User> findAll() {
		return userRepo.findAll();
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
}
