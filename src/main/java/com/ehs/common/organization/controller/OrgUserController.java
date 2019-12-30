package com.ehs.common.organization.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.bean.RoleQueryBean;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.common.organization.bean.UserQueryBean;
import com.ehs.common.organization.bean.UserRolesBean;
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
//		List<OrgUser> users= (List<OrgUser>)baseCommonService.findAll(OrgUser.class);
//		if (users!=null&&users.size()>0) {
//			long c=users.stream().filter(s->StringUtils.equals(s.getDataCode(),orgUser.getDataCode())&&!StringUtils.equals(s.getKey(), orgUser.getKey())).count();
//			if(c>0) {
//				return JsonUtils.toJsonString(resultBean.error("保存用户失败:已存在相同用户编号"));
//			}
//		}
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
	* @Description: 根据用户获取角色
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午10:10:06 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/findUserRoles")
	@ResponseBody
	public String findUserRoles(HttpServletRequest request, HttpServletResponse response) {
		String userKey=request.getParameter("userKey");
		return JsonUtils.toJsonString(orgUserService.findRolesByUserKey(userKey));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 根据用户查询角色
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
		String userKey=request.getParameter("userKey");
		String query=request.getParameter("query");
		RoleQueryBean queryBean =(RoleQueryBean) JsonUtils.parseObject(query, RoleQueryBean.class);
		PageInfoBean pb = orgUserService.findAllRolesByUserKey(userKey,queryBean);
		return (pb==null?"[]":JsonUtils.toJsonString(pb));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 添加用户角色
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月25日 下午4:13:32 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月25日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/saveUserRole")
	@ResponseBody
	public String saveUserRoles(@RequestBody UserRolesBean userRolesBean, HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			orgUserService.saveUserRole(userRolesBean);
			return JsonUtils.toJsonString(resultBean.ok("授权成功！",""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("授权失败！"));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 删除用户角色
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月25日 下午4:13:13 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月25日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/deleteUserRole")
	@ResponseBody
	public String deleteUserRole(@RequestBody UserRolesBean userRolesBean, HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		try {
			orgUserService.deleteUserRole(userRolesBean);
			return JsonUtils.toJsonString(resultBean.ok("删除成功！",""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonUtils.toJsonString(resultBean.error("删除失败！"));
	}
	
	/**
	 * 
	* @Function: OrgUserController.java
	* @Description: 验证账号是否唯一
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月25日 下午4:12:53 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月25日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/userValidation")
	@ResponseBody
	public String userValidation(HttpServletRequest request) {
		ResultBean resultBean=new ResultBean();
		String dataCode = request.getParameter("dataCode");
		String key = request.getParameter("key");
		List<OrgUser> users = (List<OrgUser>) baseCommonService.findAll(OrgUser.class);
		long count=users.stream().filter((e)->e.getDataCode().equals(dataCode)).count();
		if (!StringUtils.isBlank(key)) {
			OrgUser user=(OrgUser)baseCommonService.findByKey(OrgUser.class, key);
			if(user != null) {
				System.out.println("user===dataCode==="+user.getDataCode());
				if(StringUtils.equals(dataCode, user.getDataCode())) {
					return JsonUtils.toJsonString(resultBean.ok("该工号尚未修改"));
				}else {
					if(!dataCode.equals(user.getDataCode()) && count > 0l) {
						return JsonUtils.toJsonString(resultBean.error("该员工工号已经存在，请重新确认"));
					}
				}
			}
		}
		if (count >= 1l) {
			return JsonUtils.toJsonString(resultBean.error("该员工工号已经存在，请重新确认"));
		}
		return JsonUtils.toJsonString(resultBean.ok("该工号可以使用"));
	}
	
	/**
	 * 
	* @Function:findOrgUserByAccount 
	* @Description: 根据账号或dataCode 获取当前用户
	* @param request
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月27日 下午4:01:50 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月27日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/orgUser/findOrgUserByAccount")
	public String findOrgUserByAccount(HttpServletRequest request) {
		String account=request.getParameter("account");
		OrgUser orgUser=orgUserService.findOrgUserBySysUserKey(account);
		return orgUser==null?"{}":JsonUtils.toJsonString(orgUser);
	}
}
