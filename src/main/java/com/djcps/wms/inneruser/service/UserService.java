package com.djcps.wms.inneruser.service;

import com.djcps.wms.inneruser.model.param.*;

import java.util.Map;

/**
 * 用户模块
 * @author wzy
 * @date 2018/4/12
 **/
public interface UserService {

    /**
     * org根据用户批量id获取用户信息列表
     * @param orgGetUserInfo BatchOrgUserInfoBO
     * @return map
     * @author wzy
     * @date 2018/4/12
     **/
    Map<String,Object> getUserList(BatchOrgUserInfoBO orgGetUserInfo);

    /**
     * 从org获取一个用户的信息
     * @author wzy
     * @param orgGetUserInfo GetOrgUserInfoBO
     * @return map
     * @date 2018/4/12
     **/
    Map<String,Object> getUser(GetOrgUserInfoBO orgGetUserInfo);

    /**
     * wms获取单条用户关联信息
     * @param userBO UserBO
     * @return map
     * @author wzy
     * @date 2018/4/13
     **/
    Map<String,Object> getUserRelevance(UserBO userBO);

    /**
     * 修改用户工作状态和仓库等信息
     * @param updateUserBO UpdateUserBO
     * @return Map<String,Object>
     * @author wzy
     * @date 2018/4/13
     **/
    Map<String,Object> updateUserRelevance(UpdateUserBO updateUserBO);

    /**
     * 删除用户关联信息
     * @param userBO UserBO
     * @return Map<String,Object>
     * @author wzy
     * @date 2018/4/13
     **/
    Map<String,Object> deleteUserRelevance(UserBO userBO);

    /**
     * 删除用户信息，包括org
     * @param orgDeleteUserBO OrgDeleteUserBO
     * @return Map<String,Object>
     * @author wzy
     * @date 2018/4/13
     **/
    Map<String,Object> deleteUser(OrgDeleteUserBO orgDeleteUserBO);

    /**
     * 分页获取用户列表
     * @author  wzy
     * @param pageUserInfoBO PageUserInfoBO
     * @return Map<String,Object>
     * @date  2018/4/13 15:20
     **/
    Map<String,Object> pageUserRelevance(PageUserInfoBO pageUserInfoBO);

    /**
     * 保存用户信息新增/修改
     * @author  wzy
     * @param saveUserBO WmsSaveUserBO
     * @return Map<String,Object>
     * @date  2018/4/16 9:38
     **/
    Map<String,Object> saveUser(WmsSaveUserBO saveUserBO);

    /**
     * 获取公司所有部门信息
     * @author  wzy
     * @param getDepartmentBO GetDepartmentBO
     * @return  Map<String,Object>
     * @date  2018/4/16 11:43
     **/
    Map<String,Object> getAllDepartment(GetDepartmentBO getDepartmentBO);

    /**
     * 获取公司所有职务信息
     * @author  wzy
     * @param getJobBO GetJobBO
     * @return Map<String,Object>
     * @date  2018/4/17 9:37
     **/
    Map<String,Object> getUjob(GetJobBO getJobBO);

    /**
     * 获取公司所有职位信息
     * @author  wzy
     * @param orgPositionBO OrgPositionBO
     * @return Map<String,Object>
     * @date  2018/4/17 9:38
     **/
    Map<String,Object> getPosition(OrgPositionBO orgPositionBO);

    /**
     * 获取个人部门职位信息和全部信息
     * @author  wzy
     * @param getOrgUserInfoBO GetOrgUserInfoBO
     * @return Map<String,Object>
     * @date  2018/4/17 9:58
     **/
    Map<String,Object> getDepartAndJob(GetOrgUserInfoBO getOrgUserInfoBO);

    /**
     * 获取角色列表，根据id获取或者获取全部
     * @author  wzy
     * @param roleTypeBO RoleTypeBO
     * @return  Map<String,Object>
     * @date  2018/4/16 16:20
     **/
    Map<String,Object> roleList(RoleTypeBO roleTypeBO);

    /**
     * 获取用户关联仓库信息
     * @author  wzy
     * @param getUserWarehouse UserBO
     * @return  Map<String,Object>
     * @date  2018/4/17 11:09
     **/
    Map<String,Object> getUserWarehouse(UserBO getUserWarehouse);

    /**
     * 根据角色类型获取用户信息
     * @author Chengw
     * @since 2018/5/15  10:04
     * @param userInfoBO 参数类
     * @return Map
     */
    Map<String,Object> listUserByType(UserInfoBO userInfoBO);
}
