package com.ehs.common.flow.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.Event;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.task.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.flow.bean.ProcessCommentBean;
import com.ehs.common.flow.bean.ProcessDefineBean;
import com.ehs.common.flow.bean.ProcessInstanceBean;
import com.ehs.common.flow.bean.ProcessStepBean;
import com.ehs.common.flow.enums.FlowTaskOper;
import com.ehs.common.oper.bean.ResultBean;
import com.ehs.common.organization.entity.OrgUser;

@Controller
public class FlowBaseController {

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private ProcessEngine processEngine;

	@Resource
	private org.flowable.engine.TaskService taskService;

	@Resource
	private RepositoryService repositoryService;

	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	 * @Function: ProcessInfoController.java
	 * @Description: 获取流程信息
	 *
	 * @param:描述1描述
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * @version: v1.0.0
	 * @author: Administrator
	 * @date: 2019年12月25日 下午4:32:46
	 *
	 *        Modification History: Date Author Version Description
	 *        ---------------------------------------------------------* 2019年12月25日
	 *        Administrator v1.0.0 修改原因
	 */
	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/flowInfo/getProcessByProcessDefineKey")
	@ResponseBody
	public String getProcessByProcessDefineKey(HttpServletRequest request, HttpServletResponse response) {

		String processDefineKey = request.getParameter("processDefineKey");
		Assert.notNull(processDefineKey, "processDefineKey must be not null");

		BpmnModel bpmnModel = repositoryService.getBpmnModel(repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefineKey).latestVersion().singleResult().getId());
		Assert.notNull(bpmnModel, "bpmnModel can not be find");

		org.flowable.bpmn.model.Process process =  bpmnModel.getMainProcess();
		ProcessDefineBean pdb = new ProcessDefineBean();
		pdb.setName(process.getName());
		pdb.setKey(process.getId());

		List<ProcessStepBean> steps = new ArrayList<ProcessStepBean>();
		Collection<FlowElement> flowElements = process.getFlowElements();
		if (flowElements != null) {
			for (FlowElement flowElement : flowElements) {
				if(flowElement instanceof UserTask ) {
					ProcessStepBean psb = new ProcessStepBean();
					psb.setStepKey(flowElement.getId());
					psb.setStepName(flowElement.getName());
					steps.add(psb);
				}

			}
			for (FlowElement flowElement : flowElements) {
				if(flowElement instanceof EndEvent ) {
					ProcessStepBean psb = new ProcessStepBean();
					psb.setStepKey(flowElement.getId());
					psb.setStepName(flowElement.getName());
					steps.add(psb);
					break;
				}

			}
		}
		pdb.setSteps(steps);
		return JsonUtils.toJsonString(pdb);
	}

	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/flowInfo/getProcessCommentsByProcessInstanceKey")
	public String getProcessCommentsByProcessInstanceKey(HttpServletRequest request, HttpServletResponse response) {

		String processInstanceKey = request.getParameter("processInstanceKey");
		Assert.notNull(processInstanceKey, "ProcessInstanceKey must be not null");

		List<Comment> comments = taskService.getProcessInstanceComments(processInstanceKey);
		List<ProcessCommentBean> pcbList = new ArrayList<ProcessCommentBean>();
		if (comments != null && !comments.isEmpty()) {
			for (Comment c : comments) {
				ProcessCommentBean pcb = new ProcessCommentBean();
				pcb.setContent(c.getFullMessage());
				pcb.setCreationTime(c.getTime());
				pcb.setCreation(c.getUserId());
				if (StringUtils.isNotBlank(c.getUserId())) {
					OrgUser ou = baseCommonService.findByKey(OrgUser.class, c.getUserId());
					if (ou != null) {
						pcb.setCreationName(ou.getName());
					}
				}
				String oper="";
				if(StringUtils.equals(FlowTaskOper.AGREE.name(), c.getType())) {
					oper="同意";
				}else if(StringUtils.equals(FlowTaskOper.COMMIT.name(), c.getType())){
					oper="提交";
				}else if(StringUtils.equals(FlowTaskOper.REJECT.name(), c.getType())){
					oper="驳回";
				}
				pcb.setOperType(oper);
				pcbList.add(pcb);
			}
		}
		return JsonUtils.toJsonString(pcbList);
	}

	@RequestAuth(menuKeys = { AuthConstants.GLOBAL_MENU_KEY })
	@RequestMapping(value = "/flow/flowInfo/getProcessInstanceByKey")
	public String getProcessInstanceByKey(HttpServletRequest request, HttpServletResponse response) {

		String processInstanceKey = request.getParameter("processInstanceKey");
		Assert.notNull(processInstanceKey, "ProcessInstanceKey must be not null");

		ProcessInstanceBean pib=new ProcessInstanceBean();
		ProcessInstance pi= runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceKey).singleResult();
		
		return JsonUtils.toJsonString(pib);
	}
}
