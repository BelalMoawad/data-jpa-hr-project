package com.global.hr.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.hr.entity.Role;
import com.global.hr.entity.User;
import com.global.hr.service.RoleService;
import com.global.hr.service.UserService;

@Component
public class AppStartUp implements CommandLineRunner{
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Override
	public void run(String... args) throws Exception {
		
		// create initial roles
		Role role1 = new Role();
		role1.setRoleName("Admin");
		//\\//\\z
		Role role2 = new Role();
		role2.setRoleName("User");
		
		roleService.insert(role1);
		roleService.insert(role2);
		
		// create initial users
		User user1 = new User();
		user1.setUserName("admin");
		user1.setPassword("12345");
		//\\//\\
		User user2 = new User();
		user2.setUserName("user");
		user2.setPassword("54321");
		
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(role1);
		
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role2);
		
		user1.setRoles(adminRoles);
		user2.setRoles(userRoles);
		
		userService.insert(user1);
		userService.insert(user2);
		
	}

}