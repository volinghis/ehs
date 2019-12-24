package com.ehs.common.organization.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.common.organization.bean.UserQueryBean;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.service.OrgUserService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrgUserController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月19日 下午7:10:07 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月19日     zhaol           v1.0.0               修改原因
*/
@RestController
public class OrgUserController {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private OrgUserService orgUserService;
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:10:19 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/addUser")
	public String addOrgUser(HttpServletRequest request) {
		String orgKey = request.getParameter("orgkey");
		System.out.println("orgkey============="+orgKey);
		return null;
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 查询所有用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:10:14 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/getAllUser")
	public String getAll(@RequestBody(required = false) UserQueryBean userQueryBean,HttpServletRequest request) {
		PageInfoBean pb = orgUserService.getAllUser(userQueryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 根据部门查询用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:10:39 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/findUserByOrgKey")
	@ResponseBody
	public String findUserByOrgKey(HttpServletRequest request,UserQueryBean queryBean) {
		String orgKey=request.getParameter("orgKey");
		String searchData=request.getParameter("searchData");
		UserQueryBean uq= new UserQueryBean();
		if (StringUtils.isNotBlank(searchData)) {
			uq = (UserQueryBean) JsonUtils.parseObject(searchData, UserQueryBean.class);
		}
		PageInfoBean users=orgUserService.findUserByOrgKey(orgKey,queryBean,uq);
		System.out.println("JsonUtils.toJsonString(users)==="+JsonUtils.toJsonString(users));
		return (users == null ? "[]" : JsonUtils.toJsonString(users));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 保存用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:11:21 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/saveOrgUser")
	public String saveOrgUser(@RequestBody OrgUser orgUser,HttpServletRequest request, HttpServletResponse response) {
		ResultBean resultBean=new ResultBean();
		List<OrgUser> users= (List<OrgUser>)baseCommonService.findAll(OrgUser.class);
		if (users!=null&&users.size()>0) {
			long c=users.stream().filter(s->StringUtils.equals(s.getDataCode(),orgUser.getDataCode())&&!StringUtils.equals(s.getKey(), orgUser.getKey())).count();
			if(c>0) {
				return JsonUtils.toJsonString(resultBean.error("保存用户失败:已存在相同用户编号"));
			}
		}
		OrgUser user = orgUserService.saveUser(orgUser);
		return JsonUtils.toJsonString(resultBean.ok("保存角色成功",user.getKey()));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 改变状态：停用或者启用
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:11:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/changeState")
	@ResponseBody
	public String changeState(@RequestBody OrgUser orgUser,HttpServletRequest request) {
		OrgUser user =orgUserService.changeState(orgUser);
		return JsonUtils.toJsonString(user);
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 删除用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午7:11:48 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/deleteOrgUser")
	public String deleteOrgUser(HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			String key=request.getParameter("key");
			orgUserService.deleteOrgUser(key);
			return JsonUtils.toJsonString(resultBean.ok("用户删除成功"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("用户删除失败"));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:24:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/findAllRolesByUserKey")
	public String findAllRolesByUserKey(HttpServletRequest request) {
		System.out.println("根据用户查找角色");
		List<SysRole> allRoles=(List<SysRole>)baseCommonService.findAll(SysRole.class);
		if(allRoles==null||allRoles.isEmpty()) {
			return "[]";
		}
		String userKey=request.getParameter("userKey");
		System.out.println("userKey============"+userKey);
		List<SysRole> roles=orgUserService.findRolesByUserKey(userKey);
		if(roles==null||roles.isEmpty()) {
			List<SysRole> roleList=allRoles.stream().filter(s->(!StringUtils.equals(s.getKey(), "sysAdminRoleKey"))).collect(Collectors.toList());
			return JsonUtils.toJsonString(roleList);
		}
		return JsonUtils.toJsonString(allRoles.stream().filter(s->roles.stream().allMatch(ss->(!StringUtils.equals(s.getKey(), ss.getKey()))&&(!StringUtils.equals(s.getKey(), "sysAdminRoleKey")))).collect(Collectors.toList()));
	}
}
