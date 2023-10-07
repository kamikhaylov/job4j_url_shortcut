package ru.job4j.url.shortcut.utlis;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import static ru.job4j.url.shortcut.security.JwtAuthenticationFilter.SECRET;
import static ru.job4j.url.shortcut.security.JwtAuthenticationFilter.TOKEN_PREFIX;

public final class TokenUtils {

    private TokenUtils() {
    }

    public static String getLogin(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                       .build()
                       .verify(token.replace(TOKEN_PREFIX, ""))
                       .getSubject();
    }
}
