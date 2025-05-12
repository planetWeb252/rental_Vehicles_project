package com.rental.vehicles.services.jwtServices;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JwtServices {
    private static final String SECRET = "@Secret_Key";


    public String generateToken(String username,String email) {
        return JWT.create()
                .withSubject(username)
                .withClaim("roles", "ROLE_LOGIN")
                .withClaim("email",email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 7*24*60*60*1000)) // 1 week
                .sign(Algorithm.HMAC256(SECRET));
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    // Method to extract username (subject) from the token
    public String extractUsername(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getSubject();
    }

    // Method to extract roles from the token
    public String extractRoles(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);

        return jwt.getClaim("roles").asString();
    }

    public String extractEmail(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);

        return jwt.getClaim("email").asString();
    }
}
