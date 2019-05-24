package com.wl.web.user.model;

public class WlBRole {
	private Integer id;

	private String roleName;

	private String roleComment;

	private String operatorCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getRoleComment() {
		return roleComment;
	}

	public void setRoleComment(String roleComment) {
		this.roleComment = roleComment == null ? null : roleComment.trim();
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode == null ? null : operatorCode.trim();
	}

}