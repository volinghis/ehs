package com.ehs.common.flow.entity.entitysuper;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.flow.enums.FlowStatus;

@MappedSuperclass
public abstract class FlowProcessInfoSuper extends BaseEntity{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	
	public static final String FLOW_STATUS = "flowStatus" ;
	public static final String FLOW_CURRENT_STEP = "flowCurrentStep" ;
	public static final String FLOW_CURRENT_STEP_NAME = "flowCurrentStepName" ;
	public static final String FLOW_CURRENT_PERSON = "flowCurrentPerson" ;
	public static final String FLOW_CURRENT_PERSON_NAME = "flowCurrentPersonName" ;
	public static final String FLOW_PREV_PERSON = "flowPrevPerson" ;
	public static final String FLOW_PREV_PERSON_NAME = "flowPrevPersonName" ;
	public static final String FLOW_PREV_STEP = "flowPrevStep" ;
	public static final String FLOW_PREV_STEP_NAME = "flowPrevStepName" ;
	public static final String FLOW_PROCESS_INSTANCE_ID = "flowProcessInstanceId" ;
	public static final String BUSINESS_ENTITY_KEY = "businessEntityKey" ;
	
	
	private String businessEntityKey;
	
	/**
	 * 流程实例ID
	 */
	private String flowProcessInstanceId;
	
	
	@Transient
	private Map<String,Object> vars;
	


	/**
	 * 审批流程状态 APPROVING,END,CANCELED
	 */
	@Enumerated(EnumType.STRING)
	private FlowStatus flowStatus=FlowStatus.APPROVING;
	
	
	/**
	 * 当前环节
	 */
	private String flowCurrentStep;
	
	private String flowCurrentStepName;
	
	/**
	 * 当前处理人
	 */
	@Column(length = 3000)
	private String flowCurrentPerson;
	
	private String flowCurrentPersonName;
	
	

	/**
	 * 上 一环节处理人
	 */
	@Column(length = 3000)
	private String flowPrevPerson;
	
	private String flowPrevPersonName;
	/**
	 * 上一环节代码
	 */
	private String flowPrevStep;
	
	/**
	 * 上一环节名称
	 */
	private String flowPrevStepName;
	



	
	
	
	

	public String getBusinessEntityKey() {
		return businessEntityKey;
	}
	public void setBusinessEntityKey(String businessEntityKey) {
		this.businessEntityKey = businessEntityKey;
	}
	public FlowStatus getFlowStatus() {
		return flowStatus;
	}
	public void setFlowStatus(FlowStatus flowStatus) {
		this.flowStatus = flowStatus;
	}
	public Map<String, Object> getVars() {
		return vars;
	}
	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}

	/**
	 * @return the flowCurrentStep
	 */
	public String getFlowCurrentStep() {
		return flowCurrentStep;
	}
	/**
	 * @param flowCurrentStep the flowCurrentStep to set
	 */
	public void setFlowCurrentStep(String flowCurrentStep) {
		this.flowCurrentStep = flowCurrentStep;
	}
	/**
	 * @return the flowCurrentStepName
	 */
	public String getFlowCurrentStepName() {
		return flowCurrentStepName;
	}
	/**
	 * @param flowCurrentStepName the flowCurrentStepName to set
	 */
	public void setFlowCurrentStepName(String flowCurrentStepName) {
		this.flowCurrentStepName = flowCurrentStepName;
	}
	/**
	 * @return the flowCurrentPerson
	 */
	public String getFlowCurrentPerson() {
		return flowCurrentPerson;
	}
	/**
	 * @param flowCurrentPerson the flowCurrentPerson to set
	 */
	public void setFlowCurrentPerson(String flowCurrentPerson) {
		this.flowCurrentPerson = flowCurrentPerson;
	}
	/**
	 * @return the flowCurrentPersonName
	 */
	public String getFlowCurrentPersonName() {
		return flowCurrentPersonName;
	}
	/**
	 * @param flowCurrentPersonName the flowCurrentPersonName to set
	 */
	public void setFlowCurrentPersonName(String flowCurrentPersonName) {
		this.flowCurrentPersonName = flowCurrentPersonName;
	}
	/**
	 * @return the flowPrevPerson
	 */
	public String getFlowPrevPerson() {
		return flowPrevPerson;
	}
	/**
	 * @param flowPrevPerson the flowPrevPerson to set
	 */
	public void setFlowPrevPerson(String flowPrevPerson) {
		this.flowPrevPerson = flowPrevPerson;
	}
	/**
	 * @return the flowPrevPersonName
	 */
	public String getFlowPrevPersonName() {
		return flowPrevPersonName;
	}
	/**
	 * @param flowPrevPersonName the flowPrevPersonName to set
	 */
	public void setFlowPrevPersonName(String flowPrevPersonName) {
		this.flowPrevPersonName = flowPrevPersonName;
	}
	/**
	 * @return the flowPrevStep
	 */
	public String getFlowPrevStep() {
		return flowPrevStep;
	}
	/**
	 * @param flowPrevStep the flowPrevStep to set
	 */
	public void setFlowPrevStep(String flowPrevStep) {
		this.flowPrevStep = flowPrevStep;
	}
	/**
	 * @return the flowPrevStepName
	 */
	public String getFlowPrevStepName() {
		return flowPrevStepName;
	}
	/**
	 * @param flowPrevStepName the flowPrevStepName to set
	 */
	public void setFlowPrevStepName(String flowPrevStepName) {
		this.flowPrevStepName = flowPrevStepName;
	}


	/**
	 * @return the flowProcessInstanceId
	 */
	public String getFlowProcessInstanceId() {
		return flowProcessInstanceId;
	}
	/**
	 * @param flowProcessInstanceId the flowProcessInstanceId to set
	 */
	public void setFlowProcessInstanceId(String flowProcessInstanceId) {
		this.flowProcessInstanceId = flowProcessInstanceId;
	}
	

}
