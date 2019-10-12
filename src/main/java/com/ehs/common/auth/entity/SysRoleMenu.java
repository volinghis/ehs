package com.ehs.common.auth.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.common.base.entity.BaseEntity;



/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SysRoleMenu.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mapleave
* @date: 2019年5月23日 下午4:13:57 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月23日     Mapleave           v1.0.0               修改原因
*/
@Entity
@Table(name = "SYS_ROLE_MENU")
public class SysRoleMenu extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String ROLE_KEY="roleKey";
	public static final String MENU_KEY="menuKey";
	
	/**
	 * 角色编码
	 */
	private String roleKey;
	
	/**
	 * 菜单编码
	 */
	private String menuKey;

	


	




	public String getRoleKey() {
		return roleKey;
	}









	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}









	public String getMenuKey() {
		return menuKey;
	}









	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}









	/** 
	* @see com.ehs.common.base.entity.security.entity.BaseEntity#getForeignClasses()  
	* @Function: SysRoleMenu.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月4日 下午3:46:16 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月4日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<String> getForeignClasses() {
		return null;
	}

	@Override
	public boolean equals(Object ss) {

		return true;
	}	
}
