package com.wl.web.user.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthToken {
	
	private String token;
	
	private Date tokenValidityTime;
}
