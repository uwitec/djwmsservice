package com.djcps.wms.role.server;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.role.model.DeleteBO;
import com.djcps.wms.role.model.RoleListBO;
import com.djcps.wms.role.model.SaveBO;
import com.djcps.wms.role.model.UpdateRoleInfoBO;
import com.djcps.wms.role.model.request.GetUserStatusPO;
import com.djcps.wms.role.model.request.RoleInfoResultPO;
import com.djcps.wms.role.model.request.RoleTypeInfoPO;
import com.djcps.wms.role.request.RoleHttpRequest;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rpc.plugin.http.HTTPResponse;
/**
 * @title:调用WMS_http服务
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/4/12
 **/
@Component
public class RoleHttpServer {
    private static DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(RoleHttpServer.class);

    private Gson gson = new Gson();
    @Autowired
    private RoleHttpRequest roleHttpRequest;
    /**
     * 获取角色列表
     * @author  wyb
     * @param roleListBO
     * @return 
     * @create  2018/4/12
     **/
    public RoleInfoResultPO roleList(RoleListBO roleListBO){
        String json = gson.toJson(roleListBO);
        System.out.println("---http请求参数转化为json格式---:"+json);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http =roleHttpRequest.list(rb);
        RoleInfoResultPO roleInfoResultPO = null;
        HttpResult result = null;
      //校验请求是否成功
        if(http.isSuccessful()){
            result = gson.fromJson(http.getBodyString(), HttpResult.class);
            String data = gson.toJson(result.getData());
            roleInfoResultPO = gson.fromJson(data, RoleInfoResultPO.class);
        }
        if(result == null){
            System.err.println("Http请求出错,HttpResult结果为null");
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return roleInfoResultPO;
    }
    
    /**
     * 更新角色关联信息
     * @param param
     * @return
     */
    public HttpResult update(UpdateRoleInfoBO param){
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http =roleHttpRequest.update(requestBody);
      //校验请求是否成功
        return returnResult(http);
    }
    /**
     * 删除角色关联信息
     * @param param
     * @return
     */
    public HttpResult delete(DeleteBO param){
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http =roleHttpRequest.delete(requestBody);
      //校验请求是否成功
        return returnResult(http);
    }
    /**
     * 保存角色关联信息
     * @param param
     * @return
     */
    public HttpResult save(SaveBO param){
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http =roleHttpRequest.save(requestBody);
      //校验请求是否成功
        return returnResult(http);
    }
    /**
     * 根据角色类型批量获取用户状态等信息
     * @param param
     * @return
     */
    public List<GetUserStatusPO> getUserStatusList(List<DeleteBO> param){
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http =roleHttpRequest.getUserStatusList(requestBody);
        HttpResult result = null;
        List<GetUserStatusPO> list = null;
        //校验请求是否成功
          if(http.isSuccessful()){
              result = gson.fromJson(http.getBodyString(), HttpResult.class);
              String data = gson.toJson(result.getData());
              list = new ArrayList<>();
              list  = JSONArray.parseArray(data, GetUserStatusPO.class);
          }
          if(result == null){
              System.err.println("Http请求出错,HttpResult结果为null");
              LOGGER.error("Http请求出错,HttpResult结果为null");
          }
          return list;
    }
    
    /**
     * 获取角色类型信息
     * @return
     */
    public HttpResult getRoleType(BaseBO param){
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse http =roleHttpRequest.roleType(requestBody);
        //校验请求是否成功
          return returnResult(http);
    }
    /**
     * 公共返回
     *
     * @param httpResponse
     * @return
     */
    private HttpResult returnResult(HTTPResponse httpResponse) {
        if (httpResponse.isSuccessful()) {
            try {
                String body = httpResponse.getBodyString();
                if (StringUtils.isNotBlank(body)) {
                    HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                    return baseResult;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }
}