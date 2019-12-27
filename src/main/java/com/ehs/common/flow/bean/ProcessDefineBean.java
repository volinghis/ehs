package com.ehs.common.flow.bean;

import java.util.List;

public class ProcessDefineBean {

	private String key;
	private String name;
	private List<ProcessStepBean> steps;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProcessStepBean> getSteps() {
		return steps;
	}
	public void setSteps(List<ProcessStepBean> steps) {
		this.steps = steps;
	}
	
	
}
