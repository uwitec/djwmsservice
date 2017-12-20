package com.djcps.wms.inneruser.model.param;

import com.djcps.wms.commons.base.BaseParam;

import java.io.Serializable;

/**
 * 手机验证码登录参数类
 * @author Chengw
 * @since 2017/12/19 15:44.
 */
public class InnerUserLoginPhonePo extends BaseParam implements Serializable{

    /**
     * 手机号
     */
    private String phone;

    /**
     * 手机验证码
     */
    private String phoneCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }
}
