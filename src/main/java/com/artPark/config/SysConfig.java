package com.artPark.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author lbc on 2020/11/26  10:57.
 */
@Component
@PropertySource({"classpath:config/sysConfig.properties"})
//@ConfigurationProperties(prefix="config")
@Data
public class SysConfig {
    private String testValue;
}
