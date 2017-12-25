package com.djcps.wms.warehouse.service.impl;

import java.util.Map;

import com.djcps.wms.commons.model.PartnerInfoBo;
import com.djcps.wms.warehouse.model.location.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.warehouse.server.LocationServer;
import com.djcps.wms.warehouse.service.LocationService;
import com.google.gson.Gson;

/**
 * @title:库位业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月22日
 */
@Service
public class LocationServiceImpl implements LocationService {
	
	private Gson gson = new Gson();
	
	@Autowired
	private LocationServer locationServer;


	@Override
	public Map<String, Object> addLocation(AddLocationBO param) {
		//编码确认
		HttpResult verifyCode = locationServer.verifyCode(param);
		if(verifyCode.isSuccess()){
			HttpResult result = locationServer.addLocation(param);
			return MsgTemplate.customMsg(result);
		}else{
			return MsgTemplate.failureMsg(SysMsgEnum.CODE_ERROE);
		}
	}

	@Override
	public Map<String, Object> modifyLocation(UpdateLocationBO param) {
		HttpResult result = locationServer.modifyLocation(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> deleteLocation(DeleteLocationBO param) {
		//删除编码
		HttpResult deleteCode = locationServer.deleteCode(param);
		if(deleteCode.isSuccess()){
			HttpResult result = locationServer.deleteLocation(param);
			return MsgTemplate.customMsg(result);
		}else{
			return MsgTemplate.failureMsg(SysMsgEnum.CODE_ERROE);
		}
		
	}

	@Override
	public Map<String, Object> getLocationAllList(SelectAllLocationList param) {
		HttpResult result = locationServer.getLocationAllList(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getLocationByAttribute(SelectLocationByAttributeBO param) {
		HttpResult result = locationServer.getLocationByAttribute(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getLocationCode(PartnerInfoBo partnerInfoBo,LocationBo locationBo) {
		HttpResult result = locationServer.getLocationCode(partnerInfoBo,locationBo);
		return MsgTemplate.customMsg(result);
	}


}
