package com.djcps.wms.address.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.wms.address.request.AddressServerHttpRequest;
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.ProvinceCityAreaCodeBo;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderVO;
import com.djcps.wms.provider.request.WmsServerForProviderHttpRequest;
import com.djcps.wms.provider.service.ProviderServiceImpl;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:地址服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class AddressServer {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private AddressServerHttpRequest addressServerHttpRequest;

	public HttpResult getProvinceAllList(ProvinceCityAreaCodeBo param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = addressServerHttpRequest.getProvinceAllList(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getCityListByProvince(ProvinceCityAreaCodeBo param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = addressServerHttpRequest.getCityListByProvince(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getAreaListByCity(ProvinceCityAreaCodeBo param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = addressServerHttpRequest.getAreaListByCity(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getStreeListByArea(ProvinceCityAreaCodeBo param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = addressServerHttpRequest.getStreeListByArea(rb);
		return verifyHttpResult(http);
	}
	
	/**
	 * @title:校验HTTPResponse结果是否成功
	 * @description:
	 * @param http
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	private HttpResult verifyHttpResult(HTTPResponse http){
		HttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), HttpResult.class);
		}
		if(result == null){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}

}