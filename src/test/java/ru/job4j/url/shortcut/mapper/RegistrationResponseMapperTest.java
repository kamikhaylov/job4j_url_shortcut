package ru.job4j.url.shortcut.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.url.shortcut.dto.response.RegistrationResponseDto;
import ru.job4j.url.shortcut.model.Site;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationResponseMapperTest {

    private static final String SITE = "site";

    private Mapper<Site, RegistrationResponseDto> registrationResponseMapper;

    @BeforeEach
    public void before() {
        registrationResponseMapper = new RegistrationResponseMapper();
    }

    @Test
    public void whenMapThenRegistrationResponseDto() {
        Site site = new Site();
        site.setSite(SITE);
        RegistrationResponseDto expected = new RegistrationResponseDto();
        expected.setSite(SITE);

        RegistrationResponseDto actual = registrationResponseMapper.map(site);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}