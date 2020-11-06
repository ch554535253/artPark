package com.artPark.util;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.CompressionCodecs;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;

/**
 * @Author lbc on 2020/11/3  10:36.
 */
@Component
public class JwtUtil {
//    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//    @Value("${jwt.expiryTime}")
//    private Long expiryTime;
//
//    public String generateTokenByUser(String userId){
//        Map<String,Object> claimsMap = new HashMap<String,Object>();
//        claimsMap.put("ROLE","");
//        return generateToken(userId,claimsMap);
//    }
//
//    public boolean isTokenExpiried(String token){
//        Claims claims = getClaims(token);
//        if(claims == null){
//            return false;
//        }
//        Date expiration = claims.getExpiration();
//        return expiration.after(new Date());
//    }
//
//    private Claims getClaims(String token){
//        Claims claims;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(this.jwtSecret)
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            claims = null;
//        }
//        return claims;
//    }
//
//    private String generateToken(String subject, Map<String,Object> claimsMap){
//        return Jwts.builder()
//                .setSubject(subject)
//                .setClaims(claimsMap)
//                .setId(UUID.randomUUID().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis()+expiryTime*1000))
//                .compressWith(CompressionCodecs.DEFLATE)
//                .signWith(SIGNATURE_ALGORITHM,this.jwtSecret)
//                .compact();
//    }
}
