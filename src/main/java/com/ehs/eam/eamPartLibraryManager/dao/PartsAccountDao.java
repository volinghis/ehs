package com.ehs.eam.eamPartLibraryManager.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibraryDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午3:58:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/

@Repository
public interface PartsAccountDao extends JpaRepository<PartsAccount, String> {

	@Query(" select p from PartsAccount p where (p."+PartsAccount.DEVICE_CODE+" like %?1% or p."+PartsAccount.DEVICE_NAME+" like %?1% ) order by "+BaseEntity.CREATION_TIME+" desc")
	public Page<PartsAccount> findEamPart(String query, Pageable pageable);
}
