/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base.dao 
 * @author: chentm   
 * @date: 2019年5月7日 上午9:23:06 
 */
package com.ehs.common.base.dao;



import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ehs.common.base.entity.BaseEntity;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: BaseCommonDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月7日 上午9:23:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月7日     chentm           v1.0.0               修改原因
*/
public interface BaseCommonDao {

	public <T extends BaseEntity> T save(T t);
	
	
	public List<?> find(String hql,List<Object> params);
	
	public Session getSession();
	
}
