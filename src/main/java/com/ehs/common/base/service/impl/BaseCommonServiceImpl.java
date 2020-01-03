/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base.service 
 * @author: chentm   
 * @date: 2019年5月7日 上午9:21:47 
 */
package com.ehs.common.base.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.dao.BaseCommonDao;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: BaseCommonServiceImpl.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年5月7日 上午9:21:47
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年5月7日
 *        Administrator v1.0.0 修改原因
 */
@Service
public class BaseCommonServiceImpl implements BaseCommonService {

	private static final Logger logger = LoggerFactory.getLogger(BaseCommonServiceImpl.class);

	
	@Resource
	private BaseCommonDao baseCommonDao;

	@Transactional
	@Override
	@CacheEvict(value = DataConfig.CACHE_NAME, key = "#t.key")
	public <T extends BaseEntity> T saveOrUpdate(T t) {
		logger.debug("saveOrUpdate:"+JsonUtils.toJsonString(t));
		Assert.notNull(t, "需要操作的实例不能为空");
		T old = null;
		if (StringUtils.isNotBlank(t.getKey())) {
			old = (T) findByKey(t.getClass(), t.getKey());
		}
		if (t.getReCompletePoint()) {
			Class ss = t.getClass();
			Field[] fields = ss.getDeclaredFields();
			int fieldCount = 0;
			int notNullCount = 0;
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if ((!field.isAnnotationPresent(Transient.class)) && (!Modifier.isStatic(field.getModifiers()))
						&& !Modifier.isFinal(field.getModifiers())) {
					field.setAccessible(true);
					fieldCount++;
					try {
						if (field.get(t) != null && StringUtils.isNotBlank(field.get(t).toString())) {
							notNullCount++;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (fieldCount != 0) {
				t.setCompletePoint(Byte.valueOf(String.valueOf(notNullCount * 100 / fieldCount)));
			}
		}
		if (old == null) {
			t.initCreate();
			t.setId(null);
			baseCommonDao.save(t);
		} else {
			if (old.getDataModel() == DataModel.REMOVE) {
				throw new RuntimeException("错误的尝试更新一个已经被删除的实例");
			}
			T his = null;
			try {
				his = (T) Class.forName(old.getClass().getName() + DataConfig.ENTITY_HIS_SUFFIX).getConstructor().newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			BeanUtils.copyProperties(old, his, BaseEntity.ID);
			baseCommonDao.save(his);
			t.initUpdate();
			t.setDataModel(DataModel.UPDATE);
			baseCommonDao.save(t);
		}
		return t;

	}

	@Transactional
	@Override
	@CacheEvict(value = DataConfig.CACHE_NAME, key = "#key")
	public <T extends BaseEntity> T deleteByKey(Class<T> tc, String key) {
		logger.debug("deleteByKey:Class="+tc.getName()+",key="+key);
		T t = findByKey(tc, key);
		Assert.notNull(t, "将要删除的实例不存在");
		if (DataModel.REMOVE.equals(t.getDataModel())) {
			throw new RuntimeException("错误的尝试删除一个已经被删除的实例");
		}
		T t1 = null;
		try {
			t1 = (T) Class.forName(t.getClass().getName() + DataConfig.ENTITY_HIS_SUFFIX).getConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		BeanUtils.copyProperties(t, t1, BaseEntity.ID);
		baseCommonDao.save(t1);
		t.initUpdate();
		t.setDataModel(DataModel.REMOVE);
		baseCommonDao.save(t);
		return t;
	}

	@Override
	@Cacheable(value = DataConfig.CACHE_NAME, key = "#key", unless = "#result == null")
	public <T extends BaseEntity> T findByKey(Class<T> t, String key) {
		logger.debug("findByKey:Class="+t.getName()+",key="+key);

		StringBuilder builder = new StringBuilder(" select be from  ");
		builder.append(t.getSimpleName());
		builder.append(" be where be.");
		builder.append(BaseEntity.KEY);
		builder.append(" = ?0  order by  ");
		builder.append(BaseEntity.CREATION_TIME);
		builder.append(" desc");
		List<Object> params = new LinkedList<Object>();
		params.add(0, key);
		List<?> list = baseCommonDao.find(builder.toString(), params);
		if (list == null || list.isEmpty()) {
			return null;
		}
		T e = (T) list.get(0);
		return e;
	}

	@Override
	public List<?> findAll(Class<? extends BaseEntity> clazz) {
		logger.debug("findAll:Class="+clazz.getName());
		StringBuilder builder = new StringBuilder(" select be from  ");
		builder.append(clazz.getSimpleName());
		builder.append(" be where be.");
		builder.append(BaseEntity.DATA_MODEL);
		builder.append(" in ?0 order by ");
		builder.append(BaseEntity.CREATION_TIME);
		builder.append(" desc");
		List<Object> params = new LinkedList<Object>();
		List<DataModel> ll = new LinkedList<DataModel>();
		ll.add(DataModel.CREATE);
		ll.add(DataModel.UPDATE);
		params.add(0, ll);
		return baseCommonDao.find(builder.toString(), params);
	}

	@Override
	public Session getSession() {
		return baseCommonDao.getSession();
	}

}
