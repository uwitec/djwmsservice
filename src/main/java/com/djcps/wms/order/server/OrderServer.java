package com.djcps.wms.order.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.request.WmsForProviderHttpRequest;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;


/**
 * 订单服务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
@Component
public class OrderServer {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private WmsForProviderHttpRequest providerHttpRequest;
	
	public HttpResult add(AddProviderBO addBean){
        //将请求参数转化为requestbody格式
        String json = gson.toJson(addBean);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = providerHttpRequest.add(rb);
        //校验请求是否成功
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
