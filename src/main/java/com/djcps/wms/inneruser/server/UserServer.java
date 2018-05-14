package com.djcps.wms.inneruser.server;

import com.alibaba.fastjson.JSONArray;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.utils.HttpResultUtils;
import com.djcps.wms.inneruser.model.userparam.*;
import com.djcps.wms.inneruser.request.UserRequest;
import com.djcps.wms.inneruser.request.WmsForUserRequest;
import com.djcps.wms.stocktaking.model.orderresult.OrderResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import rpc.plugin.http.HTTPResponse;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author wzy
 * @date 2018/4/12
 **/
@Repository
public class UserServer {

    private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(UserServer.class);

    private Gson gson = new GsonBuilder().create();

    @Resource
    private UserRequest userRequest;

    @Resource
    private WmsForUserRequest wmsForUserRequest;

    /**
     * 从org批量根据用户id获取用户基本信息
     *
     * @param orgGetUserInfo BatchOrgUserInfoBO
     * @return List<OrgUserInfoPO>
     * @author wzy
     * @date 2018/4/12 14:06
     **/
    public List<OrgUserInfoPO> getUserListByOrg(BatchOrgUserInfoBO orgGetUserInfo) {
        String json = gson.toJson(orgGetUserInfo);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.getUserInfoList(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                return JSONArray.parseArray(data, OrgUserInfoPO.class);
            }
        }
        return null;
    }

    /**
     * 从org获取该用户的全部信息
     *
     * @param orgGetUserInfo GetOrgUserInfoBO
     * @return OrgUserInfoPO
     * @author wzy
     * @date 2018/4/12 14:39
     **/
    public OrgUserInfoPO getUserByOrg(GetOrgUserInfoBO orgGetUserInfo) {
        String json = gson.toJson(orgGetUserInfo);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.getUserInfo(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<OrgUserInfoPO> list = JSONArray.parseArray(data, OrgUserInfoPO.class);
                if (list.size() > 0) {
                    return list.get(0);
                }
            }
        }
        return null;
    }

    /**
     * org开启/禁用用户
     *
     * @param orgDeleteUserBO OrgDeleteUserBO
     * @return Boolean
     * @author wzy
     * @date 2018/4/13 13:36
     **/
    public Boolean updateCloseOpenUser(OrgDeleteUserBO orgDeleteUserBO) {
        String json = gson.toJson(orgDeleteUserBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.updateCloseOpenUser(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (!ObjectUtils.isEmpty(result)) {
                return result.isSuccess();
            }
        }
        return false;
    }

    /**
     * wms获取单条用户关联信息
     *
     * @param deleteUserBO DeleteUserBO
     * @return UserRelevanceBO
     * @author wzy
     * @date 2018/4/13 10:32
     **/
    public UserRelevanceBO getUserRelevance(DeleteUserBO deleteUserBO) {
        String paramJson = gson.toJson(deleteUserBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.getUserRelevance(requestBody);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                return gson.fromJson(data, UserRelevanceBO.class);
            }
        }
        return null;
    }

    /**
     * wms修改用户工作状态等信息
     *
     * @param updateUserStatusBO UpdateUserStatusBO
     * @return HTTPResponse
     * @author wzy
     * @date 2018/4/13 9:31
     **/
    public HttpResult updateUserStatus(UpdateUserStatusBO updateUserStatusBO) {
        String paramJson = gson.toJson(updateUserStatusBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.updateUserStatus(requestBody);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * wms删除用户关联信息
     *
     * @param deleteUserBO DeleteUserBO
     * @return result
     * @author wzy
     * @date 2018/4/13 11:18
     **/
    public HttpResult deleteUserRelevance(DeleteUserBO deleteUserBO) {
        String paramJson = gson.toJson(deleteUserBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.deleteUserRelevance(requestBody);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * wms新增用户关联信息
     *
     * @param userRelevanceBO UserRelevanceBO
     * @return http
     * @author wzy
     * @date 2018/4/16 10:15
     **/
    public HttpResult insertUserRelevance(UserRelevanceBO userRelevanceBO) {
        String paramJson = gson.toJson(userRelevanceBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.insertUserRelevance(requestBody);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * wms新增用户关联仓库信息
     *
     * @param userRelevanceBO UserRelevanceBO
     * @return HttpResult
     * @author wzy
     * @date 2018/4/16 11:40
     **/
    public HttpResult insertUserWarehouse(UserRelevanceBO userRelevanceBO) {
        String paramJson = gson.toJson(userRelevanceBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.insertUserWarehouse(requestBody);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * wms批量新增用户关联仓库信息
     *
     * @param addUserWarehouseBOList List<AddUserWarehouseBO>
     * @return HttpResult
     * @author wzy
     * @date 2018/4/16 11:40
     **/
    public HttpResult insertUserWarehouseList(List<AddUserWarehouseBO> addUserWarehouseBOList) {
        String paramJson = gson.toJson(addUserWarehouseBOList);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.insertUserWarehouseList(requestBody);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * WMS条件获取用户列表
     *
     * @param pageGetUserBO PageGetUserBO
     * @return OrderResult
     * @author wzy
     * @date 2018/4/13 15:17
     **/
    public OrderResult pageGetUserRelevance(PageGetUserBO pageGetUserBO) {
        String paramJson = gson.toJson(pageGetUserBO);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.pageGetUserRelevance(requestBody);
        if (httpResponse.isSuccessful()) {
            return gson.fromJson(httpResponse.getBodyString(), OrderResult.class);
        }
        return null;
    }

    /**
     * 从org获取该公司的所有部门信息
     *
     * @param orgGetDepartmentBO OrgGetDepartmentBO
     * @return OrgDepartmentPO
     * @author wzy
     * @date 2018/4/12 14:39
     **/
    public List<OrgDepartmentPO> getDepartment(OrgGetDepartmentBO orgGetDepartmentBO) {
        String json = gson.toJson(orgGetDepartmentBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.getDepartList(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                return JSONArray.parseArray(data, OrgDepartmentPO.class);
            }
        }
        return null;
    }

    /**
     * 从org获取该公司的所有职务信息
     *
     * @param orgGetDepartmentBO OrgGetDepartmentBO
     * @return List<OrgUserJobPO>
     * @author wzy
     * @date 2018/4/17 9:25
     **/
    public List<OrgUserJobPO> getUserJob(OrgGetDepartmentBO orgGetDepartmentBO) {
        String json = gson.toJson(orgGetDepartmentBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.getUjob(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                return JSONArray.parseArray(data, OrgUserJobPO.class);
            }
        }
        return null;
    }

    public List<OrgPositionPO> getPosition(OrgGetPositionBO orgGetPositionBO) {
        String json = gson.toJson(orgGetPositionBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.queryPosition(map);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                return JSONArray.parseArray(data, OrgPositionPO.class);
            }
        }
        return null;
    }


    /**
     * org修改保存用户信息
     *
     * @param saveUserBO SaveUserBO
     * @return Boolean
     * @author wzy
     * @date 2018/4/16 9:50
     **/
    public Boolean updateUserManage(SaveUserBO saveUserBO) {
        String json = gson.toJson(saveUserBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.updateUserManage(map);
        if (httpResponse.isSuccessful()) {
            String body = httpResponse.getBodyString();
            if (StringUtils.isNotBlank(body)) {
                HttpResult baseResult = gson.fromJson(body, HttpResult.class);
                if (baseResult.isSuccess()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * org新增用户并返回用户信息
     *
     * @param saveUserBO SaveUserBO
     * @return saveUserBO
     * @author wzy
     * @date 2018/4/16 9:58
     **/
    public HttpResult addPostUserInfo(SaveUserBO saveUserBO) {
        String json = gson.toJson(saveUserBO);
        Map<String, Object> map = gson.fromJson(json, Map.class);
        HTTPResponse httpResponse = userRequest.addPostUserInfo(map);
        return HttpResultUtils.returnResult(httpResponse);
    }

    /**
     * 获取用户关联仓库信息
     *
     * @param getUserWarehouse DeleteUserBO
     * @return List<String>
     * @author wzy
     * @date 2018/4/17 11:08
     **/
    public List<String> getUserWarehouse(DeleteUserBO getUserWarehouse) {
        String paramJson = gson.toJson(getUserWarehouse);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.getUserWarehouse(requestBody);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String json = gson.toJson(result.getData());
                return JSONArray.parseArray(json, String.class);
            }
        }
        return null;
    }

    /**
     * 批量获取用户关联仓库信息
     *
     * @param getWarehouseListBOS GetWarehouseListBO
     * @return List<WarehouseListPO>
     * @author wzy
     * @date 2018/4/17 11:08
     **/
    public List<WarehouseListPO> getUserWarehouseList(GetWarehouseListBO getWarehouseListBOS) {
        String paramJson = gson.toJson(getWarehouseListBOS);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForUserRequest.getUserWarehouseList(requestBody);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String json = gson.toJson(result.getData());
                return JSONArray.parseArray(json, WarehouseListPO.class);
            }
        }
        return null;
    }
}
