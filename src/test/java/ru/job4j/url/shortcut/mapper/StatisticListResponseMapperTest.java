package ru.job4j.url.shortcut.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.url.shortcut.dto.response.StatisticListResponseDto;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.model.Statistic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StatisticListResponseMapperTest {

    private static final String URL = "url";
    private static final int TOTAL = 1;

    private  Mapper<Link, StatisticListResponseDto> statisticListResponseMapper;

    @BeforeEach
    public void before() {
        statisticListResponseMapper = new StatisticListResponseMapper();
    }

    @Test
    public void whenMapThenStatisticListResponseDto() {
        Link link = new Link();
        link.setUrl(URL);
        link.setStatistic(new Statistic(1, TOTAL));
        StatisticListResponseDto expected = new StatisticListResponseDto();
        expected.setUrl(URL);
        expected.setTotal(TOTAL);

        StatisticListResponseDto actual = statisticListResponseMapper.map(link);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}