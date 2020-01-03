package com.ehs.common.flow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.controller.bean.FlowSampleBean;
import com.ehs.common.flow.service.FlowSampleService;
import com.ehs.common.oper.bean.ResultBean;

@RestController
public class FlowSampleController {

	
	@Resource
	private FlowSampleService flowSampleService;
	
	@RequestAuth(menuKeys = {"userManager"})
	@RequestMapping(value = "/flow/sample/save")
	public String save(HttpServletRequest request, @RequestBody FlowSampleBean flowSampleBean) {
		ResultBean resultBean = new ResultBean();
		flowSampleService.saveFlowSample(flowSampleBean.getFlowSample(), flowSampleBean.getFlowProcessInfo());
		return JsonUtils.toJsonString(resultBean.ok("提交流程成功！"));
	}
}
