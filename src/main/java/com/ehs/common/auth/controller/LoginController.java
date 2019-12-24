/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.login.Controller 
 * @author: qjj   
 * @date: 2019年12月10日 下午5:21:06 
 */
package com.ehs.common.auth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ehs.common.auth.bean.LoginInfoBean;
import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.service.LoginService;
import com.ehs.common.auth.utils.SessionBean;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.ResultBean;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: loginController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年12月10日 下午5:21:06
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年12月10日
 *        qjj v1.0.0 修改原因
 */
@RestController
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	@Resource
	private SessionBean sessionBean;

	/**
	 * 
	* @Function:doLogin 
	* @Description: 处理登录
	* @param loginInfo
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月11日 上午10:35:17 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月11日     qjj        v1.0.0            修改原因
	 */
	@RequestMapping(value = "/auth/login/doLogin")
	public String doLogin(@RequestBody LoginInfoBean loginInfo, HttpServletRequest request) {
		String account=loginInfo.getAccount();
		SysUser sysUser=loginService.findByAccount(account);
		ResultBean resultBean=new ResultBean();
		if(sysUser==null) {
			return JsonUtils.toJsonString(resultBean.error("用户不存在"));
		}
//		if(!StringUtils.equals(BaseUtils.string2MD5(loginInfo.getAccount()+loginInfo.getPassword()), sysUser.getPassword())) {
		if(!StringUtils.equals(BaseUtils.string2MD5(loginInfo.getAccount()+loginInfo.getPassword()+sysUser.getSalt()), sysUser.getPassword())) {
			return JsonUtils.toJsonString(resultBean.error("密码错误"));
		}
		if (sysUser.getState()!=null&&sysUser.getState()==1) {
				return JsonUtils.toJsonString(resultBean.error("用户已被锁定"));
		}
		sessionBean.login(sysUser.getKey(), request);
		return JsonUtils.toJsonString(resultBean.ok("认证成功"));
		
	}
	
	/**
	 * 
	* @Function:doLogout 
	* @Description: 退出
	* @param request
	* @param response
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月11日 上午11:21:30 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月11日     qjj        v1.0.0            修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/auth/login/doLogout")
	public String doLogout(HttpServletRequest request, HttpServletResponse response) {
		sessionBean.logout(request);
		//LoginResultBean loginResultBean=new LoginResultBean();
		ResultBean resultBean=new ResultBean();
		return JsonUtils.toJsonString(resultBean.ok("退出成功"));
	}

}
