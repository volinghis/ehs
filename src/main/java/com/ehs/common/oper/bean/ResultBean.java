/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.execute 
 * @author: chentm   
 * @date: 2019年6月24日 上午10:14:23 
 */
package com.ehs.common.oper.bean;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: ResultBean.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月24日 上午10:14:23 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月24日      chentm          v1.0.0               修改原因
*/
public  class ResultBean {



	public ResultBean ok(String message) {
		this.setMessage(message);
		this.setResultType("ok");
		return this;
	}
	public ResultBean ok(String message,String key) {
		this.setEntityKey(key);
		this.setMessage(message);
		this.setResultType("ok");
		return this;
	}
	public ResultBean error(String message) {
		this.setMessage(message);
		this.setResultType("error");
		return this;
	}

	
	private String message;
	
	
	
	
	private String resultType;

	private String entityKey;
	
	



	/**
	 * 
	 * @return
	 */
	private String getEntityKey() {
		return entityKey;
	}
	
	private void setEntityKey(String entityKey) {
		this.entityKey = entityKey;
	}

	

	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}



	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
