/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.flow.service.impl 
 * @author: chentm   
 * @date: 2019年7月17日 上午9:24:45 
 */
package com.ehs.common.flow.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.Activity;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.FlowableEngineAgenda;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityManager;
import org.flowable.engine.impl.util.CommandContextUtil;
import org.flowable.engine.impl.util.ProcessDefinitionUtil;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.AccessUtils;
import com.ehs.common.flow.entity.FlowBaseEntity;
import com.ehs.common.flow.entity.impl.FlowProcessInfo;
import com.ehs.common.flow.enums.FlowTaskOper;
import com.ehs.common.flow.service.FlowBaseService;
import com.ehs.common.flow.utils.FlowConstans;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FlowBaseServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年7月17日 上午9:24:45 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年7月17日      chentm          v1.0.0               修改原因
*/
@Service
public class FlowBaseServiceImpl implements FlowBaseService {

	private static final Logger logger=LoggerFactory.getLogger(FlowBaseServiceImpl.class);

	
	@Resource
	private TaskService taskService;

	@Resource
	private RuntimeService runtimeService;


	@Resource
	private RepositoryService repositoryService;
	
	
	
	@Resource
	private BaseCommonService baseCommonService;
	

	@Transactional
	@Override
	public void processReject(FlowProcessInfo flowProcessInfo) {
		
		try {
			ProcessInstance pi=runtimeService.createProcessInstanceQuery().includeProcessVariables().processInstanceId(flowProcessInfo.getFlowProcessInstanceId()).singleResult();
			
		    Authentication.setAuthenticatedUserId(SysAccessUser.get().getUserKey());
			taskService.addComment(flowProcessInfo.getVars().get(FlowConstans.TASK_ID).toString(), pi.getId(), FlowTaskOper.REJECT.name(),StringUtils.defaultIfBlank((String)flowProcessInfo.getVars().get(FlowConstans.TASK_COMMENT),""));
			Authentication.setAuthenticatedUserId(null);

			 runtimeService.createChangeActivityStateBuilder()
			.processInstanceId(flowProcessInfo.getFlowProcessInstanceId())
	        .moveActivityIdTo(flowProcessInfo.getFlowCurrentStep(), (String)pi.getProcessVariables().get(FlowConstans.FLOW_START_ACTIVITY_ID))
            .changeState();
		   

			List<Task> tasks= taskService.createTaskQuery().processInstanceId(pi.getId()).active().list();
			    String currentUser="";
			    String currentUserName="";
			    String currentStep="";
			    String currentStepName="";
			    if(tasks!=null&&!tasks.isEmpty()) {
			    	currentUser=String.join(",",tasks.stream().map(u -> u.getAssignee()).collect(Collectors.toList()));
			    	currentUserName=String.join(",",tasks.stream().map(u -> AccessUtils.getUserNameByUserKey(u.getAssignee())).collect(Collectors.toList()));
			    	currentStep=String.join(",",tasks.stream().map(u -> u.getTaskDefinitionKey()).collect(Collectors.toList()));
			    	currentStepName=String.join(",",tasks.stream().map(u -> u.getName()).collect(Collectors.toList()));
			    }
			    
			    flowProcessInfo.setFlowPrevPerson(flowProcessInfo.getFlowCurrentPerson());
			    flowProcessInfo.setFlowPrevPersonName(flowProcessInfo.getFlowCurrentPersonName());
			    flowProcessInfo.setFlowPrevStep(flowProcessInfo.getFlowCurrentStep());
			    flowProcessInfo.setFlowPrevStepName(flowProcessInfo.getFlowCurrentStepName());
			    flowProcessInfo.setFlowCurrentPerson(currentUser);
			    flowProcessInfo.setFlowCurrentPersonName(currentUserName);
			    flowProcessInfo.setFlowCurrentStep(currentStep);
			    flowProcessInfo.setFlowCurrentStepName(currentStepName);
			    baseCommonService.saveOrUpdate(flowProcessInfo);
			
		} catch (Exception e) {
			logger.error("执行流程驳回出错！", e.getMessage());
			throw new RuntimeException(e);
		}

	}


	/** 
	* @see com.ehs.security.flow.service.FlowBaseService#startProcess(java.lang.String, java.util.Map)  
	* @Function: FlowBaseServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月18日 下午4:13:33 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月18日      chentm           v1.0.0               修改原因
	*/


