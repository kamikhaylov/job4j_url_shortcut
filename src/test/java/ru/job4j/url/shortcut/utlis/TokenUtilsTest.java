package ru.job4j.url.shortcut.utlis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TokenUtilsTest {

    @Test
    public void whenGetLoginThenLogin() {
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9."
                               + "eyJzdWIiOiJLb25zdGFudGluIiwiZXhwIjox"
                               + "Njk3NTUyNTc4fQ.1hZDMOJ1rqJZ3FR6YODsS-"
                               + "EBAyMEzoS4UuzUgqiS2BEdT34VUNC8wl8QjD5"
                               + "vfTlpd_71IdB8Mzt-p6CCuV11Jw";
        String expected = "Konstantin";

        String actual = TokenUtils.getLogin(token);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}