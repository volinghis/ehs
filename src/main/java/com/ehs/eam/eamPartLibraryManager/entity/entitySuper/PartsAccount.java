package com.ehs.eam.eamPartLibraryManager.entity.entitySuper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class PartsAccount extends BaseEntity {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

	public static final String DEVICE_NAME="deviceName";
	public static final String DEVICE_CODE="deviceCode";
	public static final String MODEL="model";
	public static final String CATEGORY="category";
	public static final String TYPE="type";
	public static final String BRAND="brand";
	public static final String NORM="norm";
	public static final String WAREHOUSE="warehouse";
	public static final String UNIT="unit";
	public static final String PRICE="price";
	public static final String AMOUNT="amount";
	public static final String WARNING_VALUE="warningValue";
	public static final String MANUFACTURER="manufacturer";
	public static final String SUPPLIER="supplier";
	public static final String MATERIAL_TYPE_NAME="materialTypeName"; 
	public static final String LABEL_CODE="labelCode";
	public static final String USE_LIFE="useLife";
	public static final String WARRANTY_PERIOD="warrantyPeriod";
	public static final String SCRAPPED_TIME="scrappedTime";
	public static final String BUY_TIME="buyTime";
	public static final String REMARK="remark";
	public static final String SORT="sort";
	
	/**
	 * 备件名称
	 */
	private String deviceName;
	
	/**
	 * 备件编码
	 */
	private String deviceCode;
	
	/**
	 * 备件型号
	 */
	private String model;
	
	/**
	 * 设备类别
	 */
	private String category;
	
	/**
	 * 备件类型
	 */
	private String type;
	
	/**
	 * 品牌
	 */
	private String brand;
	
	/**
	 * 规格
	 */
	private String norm;
	
	/**
	 * 所在仓库
	 */
	private String warehouse;
	
	/**
	 * 单位
	 */
	private char unit;
	
	/**
	 * 价格
	 */
	private BigDecimal price;
	
	/**
	 * 数量
	 */
	private Integer amount;
	
	/**
	 * 预警值
	 */
	private Integer warningValue;
	
	/**
	 * 生产厂商
	 */
	private String manufacturer;
	
	/**
	 * 供应商
	 */
	private String supplier;
	
	/**
	 * 物资类型
	 */
	private String materialTypeName;
	
	/**
	 * 标签码
	 */
	private String labelCode;
	
	/**
	 * 使用寿命
	 */
	private String useLife;
	
	/**
	 * 保修期
	 */
	private String warrantyPeriod;
	
	/**
	 * 报废时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Timestamp scrappedTime;
	
	/**
	 * 购买时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Timestamp buyTime;
	
	/**
	 * 备注
	 */
	@Column(length = 3000)
	private String remark;
	
	/**
	 * 排序
	 */
	private Integer sort; 
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public char getUnit() {
		return unit;
	}

	public void setUnit(char unit) {
		this.unit = unit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getWarningValue() {
		return warningValue;
	}

	public void setWarningValue(Integer warningValue) {
		this.warningValue = warningValue;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUseLife() {
		return useLife;
	}

	public void setUseLife(String useLife) {
		this.useLife = useLife;
	}

	public String getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(String warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public Timestamp getScrappedTime() {
		return scrappedTime;
	}

	public void setScrappedTime(Timestamp scrappedTime) {
		this.scrappedTime = scrappedTime;
	}

	public Timestamp getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Timestamp buyTime) {
		this.buyTime = buyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
