package com.healthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.entity.Login;
import com.healthcare.service.LoginService;

@RestController
@RequestMapping("login")  //http://localhost:9090/login
@CrossOrigin
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping(value = "signin",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String SignIn(@RequestBody Login login) {
		return loginService.SignIn(login);
	}
	
	@PostMapping(value = "signup",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String SignUp(@RequestBody Login login) {
		return loginService.SignUp(login);
	}

}
