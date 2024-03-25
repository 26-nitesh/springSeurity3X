package com.ems.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmpController {

	@GetMapping("/home")
	public String test() {
		return "HOME PAGE";
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MASTER')")
	public String test2() {
		return "ADMIN PAGE";
	}
	@Secured({"ROLE_USER","ROLE_MASTER"})
	@GetMapping("/user")
	public String user() {
		return "USER PAGE";
	}
	
	@GetMapping("/secured")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MASTER')")
	public String sec() {
		return "SECURED PAGE FOR SOME";
	}
}
