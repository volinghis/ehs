/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年5月28日 上午10:52:10 
 */
package com.ehs.common.organization.entity.entitysuper;

import javax.persistence.MappedSuperclass;
import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationInfo.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 上午10:52:10 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class OrganizationInfo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String DATA_CODE="dataCode";
	public static final String NAME="name";
	public static final String REMARK="remark";
	public static final String PARENT_KEY="parentKey";
	public static final String SORT="sort";

	/**
	 * 组织编码
	 */
	private String dataCode;

	/**
	 * 组织名称
	 */
	private String name;
	
	/**
	 * 备注说明
	 */
	private String remark;

	/**
	 * 父类key
	 */
	private String parentKey;
	
	/**
	 * 排序
	 */
	private Integer sort;

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

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
