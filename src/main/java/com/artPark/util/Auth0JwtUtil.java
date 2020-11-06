package com.artPark.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author lbc on 2020/11/3  15:51.
 */
@Component
@Slf4j
public class Auth0JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiryTime}")
    private Long expiryTime;

    public String generateTokenByUser(UserDetails user){
        Map<String,String> claimsMap = new HashMap<String,String>();
        claimsMap.put("role_id",authoritiesToArray(user.getAuthorities()).get(0).toString());
        return generateToken(user.getUsername(),claimsMap);
    }

    public boolean isTokenExpired(String token){
        return JWT.decode(token).getExpiresAt().before(new Date());
    }

    public String getUserIdByToken(String token){
        return JWT.decode(token).getSubject();
    }

    public String refreshToken(){
        return "";
    }

    public DecodedJWT validateToken(String token) throws JWTVerificationException {
        DecodedJWT decodedJWT = null;
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.jwtSecret)).build();
        decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    private String generateToken(String subject, Map<String,String> claimsMap){
        JWTCreator.Builder builder = JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime*1000))
                .withSubject(subject);
        for (Map.Entry<String,String> entry : claimsMap.entrySet()){
            builder.withClaim(entry.getKey(),entry.getValue());
        }
        String token = builder.sign(Algorithm.HMAC256(this.jwtSecret));
        return token;
    }

    private List authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }

    public static void main(String[] args) {
        Auth0JwtUtil a = new Auth0JwtUtil();
        a.jwtSecret="123456";
        a.expiryTime=60L;
//        Map<String,String> claimsMap = new HashMap<String,String>();
//        claimsMap.put("ROLE","user");
//        String token = a.generateToken("lbc",claimsMap);
//        System.out.println("token:"+token);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJST0xFIjoidXNlciIsInN1YiI6ImxiYyIsImV4cCI6MTYwNDM5MTgxNywiaWF0IjoxNjA0MzkxNzU3fQ.FQYmx0BDhIKTdWhbEkcx6wRqsYGkbwXMm8rSthUs11hNU";
//        System.out.println(a.validateToken(token));
        System.out.println("isExpiry:"+a.isTokenExpired(token));
    }
}
