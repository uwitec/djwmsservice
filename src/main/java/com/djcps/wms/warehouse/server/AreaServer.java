package com.djcps.wms.warehouse.server;


import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.wms.commons.request.GetCodeRequest;
import com.djcps.wms.commons.request.MapHttpRequest;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.warehouse.model.area.AddAreaBO;
import com.djcps.wms.warehouse.model.area.DeleteAreaBO;
import com.djcps.wms.warehouse.model.area.IsUseStreetBO;
import com.djcps.wms.warehouse.model.area.SelectAllAreaListBO;
import com.djcps.wms.warehouse.model.area.UpdateAreaBO;
import com.djcps.wms.warehouse.model.warehouse.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.request.WmsForAreaHttpRequest;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpc.plugin.http.HTTPResponse;

/**
 * @title:库区调用http服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class AreaServer {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(AreaServer.class);	
	
	private Gson gson = GsonUtils.gson;
	
	@Autowired
	private WmsForAreaHttpRequest warehouseAreaHttpRequest;
	
	@Autowired
	private MapHttpRequest mapHttpRequest;

	@Autowired
	private GetCodeRequest getCodeRequest;
	
	public HttpResult addArea(AddAreaBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = warehouseAreaHttpRequest.addArea(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}

	public HttpResult modifyArea(UpdateAreaBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = warehouseAreaHttpRequest.modifyArea(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}

	public HttpResult deleteArea(DeleteAreaBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = warehouseAreaHttpRequest.deleteArea(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}

	public HttpResult getAreaAllList(SelectAllAreaListBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = warehouseAreaHttpRequest.getAreaAllList(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}

	public HttpResult getAreaById(SelectWarehouseByIdBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = warehouseAreaHttpRequest.getAreaById(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}

	/**
	 * @title 获取库区编码
	 * @author  wzy
	 * @create  2017/12/21 17:03
	 **/
	public HttpResult getAreaCode(GetCodeBO getCodeBO){
		//将请求参数转化为requestbody格式
		String json=gson.toJson(getCodeBO);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json
		);
		//调用接口获取信息
		HTTPResponse http=getCodeRequest.getCode(rb);
		return verifyHttpResult(http);
	}

	public HttpResult verifyCode(AddAreaBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = warehouseAreaHttpRequest.verifyCode(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	public HttpResult deleteCode(DeleteAreaBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = warehouseAreaHttpRequest.deleteCode(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	public HttpResult isUsedStreet(IsUseStreetBO isUseStreetBO) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(isUseStreetBO);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = warehouseAreaHttpRequest.isUsedStreet(rb);
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
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	
}
