package com.artPark.security;

import com.artPark.common.vo.ResultJson;
import com.artPark.constant.StatusCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author lbc on 2020/10/22  10:03.
 * 认证失败时会进入此处理器
 */
@Component("JwtAuthenticationEntryPoint")
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        ResultJson rsp = new ResultJson(StatusCodeEnum.auth_failed);
        PrintWriter p = response.getWriter();
        p.print(rsp.toString());
        p.flush();
        p.close();
    }
}
