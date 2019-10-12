/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.utils 
 * @author: chentm   
 * @date: 2019年7月22日 下午5:34:01 
 */
package com.ehs.common.auth.utils;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.stereotype.Component;

import com.ehs.common.base.utils.BaseUtils;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SessionBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月22日 下午5:34:01 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月22日      chentm          v1.0.0               修改原因
*/
@Configuration
public class SessionBean {


	
	public String getSession(HttpServletRequest request) {
		return (String)request.getSession().getAttribute(SessionConstants.SESSION_SYSUSER_KEY);
	}
	
	public void logout(HttpServletRequest request) {
		request.getSession().removeAttribute(SessionConstants.SESSION_SYSUSER_KEY);
	}
	
	public void login(String sysUserKey,HttpServletRequest request) {
		request.getSession().setAttribute(SessionConstants.SESSION_SYSUSER_KEY, sysUserKey);
	}

	


	
	public int valid(HttpServletRequest request) {
		try {
			String sessionSysUserKey=(String)request.getSession().getAttribute(SessionConstants.SESSION_SYSUSER_KEY);
			if(StringUtils.isBlank(sessionSysUserKey)) {
				return SessionConstants.VALID_NO_USER_CODE;
			}else {
				return SessionConstants.VALID_OK_CODE;
			}
		}catch (Exception e) {
			return SessionConstants.VALID_NO_USER_CODE;
		}

	}


}
