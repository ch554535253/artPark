package com.artPark.filter;

import com.artPark.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.artPark.constant.LogConstant.REQ_LOG_SUMMARY;

/**
 * @Author lbc on 2020/10/19  10:57.
 */
@Slf4j
@WebFilter(urlPatterns = {"/","/login","/api/*"},filterName = "reqLogFilter")
public class ReqLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init ReqLogFilter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        REQ_LOG_SUMMARY.info("summary log start!!");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String uri = request.getRequestURI();
        uri = uri.replace("/artPark/","").replaceAll("/",".")+ "-"+ NumberUtil.generateRandomByLen(8);
        MDC.put("MDC_TYPE","API");
        MDC.put("LOG_REQ_ID",uri);
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MDC.clear();
        }
        REQ_LOG_SUMMARY.info("summary log end!!");
    }

    @Override
    public void destroy() {
        log.info("destroy ReqLogFilter...");
    }
}
