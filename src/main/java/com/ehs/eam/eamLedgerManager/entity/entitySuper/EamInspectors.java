/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.entity.entitySuper 
 * @author: qjj   
 * @date: 2020年1月2日 下午3:04:57 
 */
package com.ehs.eam.eamLedgerManager.entity.entitySuper;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;
import javax.security.auth.message.callback.PrivateKeyCallback;

import org.apache.commons.lang.StringUtils;

import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamInspectors.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月2日 下午3:04:57
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年1月2日
 *        qjj v1.0.0 修改原因
 */
@MappedSuperclass
public abstract class EamInspectors extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	public static final String NAME = "name" ;
	public static final String DEPARTMENT = "department" ;
	public static final String SERVER_TIME = "serverTime" ;
	public static final String DEPARTURE_TIME = "departureTime" ;
	public static final String DEVICE_KEY = "deviceKey" ;

	private String name;

	private String department;

	/**
	 * 担任时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp serverTime;

	/**
	 * 离任时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp departureTime;
	
	
	private String devicekey;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the serverTime
	 */
	public Timestamp getServerTime() {
		return serverTime;
	}

	/**
	 * @param serverTime the serverTime to set
	 */
	public void setServerTime(Timestamp serverTime) {
		this.serverTime = serverTime;
	}

	/**
	 * @return the departureTime
	 */
	public Timestamp getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the devicekey
	 */
	public String getDevicekey() {
		return devicekey;
	}

	/**
	 * @param devicekey the devicekey to set
	 */
	public void setDevicekey(String devicekey) {
		this.devicekey = devicekey;
	}
	
}
