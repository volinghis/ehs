package com.ehs.common.auth.entity.entitysuper;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.ehs.common.base.entity.BaseEntity;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserRole.java
* @Description: 用户角色实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年5月13日 下午5:23:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月13日     zhaol           v1.0.0               修改原因
*/
/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SysUserRole.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: Mapleave
* @date: 2019年5月23日 下午5:03:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月23日     Mapleave           v1.0.0               修改原因
*/
@MappedSuperclass
public  abstract class SysUserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;
	public static final String SYS_USER_KEY="sysUserKey";
	public static final String ROLE_KEY="roleKey";
	
	
	private String roleKey;

	private String sysUserKey;
	


	


	public String getRoleKey() {
		return roleKey;
	}






	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}






	public String getSysUserKey() {
		return sysUserKey;
	}






	public void setSysUserKey(String sysUserKey) {
		this.sysUserKey = sysUserKey;
	}






	

	
}
