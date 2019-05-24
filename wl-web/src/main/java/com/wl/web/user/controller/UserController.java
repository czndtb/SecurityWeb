package com.wl.web.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wl.fms.common.entity.RestResult;
import com.wl.web.user.model.User;
import com.wl.web.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/info")
	public RestResult<User> info(){
		
		return RestResult.ok(userService.getUserInfo());
	}
}
