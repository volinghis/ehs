package com.ehs.common.auth.entity.entitysuper;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
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
@MappedSuperclass
public  abstract class SysRoleMenu extends BaseEntity {
	
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







}
