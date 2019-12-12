package com.ehs.common.base.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.service.InitDataService;

@Service
public class InitDataServiceImpl implements InitDataService {

	

	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Transactional
	@Override
	public void initData(List<BaseEntity> baseEntities) {
		Assert.notNull(baseEntities, "list for baseEntities is required");
		for (BaseEntity baseEntity : baseEntities) {
			baseCommonService.saveOrUpdate(baseEntity);
		}

	}

}
