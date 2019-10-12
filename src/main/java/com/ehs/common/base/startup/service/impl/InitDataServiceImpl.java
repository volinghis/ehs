package com.ehs.common.base.startup.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.common.auth.entity.SysMenu;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.startup.service.InitDataService;

@Service
public class InitDataServiceImpl implements InitDataService {

	

	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Transactional
	@Override
	public void initData(List<BaseEntity> baseEntities) {
		Assert.notNull(baseEntities, "list for baseEntities is required");
		for (BaseEntity baseEntity : baseEntities) {
			String key =baseEntity.getKey();
			BaseEntity entity = baseCommonService.findByKey(baseEntity.getClass(), key);
			if(entity == null) {
				baseCommonService.saveOrUpdate(baseEntity);
			}else {
				if(!baseEntity.equals(entity)) {
					baseCommonService.delete(entity);
					baseCommonService.saveOrUpdate(baseEntity);
				}
			}
			
		}

	}

}
