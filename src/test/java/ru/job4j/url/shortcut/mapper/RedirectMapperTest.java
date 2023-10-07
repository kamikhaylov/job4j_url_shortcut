package ru.job4j.url.shortcut.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.url.shortcut.dto.RedirectDto;
import ru.job4j.url.shortcut.model.Link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RedirectMapperTest {

    private Mapper<Link, RedirectDto> redirectMapper;

    @BeforeEach
    public void before() {
        redirectMapper = new RedirectMapper();
    }

    @Test
    public void whenMapThenRedirectDto() {
        String url = "url";
        Link link = new Link();
        link.setUrl(url);
        RedirectDto expected = new RedirectDto();
        expected.setUrl(url);

        RedirectDto actual = redirectMapper.map(link);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}