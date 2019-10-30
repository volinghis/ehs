
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
	


	@Override
	public List<?> find(String hql, List<Object> params) {
		Query query=entityManager.createQuery(hql);
		if(params!=null&&!params.isEmpty()) {
			for(int i=0;i<params.size();i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query.getResultList();

	}

	@Override
	public Session getSession() {
		return entityManager.unwrap(org.hibernate.Session.class);
	}

	@Override
	public <T extends BaseEntity> T save(T t) {
		return entityManager.merge(t);
	}




}
