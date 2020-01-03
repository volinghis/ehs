package com.ehs.common.flow.service;

import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.entity.impl.FlowSample;

public interface FlowSampleService {

	public FlowSample saveFlowSample(FlowSample sample,FlowProcessInfo processInfo);
}
