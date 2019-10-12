package com.ehs.common.auth.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
@Table(name = "SYS_MENU")
public class SysMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String DATA_CODE="dataCode";
	public static final String NAME="name";
	public static final String SORT="sort";
	public static final String PARENT_KEY="parentKey";
	public static final String URL="url";
	

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
		return SysMenu.foreignClassesList;
	}
	
	static {
		foreignClassesList=new LinkedList<String>();
		foreignClassesList.add(KEY+","+SysRoleMenu.class.getSimpleName()+","+SysRoleMenu.MENU_KEY);
		foreignClassesList.add(KEY+","+SysMenu.class.getSimpleName()+","+SysMenu.PARENT_KEY);
	}
	
	@Override
	public boolean equals(Object ss) {
		SysMenu s=(SysMenu)ss;
		if(!StringUtils.equals(this.getUrl(), s.getUrl())) {
			System.out.println("getUrl");
			return false;
		}
		if(!StringUtils.equals(this.getIcon(), s.getIcon())) {
			System.out.println("getIcon");

			return false;
		}
		if(!StringUtils.equals(this.getName(), s.getName())) {
			System.out.println("getName");
			return false;
		}
		if(!StringUtils.equals(this.getParentKey(), s.getParentKey())) {
			System.out.println("getParentKey");
			return false;
		}
		if(this.getSort().intValue()!=s.getSort().intValue()) {
			System.out.println(this.getSort());
			System.out.println(s.getSort());
			System.out.println("getSort");

			return false;
		}
		return true;
	}	
}
