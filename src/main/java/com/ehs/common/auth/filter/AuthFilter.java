package com.ehs.common.auth.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.env.Environment;

import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.auth.local.bean.LocalUser;
import com.ehs.common.auth.utils.SessionBean;
import com.ehs.common.base.utils.SpringUtils;

@Deprecated
public class AuthFilter implements Filter{

	private List<String> exculusionsList;

	
	@Override
    public void init(FilterConfig config) throws ServletException {
		String exclusions=config.getInitParameter("exclusions");
		if(StringUtils.isNotBlank(exclusions)) {
			exculusionsList= Arrays.asList(StringUtils.split(exclusions, ","));
		}
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		HttpServletRequest httpRequest=(HttpServletRequest)request;
//		HttpServletResponse httpResponse=(HttpServletResponse)response;
//		
//		if(matchs(httpRequest.getServletPath())) {
//			chain.doFilter(httpRequest, response);
//			return ;
//		}
//		
//		SessionBean sessionBean=SpringUtils.getBean(SessionBean.class);
//		int statusCode=sessionBean.valid(httpRequest);
//		if(statusCode!=SessionConstants.VALID_OK_CODE) {
//			httpResponse.setStatus(statusCode);
//		}else {
//			String sysUserKey=(String)httpRequest.getSession().getAttribute(SessionConstants.SESSION_SYSUSER_KEY);
//			LocalUser lu=new LocalUser();
//			SysAccessUser.set(lu.initBySysUser(sysUserKey));
//			chain.doFilter(httpRequest, httpResponse);
//		}
	}
	
	public boolean matchs(String url) {
		if(StringUtils.isBlank(url)) {
			return false;
		}
		for(String s:exculusionsList) {
			if(Pattern.matches(s, url)) {
				return true;
			}
		}
		return false;
	}

}
