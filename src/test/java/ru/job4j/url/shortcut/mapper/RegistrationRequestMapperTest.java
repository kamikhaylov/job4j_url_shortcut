package ru.job4j.url.shortcut.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.url.shortcut.dto.request.RegistrationRequestDto;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationRequestMapperTest {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SITE = "site";
    private static final String NAME = "name";

    private Mapper<RegistrationRequestDto, Site> registrationRequestMapper;

    @BeforeEach
    public void before() {
        registrationRequestMapper = new RegistrationRequestMapper();
    }

    @Test
    public void whenMapThenRedirectDto() {
        RegistrationRequestDto request = createRequest();
        Site expected = createSite();

        Site actual = registrationRequestMapper.map(request);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    private RegistrationRequestDto createRequest() {
        RegistrationRequestDto result = new RegistrationRequestDto();
        result.setLogin(LOGIN);
        result.setPassword(PASSWORD);
        result.setSite(SITE);
        result.setName(NAME);
        return result;
    }

    private Site createSite() {
        Site result = new Site();
        User user = new User();
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        result.setUser(user);
        result.setSite(SITE);
        result.setName(NAME);
        return result;
    }
}