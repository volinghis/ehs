package com.ehs.common.base.cache.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ehs.common.base.cache.service.CommandCacheEventService;
import com.ehs.common.base.cache.thread.CommandCacheThread;

@Service
public class CommandCacheEventListenerServiceImpl implements CommandCacheEventService {

	
	
	@Resource 
	private Environment ev ;
	
	@Override
	public void evictCache(String cacheKey) {
        String urls=ev.getProperty("spring.cache.ehcache.peer.localaddress");
        if(StringUtils.isNotBlank(urls)) {
        	String[] u=StringUtils.split(urls, ",");
        	for(String s: u) {
        		CommandCacheThread t=new CommandCacheThread(cacheKey,s);
        		Thread tt=new Thread(t);
        		tt.start();
        	}
        }
	}

}
