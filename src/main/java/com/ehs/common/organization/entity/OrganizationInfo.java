/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年5月28日 上午10:52:10 
 */
package com.ehs.common.organization.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Entity
@Table(name = "ORGANIZATION_INFO")
public class OrganizationInfo extends BaseEntity {
	public static final String DATA_CODE="dataCode";
	public static final String NAME="name";
	public static final String REMARK="remark";
	public static final String PARENT_KEY="parentKey";

	
	private static final long serialVersionUID = 1L;

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
	
	
	
	private String parentKey;
	
	


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
		return OrganizationInfo.foreignClassesList;
	}
	static {
		foreignClassesList=new LinkedList<String>();
		foreignClassesList.add(KEY+","+OrgUser.class.getSimpleName()+","+OrgUser.ORG_KEY);
		foreignClassesList.add(KEY+","+OrganizationInfo.class.getSimpleName()+","+OrganizationInfo.PARENT_KEY);
	}
	
	@Override
	public boolean equals(Object ss) {

		return true;
	}	
	
}
