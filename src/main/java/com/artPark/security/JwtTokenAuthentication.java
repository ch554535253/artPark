package com.artPark.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * @Author lbc on 2020/11/4  11:47.
 */
public class JwtTokenAuthentication extends AbstractAuthenticationToken {
    private String token;
    private String credentials;
    private String principal;

    public JwtTokenAuthentication(String token){
        super(Collections.emptyList());
        this.token = token;
    }

    public JwtTokenAuthentication(String credentials,String principal,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.credentials = credentials;
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public String getToken(){
        return this.token;
    }
}
