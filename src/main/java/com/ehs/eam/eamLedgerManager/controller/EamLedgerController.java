/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.controller 
 * @author: qjj   
 * @date: 2019年12月30日 下午3:59:10 
 */
package com.ehs.eam.eamLedgerManager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.service.EamLedgerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamLedgerController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月30日 下午3:59:10 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     qjj           v1.0.0               修改原因
*/
@RestController
public class EamLedgerController {

	@Resource
	private EamLedgerService eamLedgerService;
	
	
	/**
	 * 
	* @Function:getEamLedgerList 
	* @Description: 获取所有设备记录
	* @param querybean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月31日 上午10:30:47 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月31日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {"eamLedger"})
	@RequestMapping(value = "/eam/eamLedger/getList")
	public String getEamLedgerList(@RequestBody EamLedgerQueryBean querybean,HttpServletRequest request) {
		PageInfoBean pageBean = eamLedgerService.findEamLedgerList(querybean);
		return pageBean==null?"[]":JsonUtils.toJsonString(pageBean);
	}
}
