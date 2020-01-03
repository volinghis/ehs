/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.entity.entitySuper 
 * @author: qjj   
 * @date: 2020年1月2日 下午1:35:54 
 */
package com.ehs.eam.eamLedgerManager.entity.entitySuper;

import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;

/**
 * Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamParameters.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2020年1月2日 下午1:35:54
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2020年1月2日
 *        qjj v1.0.0 修改原因
 */
@MappedSuperclass
public abstract class EamParameters extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

	public static final String PARAM_NAME = "paramName";
	public static final String PARAM_VALUE = "paramValue";
	public static final String REMARK = "remark";
	public static final String DEVICE_KEY = "deviceKey";

	private String paramName;

	private String paramValue;

	private String deviceKey;

	private String remark;

	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * @return the paramValue
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 * @return the deviceKey
	 */
	public String getDeviceKey() {
		return deviceKey;
	}

	/**
	 * @param deviceKey the deviceKey to set
	 */
	public void setDeviceKey(String deviceKey) {
		this.deviceKey = deviceKey;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
