package com.djcps.wms.inneruser.model.param;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户修改密码 参数类
 * @author Chengw
 * @since 2017/12/5 15:11.
 */
public class InnerUserChangePasswordPo {

    private String token;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

    private String userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}