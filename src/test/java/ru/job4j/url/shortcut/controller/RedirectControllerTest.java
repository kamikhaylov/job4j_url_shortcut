package ru.job4j.url.shortcut.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.job4j.url.shortcut.security.JwtAuthenticationFilter.HEADER_AUTHORIZATION;

public class RedirectControllerTest extends BaseControllerTest {

    private static final String LOGIN = generateString();
    private static final String PASSWORD = generateString();
    private static final String SITE = generateString();
    private static final String NAME = generateString();
    private static final String URL = generateString();

    @Test
    public void whenRedirect() throws Exception {
        registration(LOGIN, PASSWORD, SITE, NAME);
        MvcResult authorizationResult = authorization(LOGIN, PASSWORD);
        String token = authorizationResult.getResponse().getHeader(HEADER_AUTHORIZATION);
        MvcResult convertResult = convert(URL, token);
        String code = JsonPath.read(convertResult.getResponse().getContentAsString(), "code");

        MvcResult redirectResult = redirect(code, URL);

        assertNotNull(redirectResult);
    }
}