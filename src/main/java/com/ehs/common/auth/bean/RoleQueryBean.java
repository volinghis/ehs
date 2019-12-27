/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.gsp.auth.role.controller.bean 
 * @author: qjj   
 * @date: 2019年10月9日 上午10:02:39 
 */
package com.ehs.common.auth.bean;

import com.ehs.common.oper.bean.PageBody;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: RoleQueryBean.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年10月9日 上午10:02:39
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年10月9日
 *        qjj v1.0.0 修改原因
 */
public class RoleQueryBean extends PageBody {

	private String query;

	private String menuKey;

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the menuKey
	 */
	public String getMenuKey() {
		return menuKey;
	}

	/**
	 * @param menuKey the menuKey to set
	 */
	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

}
