package com.wl.web.config.jwt.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wl.web.config.jwt.JwtUserFactory;
import com.wl.web.user.dao.WlBRoleAccountMapper;
import com.wl.web.user.model.WlBRoleAccount;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

//    @Autowired
//    private WlBAccountMapper wlBAccountMapper;

	@Autowired
	private WlBRoleAccountMapper wlBRoleAccountMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//    	User user = wlBAccountMapper.selectByUsername(username);

		WlBRoleAccount account = wlBRoleAccountMapper.selectUserWithRoles(username);

		if (account == null || null == account.getUsers()) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {

			// 权限地址列表
			List<GrantedAuthority> grantedAuthorities = account.getUrls().stream()
					.map(url -> new SimpleGrantedAuthority(url.getUrl())).collect(Collectors.toList());
			
			account.getUsers().setAuthorities(grantedAuthorities);

			return JwtUserFactory.create(account.getUsers());
		}
	}
}
