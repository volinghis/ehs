package com.ehs.common.base.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.ehs.common.auth.local.SysAccessUser;
import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.data.DataModel;
import com.ehs.common.base.utils.BaseUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





/**
 * 
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: BaseEntity.java
 * @Description: 基本类，包含公用实体映射关系
 *
 * @version: v1.0.0
 * @author: chentm
 * @date: 2019年5月6日 上午10:52:37
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年5月6日
 *        chentm v1.0.0 无修改
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public  abstract class BaseEntity implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	public static final String ID = "id" ;
	public static final String KEY = "key" ;
	public static final String VERSION_ID = "versionId" ;
	public static final String OWNER = "owner" ;
	public static final String OWNER_ORG = "ownerOrg" ;
	public static final String OWNER_CREATION_TIME = "ownerCreationTime" ;
	public static final String CREATION = "creation" ;
	public static final String CREATION_ORG = "creationOrg" ;
	public static final String CREATION_TIME = "creationTime" ;
	public static final String DATA_MODEL = "dataModel" ;
	public static final String ATTRIBUTE1 = "attribute1" ;
	public static final String ATTRIBUTE2 = "attribute2" ;
	public static final String ATTRIBUTE3 = "attribute3" ;
	public static final String ATTRIBUTE4 = "attribute4" ;
	public static final String ATTRIBUTE5 = "attribute5" ;
	public static final String COMPLETE_POINT = "completePoint" ;
	
	
	private Byte completePoint=0;
	
	@Transient
	private Boolean reCompletePoint=true;



	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	/**
	 * 数据编号
	 */
	@Column(name="data_key")
	private String key;

	/**
	 * 数据版本号,0有效，1失效
	 */
	@Version
	private Long versionId;

	/**
	 * 数据拥有者账号
	 */
	private String owner;
	
	private String ownerName;
	
	private String ownerOrg;
	
	private String ownerOrgName;
	/**
	 * 数据拥有者创建时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
	private Timestamp ownerCreationTime;

	private String creation;
	
	private String creationName;
	
	
	private String creationOrg;
	private String creationOrgName;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone="GMT+8")
	private Timestamp creationTime;
	
	/**
	 * CREATE,UPDATE,REMOVE
	 */
	@Enumerated(EnumType.STRING)
	private DataModel dataModel;
	
	
	
	
	

	


	/**
	 * 扩展字段1
	 */
	private String attribute1;

	/**
	 * 扩展字段金2
	 */
	private String attribute2;

	/**
	 * 扩展字段3
	 */
	private String attribute3;
	/**
	 * 扩展字段4
	 */
	private String attribute4;
	/**
	 *  
	 * 扩展字段5
	 */
	private String attribute5;

	
	
	

	public Boolean getReCompletePoint() {
		return reCompletePoint;
	}
	public void setReCompletePoint(Boolean reCompletePoint) {
		this.reCompletePoint = reCompletePoint;
	}
	public Byte getCompletePoint() {
		return completePoint;
	}
	public void setCompletePoint(Byte completePoint) {
		this.completePoint = completePoint;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerOrgName() {
		return ownerOrgName;
	}
	public void setOwnerOrgName(String ownerOrgName) {
		this.ownerOrgName = ownerOrgName;
	}
	public String getCreationName() {
		return creationName;
	}
	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}
	public String getCreationOrgName() {
		return creationOrgName;
	}
	public void setCreationOrgName(String creationOrgName) {
		this.creationOrgName = creationOrgName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		if(StringUtils.isNotBlank(this.key)&&!StringUtils.equals(key, this.key)) {
			throw new RuntimeException("数据唯一标识key不可修改");
		}
		this.key = key;
	}
	public Long getVersionId() {
		return versionId;
	}
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public Timestamp getOwnerCreationTime() {
		return ownerCreationTime;
	}
	public void setOwnerCreationTime(Timestamp ownerCreationTime) {
		this.ownerCreationTime = ownerCreationTime;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	public String getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	/**
	 * 
	* @Function: BaseEntity.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 上午9:38:38 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日     chentm           v1.0.0               修改原因
	 */
	public void initCreate() {
		Timestamp _ts=com.ehs.common.base.utils.BaseUtils.getNow();
		this.setCreationTime(_ts);
		this.setOwnerCreationTime(_ts);
		this.setDataModel(DataModel.CREATE);
		if(StringUtils.isBlank(this.getKey())){
			this.setKey(UUID.randomUUID().toString());
		}
		if(SysAccessUser.get()!=null&&StringUtils.isNotBlank(SysAccessUser.get().getUserKey())) {
			this.setCreation(SysAccessUser.get().getUserKey());
			this.setOwner(SysAccessUser.get().getUserKey());
			this.setOwnerName(SysAccessUser.get().getUsername());
		}
		if(SysAccessUser.get()!=null&&StringUtils.isNotBlank(SysAccessUser.get().getOrgKey())) {
			this.setCreationOrg(SysAccessUser.get().getOrgKey());
			this.setOwnerOrg(SysAccessUser.get().getOrgKey());
			this.setOwnerOrgName(SysAccessUser.get().getOrgName());
		}
	}
	/**
	 * 
	* @Function: BaseEntity.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月8日 上午9:39:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月8日     chentm           v1.0.0               修改原因
	 */
	public void initUpdate() {
		this.setCreationTime(BaseUtils.getNow());
		if(SysAccessUser.get()!=null&&StringUtils.isNotBlank(SysAccessUser.get().getUserKey())) {
			this.setCreation(SysAccessUser.get().getUserKey());
			this.setCreationName(SysAccessUser.get().getUsername());
		}
		if(SysAccessUser.get()!=null&&StringUtils.isNotBlank(SysAccessUser.get().getOrgKey())) {
			this.setCreationOrg(SysAccessUser.get().getOrgKey());
			this.setCreationOrgName(SysAccessUser.get().getOrgName());
		}
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwnerOrg() {
		return ownerOrg;
	}
	public void setOwnerOrg(String ownerOrg) {
		this.ownerOrg = ownerOrg;
	}
	public String getCreation() {
		return creation;
	}
	public void setCreation(String creation) {
		this.creation = creation;
	}
	public String getCreationOrg() {
		return creationOrg;
	}
	public void setCreationOrg(String creationOrg) {
		this.creationOrg = creationOrg;
	}
	public DataModel getDataModel() {
		return dataModel;
	}
	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}	
	

}
