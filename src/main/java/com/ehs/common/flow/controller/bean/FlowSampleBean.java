package com.ehs.common.flow.controller.bean;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.entity.impl.FlowSample;

public class FlowSampleBean {

	private FlowSample flowSample;
	private FlowProcessInfo flowProcessInfo;
	public FlowSample getFlowSample() {
		return flowSample;
	}
	public void setFlowSample(FlowSample flowSample) {
		this.flowSample = flowSample;
	}
	public FlowProcessInfo getFlowProcessInfo() {
		return flowProcessInfo;
	}
	public void setFlowProcessInfo(FlowProcessInfo flowProcessInfo) {
		this.flowProcessInfo = flowProcessInfo;
	}
	
	
}
