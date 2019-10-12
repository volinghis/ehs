
package com.ehs.common.base.dao.impl;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.entity.BaseEntity;


@Repository
public class BaseCommonDaoImpl implements com.ehs.common.base.dao.BaseCommonDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger=LoggerFactory.getLogger(BaseCommonDaoImpl.class);

	/**
	 * 
	* @see com.ehs.edm.base.dao.BaseCommonDao#saveOrUpdate(com.ehs.edm.base.bean.BaseEntity)  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午1:40:25 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	 */
	@Override
	public BaseEntity saveOrUpdate(BaseEntity entity)  {
		String dataId = entity.getId();
		if (StringUtils.isBlank(dataId)) {
			entity.initCreate();
			entity.setDataModel(DataModel.CREATE);
			entityManager.persist(entity);
			return entity;
		}
		return update(entity, DataModel.UPDATE);

	}

	private BaseEntity update(BaseEntity entity,DataModel model) {
		BaseEntity newEntity=null;
		try {
			newEntity = entity.getClass().getConstructor().newInstance();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
		BeanUtils.copyProperties(entity, newEntity);
		
		String className = entity.getClass().getSimpleName();
		StringBuilder hql=new StringBuilder(" update ").append(className).append(" set ").append(BaseEntity.VERSION_ID).append(" = ").append(DataConfig.VERSION_EXPIRE).append(" where ").append(BaseEntity.KEY).append(" =?0 and ").append(BaseEntity.VERSION_ID).append(" =?1 ");
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter(0, entity.getKey());
		query.setParameter(1, DataConfig.VERSION_EFFECTIVE);
		int result = query.executeUpdate();
		if(result<1) {
			throw new RuntimeException("data has been deleted");
		}
		newEntity.initUpdate();
		if(StringUtils.equals(model.name(), DataModel.REMOVE.name())) {
			newEntity.setVersionId(DataConfig.VERSION_EXPIRE);
		}
		newEntity.setDataModel(model);
		entityManager.persist(newEntity);
		return newEntity;
	}

	/**
	 * 
	* @see com.ehs.edm.base.dao.BaseCommonDao#deleteById(java.lang.String)  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 下午1:40:22 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日      chentm           v1.0.0               修改原因
	 */
	@Override
	public void delete(BaseEntity entity) {
		update(entity, DataModel.REMOVE);
		
	}







	/** 
	* @see com.ehs.security.dao.BaseCommonDao#getSession()  
	* @Function: BaseCommonDaoImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月19日 下午4:39:45 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月19日      chentm           v1.0.0               修改原因
	*/
	@Override
	public Session getSession() {
		return entityManager.unwrap(org.hibernate.Session.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}


	@Override
	public List<BaseEntity> find(String hql, List<Object> params) {
		Query query=entityManager.createQuery(hql);
		if(params!=null&&!params.isEmpty()) {
			for(int i=0;i<params.size();i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query.getResultList();
	}





}
