package com.ehs.common.flow.entity.entitysuper;

import javax.persistence.MappedSuperclass;

import com.ehs.common.flow.entity.FlowBaseEntity;

@MappedSuperclass
public abstract class FlowSampleSuper extends FlowBaseEntity{

	private static final long serialVersionUID = 1L;

	
	public static final String CONTENT="content";
	
	private String content;
	
	
	
	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	@Override
	public String getFlowProcessId() {
		return "TestFlow";
	}

}
