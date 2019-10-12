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
	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 保存或更新实体
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月7日 上午9:21:11 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月7日     chentm           v1.0.0               修改原因
	 */
	public <T extends BaseEntity> T saveOrUpdate(T t) ;

	
	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年6月20日 上午9:41:37 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年6月20日     chentm           v1.0.0               修改原因
	 */
	public void deleteByKey(Class clazz,String key);

	/**
	 * 删除
	 * @param entity
	 */
	public void delete(BaseEntity entity);
	/**
	 * 
	* @Function: BaseCommonService.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月3日 上午11:30:43 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月3日     chentm           v1.0.0               修改原因
	 */
	public <T extends BaseEntity> T findByKey(Class<T> clazz,String key);
	/**
	 * 查找所有
	 * @param clazz
	 * @return
	 */
	public List<? extends BaseEntity >  findAll(Class<? extends BaseEntity> clazz);
}
