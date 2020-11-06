package com.artPark.constant;

import com.artPark.common.dto.StatusCodeEntity;
import lombok.Data;

/**
 * @Author lbc on 2020/10/22  10:09.
 */
public enum StatusCodeEnum {
    success("0000"),
    auth_failed("0100"),
    permission_denied("0101"),
    token_expired("0102"),
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
