/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base.service 
 * @author: chentm   
 * @date: 2019年5月7日 上午9:21:47 
 */
package com.ehs.common.base.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.dao.BaseCommonDao;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
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

	private static Logger logger = LoggerFactory.getLogger(BaseCommonService.class);

	@Resource
	private BaseCommonDao baseCommonDao;

	/**
	 * @see com.ehs.edm.base.service.BaseCommonService#saveOrUpdate(com.ehs.edm.base.bean.BaseEntity)
	 * @Function: BaseCommonServiceImpl.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: chentm
	 * @date: 2019年5月7日 上午9:21:47
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年5月7日
	 *        chentm v1.0.0 修改原因
	 */
	@Override
	@Transactional
	@CacheEvict(value = "defaultCache", key = "#baseEntity.key")
	public BaseEntity saveOrUpdate(BaseEntity baseEntity) {
		try {
			BaseEntity b = baseCommonDao.saveOrUpdate(baseEntity);
			return b;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void subDelete(BaseEntity baseEntity) {

		List<String> foreignClasses = baseEntity.getForeignClasses();

		if (foreignClasses != null && foreignClasses.size() > 0) {
			foreignClasses.stream().forEach(f -> {
				String[] ss = StringUtils.split(f, ",");
				String foreignKey = ss[0];
				String className = ss[1];
				String refKey = ss[2];
				String v = "";
				try {
					v = (String) BeanUtils.findDeclaredMethod(baseEntity.getClass(),
							(new StringBuilder()).append("get").append(Character.toUpperCase(foreignKey.charAt(0)))
									.append(foreignKey.substring(1)).toString())
							.invoke(baseEntity);

					if (StringUtils.isBlank(v)) {
						StringBuilder builder = (new StringBuilder()).append("无法获取到数据!实体:")
								.append(baseEntity.getClass()).append(",key=").append(baseEntity.getKey());
						logger.error(builder.toString());
						throw new RuntimeException();
					}

					StringBuilder hql = new StringBuilder(" select fe from ").append(className).append(" fe where fe.")
							.append(BaseEntity.VERSION_ID).append("=?0   and fe.").append(refKey).append("=?1 ");
					List params = new LinkedList();
					params.add(0, DataConfig.VERSION_EFFECTIVE);
					params.add(1, v);
					List<BaseEntity> baseList = baseCommonDao.find(hql.toString(), params);
					if (baseList != null && baseList.size() > 0) {
						baseList.forEach(s -> {
							delete(s);
						});
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new RuntimeException(e);
				}

			});
		}
		baseCommonDao.delete(baseEntity);

	}

	/**
	 * @see com.ehs.security.service.BaseCommonService#deleteByWhereCase(java.lang.Class,
	 *      org.springframework.data.jpa.domain.Specification)
	 * @Function: BaseCommonServiceImpl.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: chentm
	 * @date: 2019年6月20日 上午9:41:55
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年6月20日
	 *        chentm v1.0.0 修改原因
	 */
	@Override
	@Transactional
	public void deleteByKey(Class clazz, String key) {
		BaseEntity baseEntity = findByKey(clazz, key);
		if (baseEntity != null) {
			delete(baseEntity);
		}
	}

	/**
	 * @see com.ehs.security.service.BaseCommonService#getSession()
	 * @Function: BaseCommonServiceImpl.java
	 * @Description: 该函数的功能描述
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: chentm
	 * @date: 2019年7月19日 下午4:39:07
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年7月19日
	 *        chentm v1.0.0 修改原因
	 */
	@Override
	public Session getSession() {
		return baseCommonDao.getSession();
	}

	@Override
	@Transactional
	@Cacheable(value = "defaultCache", key = "#key")
	public BaseEntity findByKey(Class clazz, String key) {
		StringBuilder builder = new StringBuilder(" select be from  ").append(clazz.getSimpleName())
				.append(" be where be.").append(BaseEntity.VERSION_ID).append(" = ?0 and be.key=?1 ");
		List params = new LinkedList();
		params.add(0, 0l);
		params.add(1, key);
		List<BaseEntity> list = baseCommonDao.find(builder.toString(), params);
		if (list != null && !list.isEmpty()) {
			BaseEntity en = list.stream().findFirst().get();
			getSession().evict(en);
			return en;
		} else {
			return null;
		}

	}

	@Transactional
	@Override
	public List<? extends BaseEntity> findAll(Class clazz) {
		StringBuilder builder = new StringBuilder(" select be from  ").append(clazz.getSimpleName())
				.append(" be where be.").append(BaseEntity.VERSION_ID).append(" = ?0  order by ")
				.append(BaseEntity.CREATION_TIME).append(" desc");
		List params = new LinkedList();
		params.add(0, DataConfig.VERSION_EFFECTIVE);
		List list = baseCommonDao.find(builder.toString(), params);
		if (list != null && !list.isEmpty()) {
			list.forEach(s -> {
				getSession().evict(s);
			});
		}
		return list;
	}

	@Transactional
	@Override
	@CacheEvict(value = "defaultCache", key = "#entity.key")
	public void delete(BaseEntity entity) {
		subDelete(entity);
	}

}
