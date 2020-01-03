package com.ehs.common.flow.entity.impl;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.ehs.common.flow.entity.entitysuper.FlowSampleSuper;

@Entity
@Table(name="HIS_FLOW_SAMPLE")
public class FlowSampleHis extends FlowSampleSuper {

	private static final long serialVersionUID = 1L;

}
