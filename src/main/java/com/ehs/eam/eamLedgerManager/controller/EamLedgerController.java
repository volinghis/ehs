/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.controller 
 * @author: qjj   
 * @date: 2019年12月30日 下午3:59:10 
 */
package com.ehs.eam.eamLedgerManager.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamRequestBean;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamParameters;
import com.ehs.eam.eamLedgerManager.service.EamLedgerService;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: EamLedgerController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年12月30日 下午3:59:10
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年12月30日
 *        qjj v1.0.0 修改原因
 */
@RequestMapping("/eam/eamLedger")
@RestController
public class EamLedgerController {

	@Resource
	private EamLedgerService eamLedgerService;

	@Resource
	private BaseCommonService baseCommonService;

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
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月31日
	 *        qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/getList")
	public String getEamLedgerList(@RequestBody EamLedgerQueryBean querybean, HttpServletRequest request) {
		PageInfoBean pageBean = eamLedgerService.findEamLedgerList(querybean);
		return pageBean == null ? "[]" : JsonUtils.toJsonString(pageBean);
	}

	/**
	 * 
	 * @Function:saveEamLedger
	 * @Description: 设备台账保存或修改
	 * @param eamLedger
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2019年12月31日 下午12:00:14
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月31日
	 *        qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/saveEamLedger")
	public String saveEamLedger(HttpServletRequest request, @RequestBody EamRequestBean eamRequestBean) {
		ResultBean resultBean = new ResultBean();
		System.out.println("设备台账保存请求参数=============================" + JsonUtils.toJsonString(eamRequestBean));
		eamLedgerService.saveEamLedger(eamRequestBean);
//		List<EamAccountPrint> roleInfoList=	(List<EamAccountPrint>)baseCommonService.findAll(EamAccountPrint.class);
//		if (roleInfoList!=null&&roleInfoList.size()>0) {
//			long c=roleInfoList.stream().filter(s->StringUtils.equals(s.getDeviceName(),eamAccountPrint.getDeviceName())&&!StringUtils.equals(s.getDeviceNum(), eamAccountPrint.getDeviceNum())).count();
//			if(c>0) {
//				return JsonUtils.toJsonString(resultBean.error("保存失败:已存在相同设备编号或设备名称"));
//			}
//		}
		return JsonUtils.toJsonString(resultBean.ok("保存成功"));
	}

	@RequestAuth(menuKeys = { "eamLedger" })
	@RequestMapping(value = "/getEamParamsByKey")
	public String getEamParamsByKey(HttpServletRequest request, @RequestParam String key) {
		List<EamParameters> eamParameters = eamLedgerService.getEamParametersByKey(key);
		return eamParameters != null && eamParameters.size() > 0 ? JsonUtils.toJsonString(eamParameters) : "[]";
	}
}
