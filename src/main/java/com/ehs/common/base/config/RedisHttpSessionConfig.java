package com.ehs.common.base.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

@Configuration
@EnableRedisHttpSession
public class RedisHttpSessionConfig  extends RedisHttpSessionConfiguration{

	@Value("${spring.redis.http.session.timeout}")
	private int sessionTimeout;


	//初始化参数
    @PostConstruct
    @Override
    public void init() {
    	super.init();
    	super.setMaxInactiveIntervalInSeconds(sessionTimeout);//session超时时间
    }
}
