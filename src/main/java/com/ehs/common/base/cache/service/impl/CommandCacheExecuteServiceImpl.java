package com.ehs.common.base.cache.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ehs.common.base.cache.service.CommandCacheExecuteService;

@Service
public class CommandCacheExecuteServiceImpl implements CommandCacheExecuteService {

	private static final Logger logger=LoggerFactory.getLogger(CommandCacheExecuteServiceImpl.class);
	
	@Resource
	private RestTemplate restTemplate;
	
	@Override
	public void executeEvictCache(String cacheKey,String url) {
		String result=restTemplate.getForObject(url+"/base/cache/evictCacheByKey?cacheKey="+cacheKey, String.class);
		logger.info(new StringBuilder("删除缓存结果：").append(result).append("[url:").append(url).append(",key:").append(cacheKey).append("]").toString());
	}

}
