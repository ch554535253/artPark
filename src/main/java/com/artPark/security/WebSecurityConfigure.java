package com.artPark.security;

import com.artPark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @Author lbc on 2020/10/21  17:55.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final UserService userService;

    @Autowired
    WebSecurityConfigure(@Qualifier("JwtAuthenticationEntryPoint") AuthenticationEntryPoint jwtAuthenticationEntryPoint,
                         @Qualifier("RestAuthenticationAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler,
                         UserService userService){
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)      //认证失败处理器
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)         //权限不足处理器
                .and().csrf().disable()                                                     //因使用JWT作为认证标识，允许跨域请求
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //基于token,不需要session
                .and().authorizeRequests().antMatchers("/","/login","/api/*").permitAll()  //放开无需授权访问的资源
                .anyRequest().authenticated();
//        http.addFilterBefore();
    }

    @Override
    protected  void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                return userService.getUserDetails();
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/**/*.js","/**/*.css","/**/*.png","/**/*.gif");
    }
}
