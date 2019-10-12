package com.ehs.common.auth.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.common.base.entity.BaseEntity;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: User.java
* @Description: 用户实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年5月13日 下午3:57:42 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月13日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "SYS_USER")
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String ACCOUNT="account";
	public static final String PASSWORD="password";
	public static final String STATE="state";
	public static final String USER_KEY="userKey";
	private String account;
	
	private String password;
	private String userKey;
	/**
	 * 0正常，1锁定
	 */
	private Integer state;

	
	
	
	

	public String getUserKey() {
		return userKey;
	}


	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	private static List<String> foreignClassesList=null;



	/** 
	* @see com.ehs.common.base.entity.security.entity.BaseEntity#getForeignClasses()  
	* @Function: SysRole.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月4日 下午3:01:53 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月4日      chentm           v1.0.0               修改原因
	*/
	@Override
	public List<String> getForeignClasses() {
		return SysUser.foreignClassesList;
	}


	static {
		foreignClassesList=new LinkedList<String>();
		foreignClassesList.add(KEY+","+SysUserRole.class.getSimpleName()+","+SysUserRole.SYS_USER_KEY);
	}
	
	
	@Override
	public boolean equals(Object ss) {

		return true;
	}	
}
