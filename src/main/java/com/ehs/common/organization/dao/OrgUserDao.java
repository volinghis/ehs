package com.ehs.common.organization.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.organization.entity.OrgUser;

@Repository
public interface OrgUserDao extends JpaRepository<OrgUser, String> {

	@Query(" select u from OrgUser u where u."+OrgUser.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' and (u."+OrgUser.DATA_CODE+" like %?1% or u."+OrgUser.NAME+" like %?1% ) order by  "+BaseEntity.CREATION_TIME+" desc")
	public Page<OrgUser> findUsers(String query, PageRequest pageRequest);

	@Query(" select u from OrgUser u where u.orgKey=?1 and u."+OrgUser.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"'" )
	public Page<OrgUser> findUserByOrgKey(String orgKey, String query, PageRequest pageRequest);

	@Query(" select u from OrgUser u where u."+OrgUser.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.CREATION_TIME+" desc")
	public Page<OrgUser> findUsers(PageRequest pageRequest);
	
	
}
