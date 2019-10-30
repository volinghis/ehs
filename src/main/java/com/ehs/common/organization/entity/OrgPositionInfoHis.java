package com.ehs.common.organization.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.organization.entity.entitysuper.OrgPositionInfo;

@Entity
@Table(name="ORG_POSITION_INFO_his")
public class OrgPositionInfoHis extends OrgPositionInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
}
