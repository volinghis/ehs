package com.ehs.common.organization.bean;

import java.util.List;

import com.ehs.common.auth.bean.RoleBean;

public class UserRolesBean {

	private String userKey;
	
	private List<RoleBean> roleList;

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public List<RoleBean> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleBean> roleList) {
		this.roleList = roleList;
	}
	
}
