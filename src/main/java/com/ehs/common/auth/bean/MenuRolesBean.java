package com.ehs.common.auth.bean;

import java.util.List;

public class MenuRolesBean {

	private String menuKey;
	
	private List<RoleBean> roleList;

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	/**
	 * @return the roleList
	 */
	public List<RoleBean> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<RoleBean> roleList) {
		this.roleList = roleList;
	}

	
	
	
}
