package com.ehs.common.flow.service;


import org.flowable.engine.runtime.ProcessInstance;

import com.ehs.common.flow.entity.FlowBaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;


public interface FlowBaseService {
	public void processReject(FlowProcessInfo flowProcessInfo);
	
	public void processSend(FlowProcessInfo flowProcessInfo);
	
	public void processEnd(FlowProcessInfo flowProcessInfo);
	
	public void processCancel(FlowProcessInfo flowProcessInfo);
	
	public ProcessInstance startProcess(FlowBaseEntity flowEntity,FlowProcessInfo flowProcessInfo);
}
