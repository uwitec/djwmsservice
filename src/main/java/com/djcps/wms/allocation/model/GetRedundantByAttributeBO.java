package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;

/**
 * 配货和配货管理模糊查询属性
 * @company:djwms
 * @author:zdx
 * @date:2018年1月26日
 */
public class GetRedundantByAttributeBO extends BaseListPartnerIdBO implements Serializable{

	private static final long serialVersionUID = 7980143889127607252L;
	/**
	 * 合作方名称
	 */
	@NotBlank
	private String partnerName;
	/**
	 * 合作方区域
	 */
	@NotBlank
	private String partnerArea;
	
	/**
	 * 操作人id
	 */
	@NotBlank
	private String operatorId;
	
	/**
	 * 操作人名称
	 */
	@NotBlank
	private String operator;
	
	/**
	 * 运单号
	 */
	private String waybillId;
	/**
	 * 提货单号
	 */
	private String deliveryId;
	/**
	 * 订单状态
	 */
	private String orderStatus;
	/**
	 * 运单状态
	 */
	private String waybillIdStatus;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 材料名称
	 */
	private String materialName;
	/**
	 * 下单结束时间 
	 */
	private String orderEndTime;
	/**
	 * 下单开始时间
	 */
	private String orderStartTime;
	/**
	 * 交期结束时间
	 */
	private String deliveryEndTime;
	/**
	 * 交期开始时间
	 */
	private String deliveryStartTime;
	/**
	 * 支付结束时间
	 */
	private String paymentEndTime;
	/**
	 * 支付开始时间
	 */
	private String paymentStartTime;
	
	/**
	 * 配货时间,也就是提货单创建时间
	 */
	private String deliveryCreateTime;
	
	/**
	 * 车牌号
	 */
	private String plateNumber;
	
	/**
	 * 查询标记,flag为0,则表示没有查询条件,为1表中有查询条件
	 */
	private String flag;
	/**
	 * 配货开始时间
	 */
	private String deliveryCreateStartTime;
	/**
	 * 配货结束时间
	 */
	private String deliveryCreateEndTime;
	
	/**
	 * 区分是智能配货结果页面获取追加订单,还是装车优化界面获取追加订单
	 */
	private String cache;
	
	private String allocationId;
	
	/**
	 * 参与智能配货或者装车优化的订单号
	 */
	private List<String> orderIdList;
	
	/**
	 * 订单类型
	 */
	private String orderType;
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public List<String> getOrderIdList() {
		return orderIdList;
	}

	public void setOrderIdList(List<String> orderIdList) {
		this.orderIdList = orderIdList;
	}

	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	public String getCache() {
		return cache;
	}

	public void setCache(String cache) {
		this.cache = cache;
	}

	public String getDeliveryCreateStartTime() {
		return deliveryCreateStartTime;
	}

	public void setDeliveryCreateStartTime(String deliveryCreateStartTime) {
		this.deliveryCreateStartTime = deliveryCreateStartTime;
	}

	public String getDeliveryCreateEndTime() {
		return deliveryCreateEndTime;
	}

	public void setDeliveryCreateEndTime(String deliveryCreateEndTime) {
		this.deliveryCreateEndTime = deliveryCreateEndTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

	public String getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getWaybillIdStatus() {
		return waybillIdStatus;
	}

	public void setWaybillIdStatus(String waybillIdStatus) {
		this.waybillIdStatus = waybillIdStatus;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryCreateTime() {
		return deliveryCreateTime;
	}

	public void setDeliveryCreateTime(String deliveryCreateTime) {
		this.deliveryCreateTime = deliveryCreateTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public String getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public String getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(String deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(String deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public String getPaymentEndTime() {
		return paymentEndTime;
	}

	public void setPaymentEndTime(String paymentEndTime) {
		this.paymentEndTime = paymentEndTime;
	}

	public String getPaymentStartTime() {
		return paymentStartTime;
	}

	public void setPaymentStartTime(String paymentStartTime) {
		this.paymentStartTime = paymentStartTime;
	}
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "GetRedundantByAttributeBO [partnerName=" + partnerName + ", partnerArea=" + partnerArea
				+ ", operatorId=" + operatorId + ", operator=" + operator + ", waybillId=" + waybillId + ", deliveryId="
				+ deliveryId + ", orderStatus=" + orderStatus + ", waybillIdStatus=" + waybillIdStatus + ", orderId="
				+ orderId + ", customerName=" + customerName + ", productName=" + productName + ", materialName="
				+ materialName + ", orderEndTime=" + orderEndTime + ", orderStartTime=" + orderStartTime
				+ ", deliveryEndTime=" + deliveryEndTime + ", deliveryStartTime=" + deliveryStartTime
				+ ", paymentEndTime=" + paymentEndTime + ", paymentStartTime=" + paymentStartTime
				+ ", deliveryCreateTime=" + deliveryCreateTime + ", plateNumber=" + plateNumber + ", flag=" + flag
				+ ", deliveryCreateStartTime=" + deliveryCreateStartTime + ", deliveryCreateEndTime="
				+ deliveryCreateEndTime + ", cache=" + cache + ", allocationId=" + allocationId + ", orderIdList="
				+ orderIdList + ", orderType=" + orderType + "]";
	}

}
