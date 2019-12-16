package com.ehs.common.auth.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.common.auth.entity.entitysuper.SysRoleMenu;
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
@Table(name = "SYS_ROLE_MENU_HIS")
public class SysRoleMenuHis extends SysRoleMenu {
	
	private static final long serialVersionUID = 1L;
	
	
}
