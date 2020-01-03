package com.ehs.common.flow.entity.impl;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.flow.entity.entitysuper.FlowSampleSuper;

@Entity
@Table(name="FLOW_SAMPLE",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class FlowSample extends FlowSampleSuper {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
