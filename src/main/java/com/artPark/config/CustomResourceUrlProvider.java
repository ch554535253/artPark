package com.artPark.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * @Author lbc on 2020/11/26  15:20.
 */
@Component
public class CustomResourceUrlProvider extends ResourceUrlProvider {
    @Value("${server.servlet.context-path}")
    private String CONTEXT_PATH;

    public final String parasUrl(String url){
        return CONTEXT_PATH+super.getForLookupPath(url);
    }
}
