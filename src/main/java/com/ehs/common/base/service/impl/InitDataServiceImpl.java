package com.ehs.common.base.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.CacheManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.common.auth.entity.SysMenu;
import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.config.RedisCacheConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.service.InitDataService;

@Service
public class InitDataServiceImpl implements InitDataService {

	

	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private RedisCacheManager redisCacheManager;
	
	@Transactional
	@Override
	public void initData(List<BaseEntity> baseEntities) {
		Assert.notNull(baseEntities, "list for baseEntities is required");
		for (BaseEntity baseEntity : baseEntities) {
			baseCommonService.saveOrUpdate(baseEntity);
		}

	}

}
