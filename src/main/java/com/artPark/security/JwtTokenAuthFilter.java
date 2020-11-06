package com.artPark.security;

import com.alibaba.fastjson.JSON;
import com.artPark.common.vo.ResultJson;
import com.artPark.constant.StatusCodeEnum;
import com.artPark.service.UserService;
import com.artPark.util.Auth0JwtUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author lbc on 2020/11/3  9:24.
 */
@Component
@Slf4j
public class JwtTokenAuthFilter extends OncePerRequestFilter {
    private final static String JWT_HEADER_NAME = "Authorization";
    private final static String JWT_PREFIX = "Bearer ";
    private RequestMatcher rm;
    private Auth0JwtUtil jwtUtil;
    private UserService userService;

    @Autowired
    public JwtTokenAuthFilter(Auth0JwtUtil jwtUtil,UserService userService){
        this.rm = new RequestHeaderRequestMatcher(JWT_HEADER_NAME);
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        if(rm.matches(request) && (token=request.getHeader(JWT_HEADER_NAME)) != null && !"".equals(token)){
            if(token.startsWith(JWT_PREFIX)){
                token = token.substring(JWT_PREFIX.length());
            }
            try{
                DecodedJWT claim = jwtUtil.validateToken(token);
                UserDetails userDetails = userService.loadUserByUsername(claim.getSubject());
//              JwtTokenAuthentication authentication = new JwtTokenAuthentication(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (TokenExpiredException e){
                log.info("token已过期",e);
                returnJson(response,StatusCodeEnum.token_expired);
                return;
            }catch (JWTVerificationException e){
                log.info("无效的token",e);
                returnJson(response,StatusCodeEnum.auth_failed);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void returnJson(HttpServletResponse response, StatusCodeEnum sc) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        ResultJson rj = new ResultJson(sc);
        PrintWriter pw =  response.getWriter();
        pw.write(JSON.toJSONString(rj));
        pw.flush();
        pw.close();
    }
}
