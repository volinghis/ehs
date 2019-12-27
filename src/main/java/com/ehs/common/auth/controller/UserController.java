/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.controller 
 * @author: qjj   
 * @date: 2019年12月24日 上午11:25:13 
 */
package com.ehs.common.auth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.bean.PassWordBean;
import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.service.UserService;
import com.ehs.common.auth.utils.SessionBean;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.ResultBean;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: UserController.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年12月24日 上午11:25:13
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年12月24日
 *        qjj v1.0.0 修改原因
 */
@RestController
public class UserController {

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private SessionBean sessionBean;

	@Resource
	private UserService userService;

	/**
	 * 
	 * @Function:changPassword
	 * @Description: 修改密码
	 * @param request
	 * @return
	 * @throws：异常描述
	 * @version: v1.0.0
	 * @author: qjj
	 * @date: 2019年12月24日 上午9:35:20
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月24日
	 *        qjj v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/auth/userManager/changPassword")
	public String changPassword(HttpServletRequest request, @RequestBody PassWordBean passWordBean) {
		ResultBean resultBean = new ResultBean();
		String oldPwd = passWordBean.getPass();
		// 当前用户
		SysUser sysUser = baseCommonService.findByKey(SysUser.class, sessionBean.getSession(request));
		oldPwd = BaseUtils.string2MD5(sysUser.getAccount() + oldPwd + sysUser.getSalt());
		if (StringUtils.equals(sysUser.getPassword(), oldPwd)) {
			if (userService.changePassWord(sysUser, passWordBean.getNewPass())) {
				return JsonUtils.toJsonString(resultBean.ok("密码修改成功"));
			}
		}
		return JsonUtils.toJsonString(resultBean.error("用户密码输入错误"));
	}
}
