package com.ehs.common.flow.entity.impl;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

@Entity
@Table(name="FLOW_PROCESS_INFO",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class FlowProcessInfo extends com.ehs.common.flow.entity.entitysuper.FlowProcessInfoSuper {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
