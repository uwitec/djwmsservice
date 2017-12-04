package com.djcps.wms.warehouse.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.provider.server.ProviderServer;
import com.djcps.wms.warehouse.model.AddWarehouseBO;
import com.djcps.wms.warehouse.model.DeleteWarehouseBO;
import com.djcps.wms.warehouse.model.IsUseWarehouseBO;
import com.djcps.wms.warehouse.model.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.model.SelectWarehouseByAttributeBO;
import com.djcps.wms.warehouse.model.UpdateWarehouseBO;
import com.djcps.wms.warehouse.request.WmsServerForWarehouseHttpRequest;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:仓库管理调用http服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class WarehouseServer {
	
	private static final Logger logger = LoggerFactory.getLogger(WarehouseServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private WmsServerForWarehouseHttpRequest warehouseHttpRequest;
	
	public HttpResult add(AddWarehouseBO addBean)throws Exception{
        //将请求参数转化为requestbody格式
        String json = gson.toJson(addBean);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = warehouseHttpRequest.add(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
	
	public HttpResult modify(UpdateWarehouseBO updateBean)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(updateBean);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = warehouseHttpRequest.modify(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	
	public HttpResult delete(DeleteWarehouseBO deleteBean)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(deleteBean);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = warehouseHttpRequest.delete(rb);
		//校验请求是否成功
		return verifyHttpResult(http);
	}
	

	public HttpResult getAllList(BaseListParam baseListParam)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(baseListParam);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = warehouseHttpRequest.getAllList(rb);
		return verifyHttpResult(http);
	}
	
	
	public HttpResult getWarehouseById(SelectWarehouseByIdBO selectByIdBean)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(selectByIdBean);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = warehouseHttpRequest.getWarehouseById(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getWarehouseByAttribute(SelectWarehouseByAttributeBO selectVagueBean)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(selectVagueBean);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = warehouseHttpRequest.getWarehouseByAttribute(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult enable(IsUseWarehouseBO isUseBean)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(isUseBean);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = warehouseHttpRequest.enable(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult disable(IsUseWarehouseBO isUseBean)throws Exception{
		//将请求参数转化为requestbody格式
		String json = gson.toJson(isUseBean);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = warehouseHttpRequest.disable(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getWarehouseType(String partnerId) {
		//将请求参数转化为requestbody格式
//		String json = gson.toJson(partnerId);
		System.out.println("---http请求参数转化为json格式---:"+partnerId);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),partnerId);
		//调用借口获取信息
		HTTPResponse http = warehouseHttpRequest.getWarehouseType(rb);
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
