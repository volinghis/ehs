/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.utils 
 * @author: chentm   
 * @date: 2019年7月22日 下午5:34:01 
 */
package com.ehs.common.auth.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.ehs.common.auth.bean.RoleBean;
import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.entity.SysMenu;
import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.enums.RoleType;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.auth.local.bean.LocalUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: SessionBean.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年7月22日 下午5:34:01
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年7月22日
 *        chentm v1.0.0 修改原因
 */
@Component
public class SessionBean {

	private static final Logger logger = LoggerFactory.getLogger(SessionBean.class);

	@Resource
	private BaseCommonService baseCommonService;
	
	public String getSession(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(AuthConstants.SESSION_SYSUSER_KEY);
	}

	public void logout(HttpServletRequest request) {
		request.getSession().removeAttribute(AuthConstants.SESSION_SYSUSER_KEY);
	}

	public void login(String sysUserKey, HttpServletRequest request) {
		request.getSession().setAttribute(AuthConstants.SESSION_SYSUSER_KEY, sysUserKey);
	}

	public int valid(HttpServletRequest httpRequest, Method method) {
		try {
			String sessionSysUserKey = (String) httpRequest.getSession()
					.getAttribute(AuthConstants.SESSION_SYSUSER_KEY);
			if (StringUtils.isBlank(sessionSysUserKey)) {
				logger.error(BaseUtils.getIpAddress(httpRequest)+"：进行了未登录访问！");
				return AuthConstants.VALID_NO_USER_CODE;
			}
			this.login(sessionSysUserKey, httpRequest);
			LocalUser lu=new LocalUser();
			SysAccessUser.set(lu.initBySysUser(sessionSysUserKey));
			
			RequestAuth ra = method.getAnnotation(RequestAuth.class);
			if (ra == null || ra.menuKeys() == null || ra.menuKeys().length == 0) {
				logger.error(SysAccessUser.get().getSysUserKey()+":被请求方法未进行注册！");
				return AuthConstants.VALID_SERVER_ERROR;
			} 
			String resoureMenuKey = httpRequest.getParameter(AuthConstants.RESOURE_MENU_KEY);
			if (StringUtils.isBlank(resoureMenuKey)) {
				logger.error(SysAccessUser.get().getSysUserKey()+":无法识别请求！");
				return AuthConstants.VALID_CLIENT_ERROR;
			}
			if(StringUtils.isNotBlank(SysAccessUser.get().getRoleKeys())&&Arrays.asList(StringUtils.split(SysAccessUser.get().getRoleKeys(), ",")).contains(AuthConstants.ADMIN_ROLE_KEY)) {
				return AuthConstants.VALID_OK_CODE;
			}
			
			String[] menuKeys = ra.menuKeys();
			for (String s : menuKeys) {
				if(StringUtils.isNotBlank(s)) {
					if(StringUtils.equals(".*", s)) {
						return AuthConstants.VALID_OK_CODE;
					}
					Pattern p = Pattern.compile(s);
					//当前导航可以匹配到
					if(p.matcher(resoureMenuKey).matches()) {
						SysMenu sm=baseCommonService.findByKey(SysMenu.class, resoureMenuKey);
						String roles=sm.getRoles();
						//导航无角色限制
						if(StringUtils.isBlank(roles)) {
							return AuthConstants.VALID_OK_CODE;
						}
						List<RoleBean> roleList=(List<RoleBean>)JsonUtils.parseObject(roles, new TypeReference<List<RoleBean>>(){});
						if(roleList!=null&&!roleList.isEmpty()) {
							for(RoleBean rb:roleList) {
								if(RoleType.ROLE.equals(rb.getRoleType())&&StringUtils.isNotBlank(SysAccessUser.get().getRoleKeys())&&Arrays.asList(StringUtils.split(SysAccessUser.get().getRoleKeys(), ",")).contains(rb.getRoleKey())) {
										return AuthConstants.VALID_OK_CODE;
								}else if(RoleType.ORG.equals(rb.getRoleType())&&StringUtils.equals(rb.getRoleKey(),SysAccessUser.get().getOrgKey())) {
										return AuthConstants.VALID_OK_CODE;
								}else if(RoleType.SYSUSER.equals(rb.getRoleType())&&StringUtils.equals(rb.getRoleKey(),SysAccessUser.get().getSysUserKey())) {
										return AuthConstants.VALID_OK_CODE;
								}
							}
						}
					}
				}
			}
			logger.error(SysAccessUser.get().getSysUserKey()+":没有权限！");
			return AuthConstants.VALID_NO_AUTH_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return AuthConstants.VALID_EXCEPTION;
		}

	}


}