	/** 
	* @see com.ehs.security.flow.service.FlowBaseService#startProcess(com.ehs.security.flow.entity.FlowBaseEntity)  
	* @Function: FlowBaseServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月19日 上午11:30:49 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月19日      chentm           v1.0.0               修改原因
	*/
	@Transactional
	@Override
	public ProcessInstance startProcess(FlowBaseEntity flowBaseEntity,FlowProcessInfo flowProcessInfo) {
		try {
			String processId=flowBaseEntity.getFlowProcessId();
			//部署流程
		   repositoryService.createDeployment().addClasspathResource("processes/"+processId+".bpmn").deploy();
		   //System.out.println(d);
		   ProcessDefinition pd= repositoryService.createProcessDefinitionQuery().processDefinitionKey(processId).latestVersion().singleResult();
		
		    Map maps=new HashMap();
		    maps.putAll(flowProcessInfo.getVars());
		    maps.put(FlowConstans.TASK_ASSIGNEE, SysAccessUser.get().getUserKey());
		    
		    Authentication.setAuthenticatedUserId(SysAccessUser.get().getUserKey());
		    ProcessInstance pi=   runtimeService.startProcessInstanceByKey(pd.getKey(), flowProcessInfo.getKey(),maps);
		    Authentication.setAuthenticatedUserId(null);
		    
		    
			Task task= taskService.createTaskQuery().processInstanceId(pi.getId()).active().singleResult();
			runtimeService.setVariable(pi.getId(), FlowConstans.FLOW_START_ACTIVITY_ID, task.getTaskDefinitionKey());
			
			
			 Authentication.setAuthenticatedUserId(SysAccessUser.get().getUserKey());
			taskService.addComment(task.getId(), pi.getId(), FlowTaskOper.COMMIT.name(), StringUtils.defaultIfBlank((String)flowProcessInfo.getVars().get(FlowConstans.TASK_COMMENT),""));
		    Authentication.setAuthenticatedUserId(null);

			taskService.complete(task.getId(), flowProcessInfo.getVars());
			  
			  
		    List<Task> tasks= taskService.createTaskQuery().processInstanceId(pi.getId()).active().list();
		    String currentUser="";
		    String currentUserName="";
		    String currentStep="";
		    String currentStepName="";
		    if(tasks!=null&&!tasks.isEmpty()) {
		    	currentUser=String.join(",",tasks.stream().map(u -> u.getAssignee()).collect(Collectors.toList()));
		    	currentUserName=String.join(",",tasks.stream().map(u -> AccessUtils.getUserNameByUserKey(u.getAssignee())).collect(Collectors.toList()));
		    	currentStep=String.join(",",tasks.stream().map(u -> u.getTaskDefinitionKey()).collect(Collectors.toList()));
		    	currentStepName=String.join(",",tasks.stream().map(u -> u.getName()).collect(Collectors.toList()));
		    }
		    
		    flowProcessInfo.setFlowCurrentPerson(currentUser);
		    flowProcessInfo.setFlowCurrentPersonName(currentUserName);
		    flowProcessInfo.setFlowCurrentStep(currentStep);
		    flowProcessInfo.setFlowCurrentStepName(currentStepName);
		    flowProcessInfo.setFlowProcessInstanceId(pi.getId());
		    flowProcessInfo.setBusinessEntityKey(flowBaseEntity.getKey());
		    FlowProcessInfo fpi= baseCommonService.saveOrUpdate(flowProcessInfo);
		    
		    flowBaseEntity.setFlowProcessInfoKey(fpi.getKey());
		    baseCommonService.saveOrUpdate(flowBaseEntity);
		    return pi;
		}catch(Exception ex) {
			logger.error("启动流程出错",ex);
			throw new RuntimeException(ex);
		}

	}


	/** 
	* @see com.ehs.security.flow.service.FlowBaseService#processSend(java.lang.String, java.lang.String)  
	* @Function: FlowBaseServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年8月5日 上午9:21:00 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月5日      chentm           v1.0.0               修改原因
	*/
	@Transactional
	@Override
	public void processSend(FlowProcessInfo flowProcessInfo) {
		try {


			Task task= taskService.createTaskQuery().taskId(flowProcessInfo.getVars().get(FlowConstans.TASK_ID).toString()).singleResult();
			
			 Authentication.setAuthenticatedUserId(SysAccessUser.get().getUserKey());
			taskService.addComment(task.getId(), flowProcessInfo.getFlowProcessInstanceId(), FlowTaskOper.AGREE.name(), StringUtils.defaultIfBlank((String)flowProcessInfo.getVars().get(FlowConstans.TASK_COMMENT),""));
			 Authentication.setAuthenticatedUserId(null);

			taskService.complete(task.getId(), flowProcessInfo.getVars());
		    List<Task> tasks= taskService.createTaskQuery().processInstanceId(flowProcessInfo.getFlowProcessInstanceId()).active().list();
		    String currentUser="";
		    String currentUserName="";
		    String currentStep="";
		    String currentStepName="";
		    if(tasks!=null&&!tasks.isEmpty()) {
		    	currentUser=String.join(",",tasks.stream().map(u -> u.getAssignee()).collect(Collectors.toList()));
		    	currentUserName=String.join(",",tasks.stream().map(u -> AccessUtils.getUserNameByUserKey(u.getAssignee())).collect(Collectors.toList()));
		    	currentStep=String.join(",",tasks.stream().map(u -> u.getTaskDefinitionKey()).collect(Collectors.toList()));
		    	currentStepName=String.join(",",tasks.stream().map(u -> u.getName()).collect(Collectors.toList()));
		    }
		    
		    flowProcessInfo.setFlowPrevPerson(flowProcessInfo.getFlowCurrentPerson());
		    flowProcessInfo.setFlowPrevPersonName(flowProcessInfo.getFlowCurrentPersonName());
		    flowProcessInfo.setFlowPrevStep(flowProcessInfo.getFlowCurrentStep());
		    flowProcessInfo.setFlowPrevStepName(flowProcessInfo.getFlowCurrentStepName());
		    flowProcessInfo.setFlowCurrentPerson(currentUser);
		    flowProcessInfo.setFlowCurrentPersonName(currentUserName);
		    flowProcessInfo.setFlowCurrentStep(currentStep);
		    flowProcessInfo.setFlowCurrentStepName(currentStepName);
		    baseCommonService.saveOrUpdate(flowProcessInfo);
		} catch (Exception e) {
			
			logger.error("执行流程审批出错！", e.getMessage());
			throw new RuntimeException(e);
		}
		
	}


