package com.ehs.eam.eamPartLibraryManager.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;
import com.ehs.eam.eamPartLibraryManager.service.PartsAccountService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibraryController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午4:11:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/
@RestController
public class PartsAccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(PartsAccountController.class);
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private PartsAccountService eamPartLibraryService;
	
	@RequestAuth(menuKeys = {"partsAccount"})
	@RequestMapping(value = "/eam/partsAccount/savePartsAccount")
	public String savePartsAccount(@RequestBody PartsAccount partsAccount, HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入saveEamPartLibrary方法=============");
		ResultBean resultBean=new ResultBean();
		List<PartsAccount> partsAccounts = (List<PartsAccount>)baseCommonService.findAll(PartsAccount.class);
		if(partsAccounts !=null && partsAccounts.size() > 0) {
			long c = partsAccounts.stream().filter(s->StringUtils.equals(s.getDeviceCode(),partsAccount.getDeviceCode()) && !StringUtils.equals(s.getKey(), partsAccount.getKey())).count();
			if(c>0) {
				return JsonUtils.toJsonString(resultBean.error("保存数据失败:已存在相同编号"));
			}
		}
		if (partsAccount != null) {
			PartsAccount pa = eamPartLibraryService.saveOrUpdateEamPart(partsAccount);
			logger.info("===========退出saveEamPartLibrary方法=============");
			return JsonUtils.toJsonString(resultBean.ok("保存成功！",pa.getKey()));
		}
		return JsonUtils.toJsonString(resultBean.error("保存数据失败"));
	}
	
	@RequestAuth(menuKeys = {"partsAccount"})
	@RequestMapping(value = "/eam/partsAccount/getPartsAccountAll")
	public String getPartsAccountAll(QueryBean queryBean,HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入getPartsAccountAll方法=============");
		PageInfoBean pb = eamPartLibraryService.findPartsAccountAll(queryBean);
		System.out.println("pb==============="+JsonUtils.toJsonString(pb));
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	@RequestAuth(menuKeys = {"partsAccount"})
	@RequestMapping(value = "/eam/partsAccount/getPartsAccount")
	public String getEamPartLibrary(HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入getEamPartLibrary方法=============");
		String key=request.getParameter("key");
		PartsAccount partsAccount= baseCommonService.findByKey(PartsAccount.class, key);
		return partsAccount==null?"{}":JsonUtils.toJsonString(partsAccount);
	}
	
	@RequestAuth(menuKeys = {"partsAccount"})
	@RequestMapping(value = "/eam/partsAccount/deletePartsAccount")
	public String deletePartsAccount(HttpServletRequest request,HttpServletResponse response) {
		logger.info("===========进入deleteEamPartLibrary方法=============");
		ResultBean resultBean=new ResultBean();
		String key=request.getParameter("key");
		baseCommonService.deleteByKey(PartsAccount.class, key);
		return JsonUtils.toJsonString(resultBean.ok("删除备件成功！"));
	}

}
