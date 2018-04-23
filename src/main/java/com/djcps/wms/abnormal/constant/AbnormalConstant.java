package com.djcps.wms.abnormal.constant;

/**
 * 异常订单常量
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
public class AbnormalConstant {
	
	/**常量类
	 * 入库环节
	 * ublic static final可以让访问变得很方便，而且不会被修改。一般可以放配置信息，还有一些状态码的定义。
	 */
	
	public static final String ABNORMAL_LINK_ADD_STOCK = "1";

	/**
	 * 盘点环节
	 */
	public static final String ABNORMAL_LINK_ADD_STOCKTAKING = "2";
	
	/**
	 * 处理状态,1,已处理
	 */
	public static final String ABNORMAL_STATUS = "1";
	
	public static String ABNORMAL_ERROR_REASON = "少数";

	public static String ABNORMAL_ERROR_MORE = "盘盈";

	public static String ABNORMAL_ERROR_less = "盘亏";
	
	public static final String ABNORMAL_REASON_NULL = "无";

	public static void setAbnormalErrorReason(String abnormalErrorReason) {
		ABNORMAL_ERROR_REASON = abnormalErrorReason;
	}

	public static String getAbnormalErrorReason() {
		return ABNORMAL_ERROR_REASON;
	}
	
}
