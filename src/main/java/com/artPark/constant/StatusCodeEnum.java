package com.artPark.constant;

import com.artPark.common.dto.StatusCodeEntity;
import lombok.Data;

/**
 * @Author lbc on 2020/10/22  10:09.
 */
public enum StatusCodeEnum {
    /**成功**/
    success("0000"),
    /**认证失败**/
    auth_failed("0100"),
    /**权限不足**/
    permission_denied("0101"),
    /**token已失效**/
    token_expired("0102"),
    /**登录失败（用户名或密码有误）**/
    login_failed("0103"),
    /**失败**/
    failed("9999"),
    ;


    private String code;
    StatusCodeEnum(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }

    public String getDesc(){
        return StatusCodeEntity.get(this.code);
    }

}
