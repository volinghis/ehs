package com.ehs.common.auth.config;

import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ehs.common.auth.interceptor.RequestAuthInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	
	@Resource
	private Environment environment;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String ignorePattens=environment.getProperty("com.ehs.auth.authFilter.ignorePattens");
		if(StringUtils.isNotBlank(ignorePattens)) {
			registry.addInterceptor(new RequestAuthInterceptor()).addPathPatterns("/**")
			// 去除拦截主页，登录页面，注册页面，忘记密码页面
			// 静态资源拦截springboot2.1.7 自动配置好的不用设置 （不知道是不是2.X版本都不用设置）
			.excludePathPatterns(Arrays.asList(ignorePattens));
		}else {
			registry.addInterceptor(new RequestAuthInterceptor()).addPathPatterns("/**");
		}

	}
}
