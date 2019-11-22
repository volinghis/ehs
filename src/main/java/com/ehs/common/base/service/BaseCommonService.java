/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base.service 
 * @author: chentm   
 * @date: 2019年5月7日 上午9:20:39 
 */
package com.ehs.common.base.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ehs.common.base.entity.BaseEntity;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: BaseCommonService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月7日 上午9:20:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月7日     chentm           v1.0.0               修改原因
*/
public interface BaseCommonService {
	
	public Session getSession();
	
	public <T extends BaseEntity> T saveOrUpdate(T t);

	public <T extends BaseEntity> T deleteByKey(Class<T> t,String key);
	

	public <T extends BaseEntity> T findByKey(Class<T> t, String key);
	
	public List<?>  findAll(Class<? extends BaseEntity> clazz);
}
