package com.ehs.common.base.cache.aspect;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import com.ehs.common.base.cache.service.CommandCacheEventService;
import com.ehs.common.base.entity.BaseEntity;

@Aspect
@Component
public class CommandCacheAspect {

	private Logger logger = LoggerFactory.getLogger(CommandCacheAspect.class);

	@Resource
	private CommandCacheEventService commandCacheEventService;

	@Before("@annotation(org.springframework.cache.annotation.CacheEvict)") // 拦截被TestAnnotation注解的方法；如果你需要拦截指定package指定规则名称的方法，可以使用表达式execution(...)，具体百度一下资料一大堆
	public void beforeCacheEvict(JoinPoint point) throws Throwable {
		Object[] args = point.getArgs();
		if (args != null && args.length > 0) {
			BaseEntity b = (BaseEntity) args[0];
			if (StringUtils.isNotBlank(b.getKey())) {
				logger.info("刷新缓存");
				commandCacheEventService.evictCache(b.getKey());
			}
		}

	}
}
