package com.ehs.common.auth.entity.entitysuper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.ehs.common.base.entity.BaseEntity;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: User.java
 * @Description: 用户实体类
 *
 * @version: v1.0.0
 * @author: zhaol
 * @date: 2019年5月13日 下午3:57:42
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年5月13日
 *        zhaol v1.0.0 修改原因
 */
@MappedSuperclass
public abstract class SysUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static final String ACCOUNT = "account";
	public static final String PASSWORD = "password";
	public static final String STATE = "state";
	public static final String USER_KEY = "userKey";
	public static final String ROLE_KEYS = "roleKeys";

	public static final String SALT = "salt";

	private String account;
	private String salt;
	private String password;
	private String userKey;
	/**
	 * 0正常，1锁定
	 */
	private Integer state;

	@Column(length = 4000)
	private String roleKeys;

	public String getRoleKeys() {
		return roleKeys;
	}

	public void setRoleKeys(String roleKeys) {
		this.roleKeys = roleKeys;
	}

	public String getUserKey() {
		return userKey;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
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

}