	/** 
	* @see com.ehs.security.flow.service.FlowBaseService#processEnd(java.lang.String)  
	* @Function: FlowBaseServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年8月5日 下午5:38:24 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年8月5日      chentm           v1.0.0               修改原因
	*/
	@Override
	@Transactional
	public void processEnd(FlowProcessInfo flowProcessInfo) {
		try {
			Task task= taskService.createTaskQuery().taskId(flowProcessInfo.getVars().get(FlowConstans.TASK_ID).toString()).singleResult();

			Authentication.setAuthenticatedUserId(SysAccessUser.get().getUserKey());
			taskService.addComment(task.getId(), flowProcessInfo.getFlowProcessInstanceId(), FlowTaskOper.AGREE.name(), StringUtils.defaultIfBlank((String)flowProcessInfo.getVars().get(FlowConstans.TASK_COMMENT),""));
			 Authentication.setAuthenticatedUserId(null);

			taskService.complete(task.getId());
			
		    String currentUser="";
		    String currentUserName="";
		    String currentStep="";
		    String currentStepName="";
		    flowProcessInfo.setFlowPrevPerson(flowProcessInfo.getFlowCurrentPerson());
		    flowProcessInfo.setFlowPrevPersonName(flowProcessInfo.getFlowCurrentPersonName());
		    flowProcessInfo.setFlowPrevStep(flowProcessInfo.getFlowCurrentStep());
		    flowProcessInfo.setFlowPrevStepName(flowProcessInfo.getFlowCurrentStepName());
		    flowProcessInfo.setFlowCurrentPerson(currentUser);
		    flowProcessInfo.setFlowCurrentPersonName(currentUserName);
		    flowProcessInfo.setFlowCurrentStep(currentStep);
		    flowProcessInfo.setFlowCurrentStepName(currentStepName);
		    baseCommonService.saveOrUpdate(flowProcessInfo);
		} catch (Exception e) {
			
			logger.error("执行流程审批出错！", e.getMessage());
			throw new RuntimeException(e);
		}
		
	}


	@Override
	@Transactional
	public void processCancel(FlowProcessInfo flowProcessInfo) {
		Authentication.setAuthenticatedUserId(SysAccessUser.get().getUserKey());
		runtimeService.deleteProcessInstance(flowProcessInfo.getFlowProcessInstanceId(),StringUtils.defaultIfBlank((String)flowProcessInfo.getVars().get(FlowConstans.FLOW_DELETE_REASON), ""));
		Authentication.setAuthenticatedUserId(null);
	    String currentUser="";
	    String currentUserName="";
	    String currentStep="";
	    String currentStepName="";
	    flowProcessInfo.setFlowPrevPerson(flowProcessInfo.getFlowCurrentPerson());
	    flowProcessInfo.setFlowPrevPersonName(flowProcessInfo.getFlowCurrentPersonName());
	    flowProcessInfo.setFlowPrevStep(flowProcessInfo.getFlowCurrentStep());
	    flowProcessInfo.setFlowPrevStepName(flowProcessInfo.getFlowCurrentStepName());
	    flowProcessInfo.setFlowCurrentPerson(currentUser);
	    flowProcessInfo.setFlowCurrentPersonName(currentUserName);
	    flowProcessInfo.setFlowCurrentStep(currentStep);
	    flowProcessInfo.setFlowCurrentStepName(currentStepName);
	    baseCommonService.saveOrUpdate(flowProcessInfo);

	}

}
