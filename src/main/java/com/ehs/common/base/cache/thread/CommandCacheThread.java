package com.ehs.common.base.cache.thread;

import com.ehs.common.base.cache.service.CommandCacheExecuteService;
import com.ehs.common.base.utils.SpringUtils;

public class CommandCacheThread implements Runnable {
	
	private String cacheKey;
	
	private String url;
	public CommandCacheThread(String cacheKey,String url) {
		this.cacheKey=cacheKey;
		this.url=url;
	}
	
	@Override
	public void run() {
		CommandCacheExecuteService cce=	SpringUtils.getBean(CommandCacheExecuteService.class);
		cce.executeEvictCache(cacheKey,url);
	}

}
