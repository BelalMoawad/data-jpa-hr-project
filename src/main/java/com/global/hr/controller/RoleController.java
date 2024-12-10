package com.global.hr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Role;
import com.global.hr.service.RoleService;


@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public Role findByName(@RequestParam String roleName) {
		return roleService.findByName(roleName);
	}
	
}
