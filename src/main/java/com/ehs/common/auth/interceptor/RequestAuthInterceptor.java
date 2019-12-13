package com.ehs.common.auth.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.auth.local.bean.LocalUser;
import com.ehs.common.auth.utils.SessionBean;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.SpringUtils;

public class RequestAuthInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler)
			throws Exception {
		SysAccessUser.remove();
		SessionBean sessionBean=SpringUtils.getBean(SessionBean.class);
		HandlerMethod handlerMethod = (HandlerMethod) handler;      
		Method method = handlerMethod.getMethod();   
		int statusCode=sessionBean.valid(httpRequest,method);
		if(statusCode!=AuthConstants.VALID_OK_CODE) {
			httpResponse.setStatus(statusCode);
			return false;
		}
		return true;
	}

}
