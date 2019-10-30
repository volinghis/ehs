package com.ehs.common.auth.entity.entitysuper;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;



/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: LoginLogEntity.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年6月18日 下午4:24:42 
*
* Modification History:
* Date          Author          Version            Description
*---------------------------------------------------------*
* 2019年6月18日    qjj           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class SysLoginLog extends BaseEntity  {
	
	private static final long serialVersionUID = 1L;
	
	public static final String NAME="name";
	public static final String ACCOUNT="account";
	public static final String TIME="time";
	public static final String IP="ip";
	
	/**
	 * 	用户名
	 */
	private String name;
	/**
	 * 	登录账号
	 */
	private String account ;
	/**
	 * 	登录时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Timestamp time;
	/**
	 * 	ip地址
	 */
	private String ip;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	
	
	
	
}
