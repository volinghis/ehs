package com.ehs.common.flow.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.entity.impl.FlowSample;
import com.ehs.common.flow.service.FlowBaseService;

@Service
public class FlowSampleService implements com.ehs.common.flow.service.FlowSampleService {

	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private FlowBaseService flowBaseService; 
	
	@Transactional
	@Override
	public FlowSample saveFlowSample(FlowSample sample, FlowProcessInfo processInfo) {
		FlowSample t= baseCommonService.saveOrUpdate(sample);
		flowBaseService.startProcess(t, processInfo);
		return t;
	}

}
