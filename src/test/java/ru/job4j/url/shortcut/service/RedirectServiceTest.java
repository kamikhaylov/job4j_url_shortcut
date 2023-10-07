package ru.job4j.url.shortcut.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.url.shortcut.dto.RedirectDto;
import ru.job4j.url.shortcut.mapper.Mapper;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.model.Statistic;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class RedirectServiceTest {

    private RedirectService redirectService;

    @Mock
    private LinkService linkService;
    @Mock
    private StatisticService statisticService;
    @Mock
    private Mapper<Link, RedirectDto> redirectMapper;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        redirectService = new RedirectService(linkService, statisticService, redirectMapper);
    }

    @AfterEach
    public void after() {
        Mockito.reset(linkService, statisticService, redirectMapper);
    }

    @Test
    public void whenFindByCodeThenRedirectDto() {
        RedirectDto redirectDto = new RedirectDto();
        Link link = new Link();
        link.setStatistic(new Statistic(0, 0));
        String code = "code";

        when(linkService.findByCode(code)).thenReturn(Optional.of(link));
        when(redirectMapper.map(any())).thenReturn(redirectDto);

        Optional<RedirectDto> actual = redirectService.findByCode(code);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        assertNotNull(actual.get());
        verify(linkService).findByCode(code);
        verify(statisticService).requestCountIncrement(0);
        verify(redirectMapper).map(any());
        verifyNoMoreInteractions(linkService, statisticService, redirectMapper);
    }

}