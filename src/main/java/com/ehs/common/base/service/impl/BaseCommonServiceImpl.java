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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.dao.BaseCommonDao;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.organization.entity.OrgUser;

import net.sf.ehcache.CacheManager;

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

	@Resource
	private BaseCommonDao baseCommonDao;

	@Transactional
	@Override
	public <T extends BaseEntity> T saveOrUpdate(T t) {
		Assert.notNull(t, "需要操作的实例不能为空");
		T old = null;
		if (StringUtils.isNotBlank(t.getKey())) {
			old = (T) findByKey(t.getClass(), t.getKey());
		}
		if (old == null) {
			t.initCreate();
			t.setId(null);
			baseCommonDao.save(t);
		} else {
			if (old.getDataModel()== DataModel.REMOVE) {
				throw new RuntimeException("错误的尝试更新一个已经被删除的实例");
			}
	
			if (!StringUtils.equals(t.getId(), old.getId())) {
				throw new RuntimeException("错误的尝试更新一个无法定位的实例");
			}
			T his = null;
			try {
				his = (T) Class.forName(old.getClass().getName() + DataConfig.TABLE_HIS_SUFFIX)
						.getConstructor().newInstance();
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
	public <T extends BaseEntity> T deleteByKey(Class<T> tc, String key) {
		T t = findByKey(tc, key);
		return delete(t);

	}

	@Override
	public <T extends BaseEntity> T findByKey(Class<T> t, String key) {
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
		StringBuilder builder = new StringBuilder(" select be from  ");
		builder.append(clazz.getSimpleName());
		builder.append(" be where be.");
		builder.append(BaseEntity.DATA_MODEL);
		builder.append(" in ?0 order by ");
		builder.append(BaseEntity.CREATION_TIME);
		builder.append(" desc");
		List<Object> params = new LinkedList<Object>();
		List<DataModel> ll=new LinkedList<DataModel>();
		ll.add(DataModel.CREATE);
		ll.add(DataModel.UPDATE);
		params.add(0, ll);
		return baseCommonDao.find(builder.toString(), params);
	}

	@Override
	public Session getSession() {
		return baseCommonDao.getSession();
	}

	@Transactional
	@Override
	public <T extends BaseEntity> T delete(T t) {
		Assert.notNull(t, "将要删除的实例不存在");

		if (DataModel.REMOVE.equals(t.getDataModel())) {
			throw new RuntimeException("错误的尝试删除一个已经被删除的实例");
		}

		T t1 = null;
		try {
			t1 = (T) Class.forName(t.getClass().getName() + DataConfig.TABLE_HIS_SUFFIX).getConstructor()
					.newInstance();
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

}
