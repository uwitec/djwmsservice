package com.djcps.wms.stock.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBo;
import com.djcps.wms.commons.base.BaseParam;

/**
 * 移库对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
public class MoveStock extends BaseAddBo implements Serializable{

	private static final long serialVersionUID = 2489810729435810994L;
	
	/**
	 * 订单编号
	 */
	@NotBlank
	private String orderId;
	/**
	 * 准备移库数量
	 */
	@NotBlank
	private String amountSave;
	
	/**
	 * 新库区编号
	 */
	@NotBlank
	private String warehouseAreaId;
	/**
	 * 原库区编号
	 */
	@NotBlank
	private String originalWarehouseAreaId;
	/**
	 * 新库区名称
	 */
	@NotBlank
	private String warehouseAreaName;
	/**
	 * 新库位编码
	 */
	@NotBlank
	private String warehouseLocId;
	/**
	 * 原库位编号
	 */
	@NotBlank
	private String originalWarehouseLocId;
	/**
	 * 新库位名称
	 */
	@NotBlank
	private String warehouseLocName;
	/**
	 * 操作记录信息
	 */
	@NotNull
	private OperationRecordBo operationRecord;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmountSave() {
		return amountSave;
	}
	public void setAmountSave(String amountSave) {
		this.amountSave = amountSave;
	}
	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}
	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}
	public String getOriginalWarehouseAreaId() {
		return originalWarehouseAreaId;
	}
	public void setOriginalWarehouseAreaId(String originalWarehouseAreaId) {
		this.originalWarehouseAreaId = originalWarehouseAreaId;
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
	public String getOriginalWarehouseLocId() {
		return originalWarehouseLocId;
	}
	public void setOriginalWarehouseLocId(String originalWarehouseLocId) {
		this.originalWarehouseLocId = originalWarehouseLocId;
	}
	public String getWarehouseLocName() {
		return warehouseLocName;
	}
	public void setWarehouseLocName(String warehouseLocName) {
		this.warehouseLocName = warehouseLocName;
	}
	public OperationRecordBo getOperationRecord() {
		return operationRecord;
	}
	public void setOperationRecord(OperationRecordBo operationRecord) {
		this.operationRecord = operationRecord;
	}
	@Override
	public String toString() {
		return "MoveStock [orderId=" + orderId + ", amountSave=" + amountSave + ", warehouseAreaId=" + warehouseAreaId
				+ ", originalWarehouseAreaId=" + originalWarehouseAreaId + ", warehouseAreaName=" + warehouseAreaName
				+ ", warehouseLocId=" + warehouseLocId + ", originalWarehouseLocId=" + originalWarehouseLocId
				+ ", warehouseLocName=" + warehouseLocName + ", operationRecord=" + operationRecord + "]";
	}

}