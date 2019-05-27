package com.wl.web.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.web.config.jwt.JwtUser;
import com.wl.web.user.model.User;
import com.wl.web.user.service.UserService;
import com.wl.web.util.ParamTool;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ParamTool pt;

	@Override
	public User getUserInfo() {
		User user = new User();
		JwtUser jwtUser = pt.getJwtUser();

		if (null != jwtUser) {
			BeanUtils.copyProperties(jwtUser, user);
			
			if (null != jwtUser.getAuthorities()) {
				List<String> access = jwtUser.getAuthorities().stream().map(authoritiy -> authoritiy.getAuthority()).collect(Collectors.toList());
				user.setAccess(access);
			}
		}

		return user;
	}

}
