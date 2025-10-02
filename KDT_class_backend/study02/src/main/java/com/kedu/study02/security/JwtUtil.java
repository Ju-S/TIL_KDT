package com.kedu.study02.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.expiration}")
    private long exp;

    private Algorithm algorithm;
    private JWTVerifier jwt;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.jwt = JWT.require(algorithm).build();
    }

    public String createToken(String id) {
        return JWT.create()
                .withSubject(id)
                .withClaim("name", "Tom")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + exp))
                .sign(algorithm);
    }

    public DecodedJWT verifyToken(String token) {
        return jwt.verify(token);
    }
}
