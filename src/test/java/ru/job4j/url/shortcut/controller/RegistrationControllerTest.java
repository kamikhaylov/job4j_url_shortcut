package ru.job4j.url.shortcut.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationControllerTest extends BaseControllerTest {

    private static final String LOGIN = generateString();
    private static final String PASSWORD = generateString();
    private static final String SITE = generateString();
    private static final String NAME = generateString();
    private static final String URL = generateString();

    @Test
    public void whenRegistration() throws Exception {
        MvcResult result = registration(LOGIN, PASSWORD, SITE, NAME);

        assertNotNull(result);
    }
}