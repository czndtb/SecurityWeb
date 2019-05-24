package com.wl.web.user.model;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class User {
	private Long id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String avatar;
	private String language;
	private Integer state;
	private String operatorCode;
	private Date createTime;
	private Date updateTime;
	private String[] roles = { "super_admin", "admin" };// 默认写admin 具体路由信息通过更改路由表控制
	private List<GrantedAuthority> authorities;
}
