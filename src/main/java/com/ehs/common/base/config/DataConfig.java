package com.ehs.common.base.config;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class DataConfig {

	/**
	 * 版本有效值
	 */
	public static final long VERSION_EFFECTIVE=0l;
	/**
	 * 版本失效值
	 */
	public static final long VERSION_EXPIRE=1l;
	
	 @Bean
	 public RestTemplate restTemplate(RestTemplateBuilder builder){
	        return builder.build();
	 }
}
