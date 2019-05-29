package com.wl.web.user.model;

import java.util.List;

public class WlBRoleAccount {
	private Long id;

	private Long userId;

	private Long roleId;

	private User users;

	private WlBRole roles;
	
	private List<WlBRoleUrl> urls;
	
	private List<WlBAccess> accesses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public WlBRole getRoles() {
		return roles;
	}

	public void setRoles(WlBRole roles) {
		this.roles = roles;
	}

	public List<WlBRoleUrl> getUrls() {
		return urls;
	}

	public void setUrls(List<WlBRoleUrl> urls) {
		this.urls = urls;
	}

	public List<WlBAccess> getAccesses() {
		return accesses;
	}

	public void setAccesses(List<WlBAccess> accesses) {
		this.accesses = accesses;
	}

}