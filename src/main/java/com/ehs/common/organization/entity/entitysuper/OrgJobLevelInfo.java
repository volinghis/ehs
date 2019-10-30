package com.ehs.common.organization.entity.entitysuper;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.ehs.common.base.entity.BaseEntity;


@MappedSuperclass
public abstract  class OrgJobLevelInfo extends BaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DATA_CODE="dataCode";
	public static final String NAME="name";
	
	private String dataCode;
	private String name;
	
	
	

	
	
	public String getDataCode() {
		return dataCode;
	}
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
