package com.djcps.wms.permission.model.bo;

import java.io.Serializable;

/**
 * @author zhq
 * 获取组合权限列表
 * 2018年4月12日
 */
public class GetPermissionBO implements Serializable{

	private static final long serialVersionUID = 8671143928748955433L;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 业务模块
	 */
	private String business;
	/**
	 * 公司id
	 */
	private String companyID;
	/**
	 * 当前页
	 */
	private String page;
	/**
	 * 关键字
	 */
	private String keyword;
	/**
	 * 分页大小
	 */
	private String pageSize;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "GetPermissionBO [operator=" + operator + ", ip=" + ip + ", business=" + business + ", companyID="
				+ companyID + ", page=" + page + ", keyword=" + keyword + ", pageSize=" + pageSize + "]";
	}
	
}
