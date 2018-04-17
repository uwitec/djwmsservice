package com.djcps.wms.permission.request;


import java.util.Map;

import com.djcps.wms.commons.config.ParamsConfig;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rpc.plugin.http.HTTPResponse;
import rpc.plugin.http.RPCClientFields;

/**
 * @author zhq
 * 向or调取服务
 * 2018年4月12日
 */
@RPCClientFields(urlfield="ORG",urlbean=ParamsConfig.class)
public interface DjorForPermissionHttpRequest {
	/**
	 * 获取组合权限的数据
	 * @param map
	 * @return
	 */
	//@FormUrlEncoded
	@GET("getPerManageList.org")
	HTTPResponse getPermissionList(@QueryMap Map<String,Object> map);
	
	/**
	 * 获取WMS权限
	 * @param map
	 * @return
	 */
	@GET("getPerBaseAll.org")
	HTTPResponse getWmsPermission(@QueryMap Map<String,Object> map);
	
	/**
	 * 新增/修改权限包
	 * @param map
	 * @return
	 */
	@POST("updatePostPerManage.org")
	HTTPResponse insertOrUpdatePermission(@QueryMap Map<String,Object> map);
	
	/**
	 * 删除权限包
	 * @param map
	 * @return
	 */
	@POST("delPerManage.org")
	HTTPResponse deletePermission(@QueryMap Map<String,Object> map);
	
	/**
	 * 根据权限id获取用户信息
	 * @param map
	 * @return
	 */
	@GET("getPerToUser.org")
	HTTPResponse getUserByPermissionId(@QueryMap Map<String,Object> map);
	
	/**
	 * 根据组合权限id和公司id，获取获取组合权限集合
	 * @param map
	 * @return
	 */
	@GET("getPerChoose.org")
	HTTPResponse getPerChoose(@QueryMap Map<String,Object> map);
}
