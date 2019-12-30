/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamAccountPrint.entity.entitySuper 
 * @author: qjj   
 * @date: 2019年10月31日 下午1:56:48 
 */
package com.ehs.eam.eamLedgerManager.entity.entitySuper;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamAccountPrint.java
* @Description: 设备台账实体类
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年10月31日 下午1:56:48 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年10月31日     qjj           v1.0.0               修改原因
*/
@MappedSuperclass
public abstract class EamLedger extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;
	
	public static final String DEVICE_NUM = "deviceNum";
	public static final String DEVICE_NAME = "deviceName";
	public static final String DEVICE_MODEL = "deviceModel";
	public static final String FACTORY_NAME = "factoryName";
	public static final String SUPPLIER = "supplier";
	public static final String DEVICE_STATUS = "deviceStatus";
	public static final String RUN_DATE = "runDate";
	public static final String INSTALL_LOCATION = "installLocation";
	public static final String PURCHASE_TIME = "purchaseTime";
	public static final String WARRANTY = "warranty";
	public static final String SERVICE_LIFE = "serviceLife";
	public static final String REPAIR_FREQUENCY = "repairFrequency";
	public static final String BUYING_PRICE = "buyingPrice";
	public static final String PERSON = "person";
	public static final String FILE_ID = "fileId";
	public static final String REPAIR_NUMBER = "repairNumber";
	public static final String FAULTS_NUMBER = "faultsNumber";
	public static final String REMARKS = "remarks";

	/**
	 * 设备编码
	 */
	private String deviceNum;

	/**
	 * 设备名称
	 */
	private String deviceName;

	/**
	 * 规格型号
	 */
	private String deviceModel;

	/**
	 * 生产厂家
	 */
	private String factoryName;

	/**
	 * 供应商
	 */
	private String supplier;

	/**
	 * 设备状态
	 */
	private String deviceStatus;

	/**
	 * 启用日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp runDate;

	/**
	 * 安装位置
	 */
	private String installLocation;

	/**
	 * 采购时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Timestamp purchaseTime;

	/**
	 * 保修期
	 */
	private String warranty;

	/**
	 * 使用寿命
	 */
	private String serviceLife;

	/**
	 * 检修频率
	 */
	private Byte repairFrequency;

	/**
	 * 采购价格
	 */
	private BigDecimal buyingPrice;

	/**
	 * 负责人
	 */
	private String person;

	/**
	 * 设备图片
	 */
	private String fileId;

	/**
	 * 检修数量
	 */
	private Integer repairNumber;

	/**
	 * 故障数量
	 */
	private Integer faultsNumber;

	/**
	 * 备注
	 */
	@Column(length = 3000)
	private String remarks;

	/**
	 * @return the deviceNum
	 */
	public String getDeviceNum() {
		return deviceNum;
	}

	/**
	 * @param deviceNum the deviceNum to set
	 */
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the deviceModel
	 */
	public String getDeviceModel() {
		return deviceModel;
	}

	/**
	 * @param deviceModel the deviceModel to set
	 */
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	/**
	 * @return the factoryName
	 */
	public String getFactoryName() {
		return factoryName;
	}

	/**
	 * @param factoryName the factoryName to set
	 */
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	/**
	 * @return the supplier
	 */
	public String getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the deviceStatus
	 */
	public String getDeviceStatus() {
		return deviceStatus;
	}

	/**
	 * @param deviceStatus the deviceStatus to set
	 */
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	/**
	 * @return the runDate
	 */
	public Timestamp getRunDate() {
		return runDate;
	}

	/**
	 * @param runDate the runDate to set
	 */
	public void setRunDate(Timestamp runDate) {
		this.runDate = runDate;
	}

	/**
	 * @return the installLocation
	 */
	public String getInstallLocation() {
		return installLocation;
	}

	/**
	 * @param installLocation the installLocation to set
	 */
	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}

	/**
	 * @return the purchaseTime
	 */
	public Timestamp getPurchaseTime() {
		return purchaseTime;
	}

	/**
	 * @param purchaseTime the purchaseTime to set
	 */
	public void setPurchaseTime(Timestamp purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	/**
	 * @return the warranty
	 */
	public String getWarranty() {
		return warranty;
	}

	/**
	 * @param warranty the warranty to set
	 */
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	/**
	 * @return the serviceLife
	 */
	public String getServiceLife() {
		return serviceLife;
	}

	/**
	 * @param serviceLife the serviceLife to set
	 */
	public void setServiceLife(String serviceLife) {
		this.serviceLife = serviceLife;
	}

	/**
	 * @return the repairFrequency
	 */
	public Byte getRepairFrequency() {
		return repairFrequency;
	}

	/**
	 * @param repairFrequency the repairFrequency to set
	 */
	public void setRepairFrequency(Byte repairFrequency) {
		this.repairFrequency = repairFrequency;
	}

	/**
	 * @return the buyingPrice
	 */
	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}

	/**
	 * @param buyingPrice the buyingPrice to set
	 */
	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	/**
	 * @return the person
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
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

	/**
	 * @return the repairNumber
	 */
	public Integer getRepairNumber() {
		return repairNumber;
	}

	/**
	 * @param repairNumber the repairNumber to set
	 */
	public void setRepairNumber(Integer repairNumber) {
		this.repairNumber = repairNumber;
	}

	/**
	 * @return the faultsNumber
	 */
	public Integer getFaultsNumber() {
		return faultsNumber;
	}

	/**
	 * @param faultsNumber the faultsNumber to set
	 */
	public void setFaultsNumber(Integer faultsNumber) {
		this.faultsNumber = faultsNumber;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
