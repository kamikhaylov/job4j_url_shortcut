package ru.job4j.url.shortcut.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.url.shortcut.dto.response.LinkResponseDto;
import ru.job4j.url.shortcut.model.Link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LinkResponseMapperTest {

    private Mapper<Link, LinkResponseDto> linkResponseMapper;

    @BeforeEach
    public void before() {
        linkResponseMapper = new LinkResponseMapper();
    }

    @Test
    public void whenMapThenLinkResponseDto() {
        String code = "code";
        Link link = new Link();
        link.setCode(code);
        LinkResponseDto expected = new LinkResponseDto();
        expected.setCode(code);

        LinkResponseDto actual = linkResponseMapper.map(link);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}