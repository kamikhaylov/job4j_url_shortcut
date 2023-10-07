package ru.job4j.url.shortcut.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.job4j.url.shortcut.security.JwtAuthenticationFilter.HEADER_AUTHORIZATION;
import static ru.job4j.url.shortcut.security.JwtAuthenticationFilter.TOKEN_PREFIX;

public class LoginControllerTest extends BaseControllerTest {

    private static final String LOGIN = generateString();
    private static final String PASSWORD = generateString();
    private static final String SITE = generateString();
    private static final String NAME = generateString();
    private static final String URL = generateString();

    @Test
    public void whenLogin() throws Exception {
        registration(LOGIN, PASSWORD, SITE, NAME);

        MvcResult result = authorization(LOGIN, PASSWORD);

        assertNotNull(result);
        assertTrue(Objects.requireNonNull(
                result.getResponse().getHeader(HEADER_AUTHORIZATION)).contains(TOKEN_PREFIX));
    }
}
