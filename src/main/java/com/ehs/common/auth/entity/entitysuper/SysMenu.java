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
* @ClassName: Menu.java
* @Description: 菜单实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年5月13日 下午6:46:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月13日     zhaol           v1.0.0               修改原因
*/
@MappedSuperclass
public  abstract  class SysMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String DATA_CODE="dataCode";
	public static final String NAME="name";
	public static final String SORT="sort";
	public static final String PARENT_KEY="parentKey";
	public static final String URL="url";
	

	public static final String ROLES="roles";
	
	
	
	/**
	 * 菜单编码
	 */
	private String dataCode;
	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单排序
	 */
	private Integer sort;
	
	/**
	 * 父菜单id
	 */
	private String parentKey;
	
	/**
	 * 菜单地址
	 */
	private String url;

	
	private String icon;
	
	
	@Column(length = 4000)
	private String roles;

	
	

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}


	

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}





	

}
