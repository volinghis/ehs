package com.ehs.common.organization.bean;

import java.io.Serializable;

import com.ehs.common.auth.enums.RoleType;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月23日 下午7:24:02 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月23日     zhaol           v1.0.0               修改原因
*/
public class UserBean implements Serializable{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	/**
	 * 角色Key
	 */
	private String roleKey;

	/**
	 * 角色类型
	 */
	private RoleType roleType;

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleKey == null) ? 0 : roleKey.hashCode());
		result = prime * result + ((roleType == null) ? 0 : roleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (roleKey == null) {
			if (other.roleKey != null)
				return false;
		} else if (!roleKey.equals(other.roleKey))
			return false;
		if (roleType != other.roleType)
			return false;
		return true;
	}

}
