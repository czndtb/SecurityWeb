package com.wl.web.user.config;

import javax.validation.constraints.NotBlank;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationRequest {

	@NotBlank
	private String username;
	@NotBlank
	private String password;

	public JwtAuthenticationRequest() {
		super();
	}

	public JwtAuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
