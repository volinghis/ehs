package com.ehs.common.organization.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.common.organization.bean.OrganizationBean;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.common.organization.service.OrganizationService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月13日 上午9:06:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月13日     zhaol           v1.0.0               修改原因
*/
@Controller
public class OrganizationController {
	
	@Resource
	private OrganizationService organizationService;
	@Resource
	private BaseCommonService baseCommonService;
	
	private ResultBean resultBean=new ResultBean();
	/**
	 * 
	* @Function: OrganizationController.java
	* @Description: 查询所有组织
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月13日 上午9:06:58 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月13日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/auth/organization/getAll")
	@ResponseBody
	public String findAllOrg(HttpServletRequest request, HttpServletResponse response) {
		List<OrganizationInfo> orgList =(List<OrganizationInfo>)baseCommonService.findAll(OrganizationInfo.class);
		if (orgList == null || orgList.isEmpty()) {
			return "[]";
		}
		if (orgList.size()> 1) {
			orgList.sort((a, b) -> a.getSort() - b.getSort());
		}
		List<OrganizationBean> orgs = new ArrayList<OrganizationBean>();
		createOrg(orgs, orgList, null);
		return JsonUtils.toJsonString(orgs);
	}
	
	/**
	 * 
	* @Function: OrganizationController.java
	* @Description: 组织递归
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月13日 上午9:07:18 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月13日     zhaol           v1.0.0               修改原因
	 */
	private void createOrg(List<OrganizationBean> orgBeans,List<OrganizationInfo> orgs,String parentkey) {
		orgs.stream().filter(s->StringUtils.equals(s.getParentKey(),parentkey)).forEach(c->{
			OrganizationBean orgBean=new OrganizationBean();
			orgBean.setId(c.getKey());
			orgBean.setLabel(c.getName());
			orgBean.setParentId(c.getParentKey());
			List ll=new ArrayList();
			createOrg(ll,orgs,c.getDataCode());
			if(ll.size()>0) {
				orgBean.setChildren(ll);
			}
			orgBeans.add(orgBean);
		});
	}
	
	/**
	 * 
	* @Function: OrganizationController.java
	* @Description: 保存
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月13日 下午7:34:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月13日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {AuthConstants.ADMIN_ROLE_KEY})
	@RequestMapping(value = "/auth/organization/saveOrg")
	@ResponseBody
	public String saveOrg(@RequestBody OrganizationBean orgBean, HttpServletRequest request) {
	 	OrganizationInfo org = organizationService.saveOrg(orgBean);
		return JsonUtils.toJsonString(resultBean.ok("认证成功",org.getKey()));
	}

}
