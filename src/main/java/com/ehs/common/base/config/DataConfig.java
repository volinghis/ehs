package com.ehs.common.base.config;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class DataConfig {



	
	public static final String ENTITY_HIS_SUFFIX="His";
	
	public static final String TABLE_UNIQUE_KEY="DATA_KEY";
	
	public static final boolean DATA_UPDATED=true;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
	       return builder.build();
	}
}
