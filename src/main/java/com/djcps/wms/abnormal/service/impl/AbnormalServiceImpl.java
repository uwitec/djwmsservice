package com.djcps.wms.abnormal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.abnormal.constant.AbnormalConstant;
import com.djcps.wms.abnormal.enums.AbnormalMsgEnum;
import com.djcps.wms.abnormal.model.AddAbnormal;
import com.djcps.wms.abnormal.model.GetOrderByAttributeBO;
import com.djcps.wms.abnormal.model.OrderIdBO;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;
import com.djcps.wms.abnormal.server.AbnormalServer;
import com.djcps.wms.abnormal.service.AbnormalService;
import com.djcps.wms.address.model.ProvinceCityAreaCodeBO;
import com.djcps.wms.allocation.server.AllocationServer;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtask.constant.LoadingTaskConstant;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.server.OrderServer;

/**
 * 异常订单业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
@Service
public class AbnormalServiceImpl implements AbnormalService {
	
	@Autowired
	private AbnormalServer abnormalServer;
	@Autowired
	private OrderServer orderServer;
	
	
	@Override
	public Map<String, Object> getOrderByAttributeBO(GetOrderByAttributeBO param) {
		HttpResult result = abnormalServer.getOrderByAttributeBO(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getSplitOrderByOrderId(OrderIdBO param) {
		HttpResult result = abnormalServer.getSplitOrderByOrderId(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> updateAbnormal(UpdateAbnormalBO param) {
		param.setSubmiter(param.getOperator());
		HttpResult result = abnormalServer.updateAbnormal(param);
		
		//OMS处理订单提醒状态
		if(result.isSuccess()){
			String orderId = param.getOrderId();
			String updateResult = param.getResult();
			List<UpdateSplitOrderBO> list = new ArrayList<>();
			UpdateSplitOrderBO update = new UpdateSplitOrderBO();
			update.setSubOrderId(orderId);
			update.setKeyArea(param.getPartnerArea());
			if(updateResult.equals(AbnormalConstant.ABNORMAL_UPDATE_1)){
				update.setIsProduce(1);
				update.setIsException(0);
			}else if(updateResult.equals(AbnormalConstant.ABNORMAL_UPDATE_2)){
				update.setIsStored(1);
				update.setIsException(0);
			}else if(updateResult.equals(AbnormalConstant.ABNORMAL_UPDATE_3)){
				update.setIsException(0);
			}else if(updateResult.equals(AbnormalConstant.ABNORMAL_UPDATE_4)){
				update.setIsException(0);
			}
			list.add(update);
			result = orderServer.updateSplitOrderInfo(list);
		}
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> addAbnormal(AddAbnormal param) {
		//新增异常订单逻辑,新增时,需要修改OMS处订单的异常状态
		HttpResult result = abnormalServer.addAbnormal(param);
		if(result.isSuccess()){
			result = abnormalServer.updateExecptionFlag(1, param.getOrderId(),param.getPartnerArea());
			if(!result.isSuccess()){
				return MsgTemplate.failureMsg(AbnormalMsgEnum.STOCK_UPDATE_SPLIT_ORDER_STATUS_ERROR);
			}
		}
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getOrderByOrderIdList(OrderIdListBO param) {
		HttpResult result = abnormalServer.getOrderByOrderIdList(param);
		return MsgTemplate.customMsg(result);
	}

}
