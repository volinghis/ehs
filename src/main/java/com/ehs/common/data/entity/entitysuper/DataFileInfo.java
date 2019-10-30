/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年6月26日 下午4:22:40 
 */
package com.ehs.common.data.entity.entitysuper;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FileInfo.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月26日 下午4:22:40 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日      chentm          v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class DataFileInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	public static final String NAME="name";
	public static final String TYPE="type";
	public static final String SIZE="size";
	public static final String ENTITY_KEY="entityKey";
	public static final String FILE_ID="fileId";
	
	private String name;
	
	private String type;
	
	private String fileSize;
	
	
	private String entityKey;
	
	private String fileId;
	
	
	
	
	
	






	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}












	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}












	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}












	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
























	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}












	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}



	public String getEntityKey() {
		return entityKey;
	}



	public void setEntityKey(String entityKey) {
		this.entityKey = entityKey;
	}


	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}












	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}











	
}
