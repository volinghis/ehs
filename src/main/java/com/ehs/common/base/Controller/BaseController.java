package com.ehs.common.base.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.utils.JsonUtils;

@Controller
public class BaseController {

	/**
	 * 
	* @Function: BaseController.java
	* @Description: 获取当前登陆信息
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年12月12日 下午4:31:34 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月12日     Administrator           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/base/user/getCurrentUser")
	@ResponseBody
	public String getMenu(HttpServletRequest request,HttpServletResponse response) {
		return JsonUtils.toJsonString(SysAccessUser.get());
	}
}
