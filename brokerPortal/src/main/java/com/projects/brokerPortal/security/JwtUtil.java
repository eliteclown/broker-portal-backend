package com.projects.brokerPortal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;



@Component
public class JwtUtil {

    private static final String SECRET_KEY = "mysecretkeythatneedstobeatleast32characters"; //unique secret key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 5; // session expiration time of 5 hours

    private Key getSignInKey() {

        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); //encryption algorithm

    }

    // function to generate JWT tokens
    public String generateToken(String username) {

        return Jwts.builder()

                .setSubject(username)

                .setIssuer("BrokerPortalApp")

                .setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))

                .signWith(getSignInKey(), SignatureAlgorithm.HS256)

                .compact();

    }

    public String extractUsername(String token) {

        return parseToken(token).getBody().getSubject();

    }

    // function to verify and validate JWT tokens
    public boolean isTokenValid(String token) {

        try {

            parseToken(token);

            return true;

        } catch (Exception e) {

            return false;

        }

    }



    private Jws<Claims> parseToken(String token) {

        return Jwts.parser()

                .setSigningKey(getSignInKey())

                .build()

                .parseClaimsJws(token);

    }

}


