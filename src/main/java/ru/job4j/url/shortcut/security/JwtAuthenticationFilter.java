package ru.job4j.url.shortcut.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.job4j.url.shortcut.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

/**
 * Фильтр аутентификации
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; /* 10 days */
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String SIGN_UP_URL = "/registration/sign-up";
    public static final String REDIRECT_URL = "/redirect/**";
    public static final String STATISTIC_LIST = "/statistic/list";

    private final AuthenticationManager auth;

    public JwtAuthenticationFilter(AuthenticationManager auth) {
        this.auth = auth;
    }

    /** Проверка логина и пароля пользователя */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper()
                                   .readValue(req.getInputStream(), User.class);

            return auth.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getLogin(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Создание токена */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                               .withSubject(((org.springframework.security.core.userdetails.User)
                                                     auth.getPrincipal()).getUsername())
                               .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                               .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_AUTHORIZATION, TOKEN_PREFIX + token);
    }
}
