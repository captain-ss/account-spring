package com.example.demo.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.utility.Constants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

@Component
public class UserAuthenticationProvider {
    private final String secretKey;
    private final Algorithm algorithm;
    public UserAuthenticationProvider(){
        this.secretKey = Constants.SECRET_KEY;
        this.algorithm = Algorithm.HMAC256(Constants.SECRET_KEY);
    }
    public String createJwtToken(String username, String password){
        Date now = new Date();
        Date validTill=new Date(now.getTime() + now.getTime());
        final String JwtToken = JWT.create().withIssuer(username)
                .withIssuedAt(now)
                .withPayload(new HashMap<String, String>(){{put("username", username);put("password", password);}})
                .withExpiresAt(validTill)
                .sign(this.algorithm);
        System.out.println(JwtToken+ " JWT Token");
        return  JwtToken;
    }
    public Authentication verifyToken(String token){
        JWTVerifier jwtVerifier = JWT.require(this.algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return new UsernamePasswordAuthenticationToken(decodedJWT.getIssuer(), null, Collections.emptyList());
    }
}
