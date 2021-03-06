package com.djcps.wms.warehouse.model.area;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @title:仓库库区详情修改对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class UpdateAreaDetailBO implements Serializable{

	private static final long serialVersionUID = 1632348459664614422L;
	
	/**
	 *合作方id
	 */
	@NotBlank
	private String partnerId;
	
	/**
	 * 仓库编号
	 */
	@NotBlank
	private String warehouseId;
	
	/**
	 * 库区编号
	 */
	@NotBlank
	private String warehouseAreaId;
	
	/**
	 * 区名称
	 */
	@NotBlank
	private String countyName;
	
	/**
	 * 区编码
	 */
	@NotBlank
	private String countyCode;
	
	/**
	 * 街道名称
	 */
	@NotBlank
	private String streetName;
	
	/**
	 * 街道编码
	 */
	@NotBlank
	private String streetCode;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}

	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetCode() {
		return streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}

	@Override
	public String toString() {
		return "UpdateAreaDetailBO [partnerId=" + partnerId + ", warehouseId=" + warehouseId + ", warehouseAreaId="
				+ warehouseAreaId + ", countyName=" + countyName + ", countyCode=" + countyCode + ", streetName="
				+ streetName + ", streetCode=" + streetCode + "]";
	}
	
}
