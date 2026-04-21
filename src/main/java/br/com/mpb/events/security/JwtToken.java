package br.com.mpb.events.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtToken {
    private static final String SECRET = "123456"; // apenas pra testes
    private static final long EXPIRATION = 5 * 60 * 1000; // 5 minutos

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validateToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);
        return jwt.getSubject(); // retorna o usuário
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = validateToken(token);
        return (username.equals(userDetails.getUsername()));
    }

}
