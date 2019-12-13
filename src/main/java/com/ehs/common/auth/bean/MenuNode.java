package com.ehs.common.auth.bean;

import java.util.List;

public class MenuNode {

	/**
	 * id
	 */
	private String code;
	
	/**
	 * 名称
	 */
	private String label;
	
	/**
	 * 唯一值
	 */
	private String key;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * path路径
	 */
	private String path;
	
	/**
	 * 组件路径
	 */
	private String component;
	
	/**
	 * 是否为叶子
	 */
	private Boolean leaf;
	
	/**
	 * 菜单是否显示
	 */
	private Boolean business;
	
	/**
	 * 子节点
	 */
	private List<MenuNode> children;
	
	
	
	



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public List<MenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getBusiness() {
		return business;
	}

	public void setBusiness(Boolean business) {
		this.business = business;
	}
	
}
