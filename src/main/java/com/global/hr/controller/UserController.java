package com.global.hr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.User;
import com.global.hr.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@GetMapping("")
	public Iterable<User> findAll() {
		return userService.findAll();
	}
	
	@PostMapping("")
	public User  insert(@RequestBody User user) {
		return userService.insert(user);
	}
	
	@PutMapping("")
	public User  update(@RequestBody User  user) {
		return userService.update(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		userService.deleteById(id);
	}
	
	@DeleteMapping("")
	public void deleteAll() {
		userService.deleteAll();
	}
}
