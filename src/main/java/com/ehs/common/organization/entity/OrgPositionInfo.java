package com.ehs.common.organization.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;

@Entity
@Table(name="ORG_POSITION_INFO",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class OrgPositionInfo extends com.ehs.common.organization.entity.entitysuper.OrgPositionInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
}
