package com.artPark.common.dto;

import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lbc on 2020/10/22  10:42.
 */
@Component
@PropertySource(value={"classpath:config/statusCode.properties"},encoding = "utf-8")
public class StatusCodeEntity {
    private Map<String,String> sc = new HashMap<String,String>();
    private static StatusCodeEntity instance;

    @PostConstruct
    public void init(){
        instance = this;
    }
    public static String get(String key){
        return instance.sc.get(key);
    }
}
