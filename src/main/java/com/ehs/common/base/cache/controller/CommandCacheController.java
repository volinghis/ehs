package com.ehs.common.base.cache.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehs.common.base.utils.JsonUtils;

import net.sf.ehcache.CacheManager;

@Controller
public class CommandCacheController {
	
	
	/**
	 * 移除缓存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/base/cache/evictCacheByKey")
	@ResponseBody
	public String evictCacheByKey(HttpServletRequest request, HttpServletResponse response) {
		String key=request.getParameter("cacheKey");
		try {
			CacheManager.getInstance().getCache("defaultCache").remove(key);
		}catch (Exception e) {
			return JsonUtils.toJsonString("error");
		}
		return JsonUtils.toJsonString("ok");
	}
}
