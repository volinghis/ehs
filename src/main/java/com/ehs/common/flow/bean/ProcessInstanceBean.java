package com.ehs.common.flow.bean;

import java.util.List;

public class ProcessInstanceBean {


	
	private String processInstanceKey;
	
	private ProcessStepBean currentStep;
	
	private ProcessStepBean prevStep;
	
	private List<ProcessStepBean> nextStep;
	
	
	


	public String getProcessInstanceKey() {
		return processInstanceKey;
	}

	public void setProcessInstanceKey(String processInstanceKey) {
		this.processInstanceKey = processInstanceKey;
	}

	public ProcessStepBean getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(ProcessStepBean currentStep) {
		this.currentStep = currentStep;
	}

	public ProcessStepBean getPrevStep() {
		return prevStep;
	}

	public void setPrevStep(ProcessStepBean prevStep) {
		this.prevStep = prevStep;
	}

	public List<ProcessStepBean> getNextStep() {
		return nextStep;
	}

	public void setNextStep(List<ProcessStepBean> nextStep) {
		this.nextStep = nextStep;
	}


	

	
}
