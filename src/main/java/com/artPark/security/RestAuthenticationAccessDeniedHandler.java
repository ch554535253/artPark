package com.artPark.security;

import com.artPark.common.vo.ResultJson;
import com.artPark.constant.StatusCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author lbc on 2020/10/22  15:10.
 */
@Component("RestAuthenticationAccessDeniedHandler")
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        ResultJson rsp = new ResultJson(StatusCodeEnum.permission_denied);
        PrintWriter p = response.getWriter();
        p.print(rsp.toString());
        p.flush();
        p.close();
    }
}
