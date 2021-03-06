package com.djcps.wms.cancelstock.model.param;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseOperationRecordBO;

/**
 * 新增在库信息
 * @company:djwms
 * @author:zdx
 * @date:2018年3月19日
 */
public class AddStockBO extends BaseOperationRecordBO implements Serializable{

	private static final long serialVersionUID = -4873944563866619544L;
	
	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	@NotBlank
	private String orderId;
	/**
	 * 实时在此库位的数量
	 */
	@NotNull
	private Integer amount;
	/**
	 * 库区id
	 */
	@NotBlank
	private String warehouseAreaId;
	/**
	 * 库区名称
	 */
	@NotBlank
	private String warehouseAreaName;
	/**
	 * 库位id
	 */
	@NotBlank
	private String warehouseLocId;
	/**
	 * 库位名称
	 */
	@NotBlank
	private String warehouseLocName;
	
	/**
	 * 退库人
	 */
	@NotBlank
	private String pickerId;
	
	/**
	 * 仓库编码
	 */
	@NotBlank
	private String warehouseId;
	/**
	 * 仓库名称
	 */
	@NotBlank
	private String warehouseName;
	
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getPickerId() {
		return pickerId;
	}
	public void setPickerId(String pickerId) {
		this.pickerId = pickerId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}
	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}
	public String getWarehouseAreaName() {
		return warehouseAreaName;
	}
	public void setWarehouseAreaName(String warehouseAreaName) {
		this.warehouseAreaName = warehouseAreaName;
	}
	public String getWarehouseLocId() {
		return warehouseLocId;
	}
	public void setWarehouseLocId(String warehouseLocId) {
		this.warehouseLocId = warehouseLocId;
	}
	public String getWarehouseLocName() {
		return warehouseLocName;
	}
	public void setWarehouseLocName(String warehouseLocName) {
		this.warehouseLocName = warehouseLocName;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "AddStockBO [id=" + id + ", orderId=" + orderId + ", amount=" + amount + ", warehouseAreaId="
				+ warehouseAreaId + ", warehouseAreaName=" + warehouseAreaName + ", warehouseLocId=" + warehouseLocId
				+ ", warehouseLocName=" + warehouseLocName + ", pickerId=" + pickerId + ", warehouseId=" + warehouseId
				+ ", warehouseName=" + warehouseName + "]";
	}
}
