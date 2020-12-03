package com.artPark.exception;

import com.artPark.constant.StatusCodeEnum;
import lombok.Data;
import lombok.ToString;

/**
 * @Author lbc on 2020/11/26  16:04.
 */
@Data
public class CustomException extends RuntimeException{
    private String code;
    private String msg;

    CustomException(Throwable throwable){
        super(throwable);
    }

    public CustomException(String code,String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    @SuppressWarnings("static-access")
    public CustomException(StatusCodeEnum scEnum) {
        super(scEnum.getDesc());
        this.code = scEnum.getCode();
        this.msg = super.getMessage();
    }

    public CustomException(StatusCodeEnum scEnum,String msg) {
        super(msg);
        this.code = scEnum.getCode();
        this.msg = msg;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\"code\":");
        sb.append("\"");
        sb.append(this.code);
        sb.append("\",");
        sb.append("\"msg\":");
        sb.append("\"");
        sb.append(this.msg);
        sb.append("\"}");
        return sb.toString();
    }
}
