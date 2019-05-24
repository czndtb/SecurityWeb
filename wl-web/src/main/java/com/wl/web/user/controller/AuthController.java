package com.wl.web.user.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wl.fms.common.entity.ErrorCode;
import com.wl.fms.common.entity.RestResult;
import com.wl.web.config.jwt.JwtTokenUtil;
import com.wl.web.config.security.AuthenticationException;
import com.wl.web.user.config.JwtAuthenticationRequest;
import com.wl.web.user.model.AuthToken;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("databaseUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
	public RestResult<Object> createAuthenticationToken(
			@Valid @RequestBody JwtAuthenticationRequest authenticationRequest, BindingResult bindingResult) {

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

//			 Reload password post-security so we can generate the token
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final AuthToken token = jwtTokenUtil.generateToken(userDetails);

			return RestResult.ok(token);

		} catch (AuthenticationException e) {
			return RestResult.error(ErrorCode.ERROR_USERORPSW_ERROR);
		}

	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public RestResult<String> logout() {
		return RestResult.OK_EMPTY_MSG;
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("User is disabled!", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("Bad credentials!", e);
		}
	}
}
