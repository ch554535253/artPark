package com.artPark.common.vo;

import com.alibaba.fastjson.JSON;
import com.artPark.constant.StatusCodeEnum;
import lombok.Data;

/**
 * @Author lbc on 2020/10/22  10:07.
 */
@Data
public class ResultJson <T>{
    private String msg;
    private String code;
    private T obj;

    public ResultJson(StatusCodeEnum sc){
        this.code = sc.getCode();
        this.msg = sc.getDesc();
    }

    public ResultJson(StatusCodeEnum sc,T obj){
        this.code = sc.getCode();
        this.msg = sc.getDesc();
        this.obj = obj;
    }

    public ResultJson(StatusCodeEnum sc,String msg){
        this.code = sc.getCode();
        this.msg = msg;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
