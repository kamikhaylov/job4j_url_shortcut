package ru.job4j.url.shortcut.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.url.shortcut.dto.request.LinkRequestDto;
import ru.job4j.url.shortcut.model.Link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LinkRequestMapperTest {

    private Mapper<LinkRequestDto, Link> linkRequestMapper;

    @BeforeEach
    public void before() {
        linkRequestMapper = new LinkRequestMapper();
    }

    @Test
    public void whenMapThenLink() {
        String utl = "url";
        LinkRequestDto linkRequestDto = new LinkRequestDto();
        linkRequestDto.setUrl(utl);
        Link expected = new Link();
        expected.setUrl(utl);

        Link actual = linkRequestMapper.map(linkRequestDto);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}