/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.bean 
 * @author: qjj   
 * @date: 2019年12月30日 下午4:00:18 
 */
package com.ehs.eam.eamLedgerManager.bean;

import com.ehs.common.oper.bean.PageBody;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamLedgerQueryBean.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年12月30日 下午4:00:18
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年12月30日
 *        qjj v1.0.0 修改原因
 */
public class EamLedgerQueryBean extends PageBody {

	private String query;

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

}
