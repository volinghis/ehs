package com.ehs.common.auth.entity.entitysuper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.ehs.common.base.entity.BaseEntity;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: Role.java
* @Description: 角色实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年5月13日 下午3:57:47 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月13日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class SysRole extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String DATA_CODE="dataCode";
	public static final String NAME="name";
	public static final String REMARK="remark";
	
	/**
	 * 角色编码
	 */
	private String dataCode;
	
	/**
	 * 角色名
	 */
	private String name;
	
	/**
	 * 备注
	 */
	private String remark;

	
	




	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}




}
