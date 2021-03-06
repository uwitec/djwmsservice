package com.djcps.wms.commons.request;

import com.djcps.wms.commons.config.ParamsConfig;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @title 仓库库区库位编码http请求
 * @author  wzy
 * @create  2017/12/20 17:18
 **/
@RPCClientFields(urlfield = "WMS_SERVER", urlbean = ParamsConfig.class)
public interface GetCodeRequest {

    /**
     * 获取编码统一接口
     * @author  wzy
     * @param rb
     * @return
     * @create  2017/12/22 13:43
     **/
    @Headers("content-type:application/json")
    @POST("code/getCode.do")
    HTTPResponse getCode(@Body RequestBody rb);

}
