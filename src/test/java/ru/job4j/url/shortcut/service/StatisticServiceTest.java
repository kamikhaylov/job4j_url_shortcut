package ru.job4j.url.shortcut.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.url.shortcut.dto.response.StatisticListResponseDto;
import ru.job4j.url.shortcut.mapper.Mapper;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.model.Statistic;
import ru.job4j.url.shortcut.repository.api.LinkRepository;
import ru.job4j.url.shortcut.repository.api.StatisticRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class StatisticServiceTest {

    private StatisticService statisticService;

    @Mock
    private StatisticRepository statisticRepository;
    @Mock
    private LinkRepository linkRepository;
    @Mock
    private Mapper<Link, StatisticListResponseDto> statisticListResponseMapper;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        statisticService = new StatisticService(statisticRepository, linkRepository, statisticListResponseMapper);
    }

    @AfterEach
    public void after() {
        Mockito.reset(statisticRepository, linkRepository, statisticListResponseMapper);
    }

    @Test
    public void whenCreateThenStatistic() {
        Statistic statistic = new Statistic();
        when(statisticRepository.save(statistic)).thenReturn(statistic);

        Optional<Statistic> actual = statisticService.create(statistic);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        assertNotNull(actual.get());
        assertEquals(statistic, actual.get());
        verify(statisticRepository).save(statistic);
        verifyNoMoreInteractions(statisticRepository, linkRepository, statisticListResponseMapper);
    }

    @Test
    public void whenCreateThenException() {
        Statistic statistic = new Statistic();
        doThrow(IllegalStateException.class).when(statisticRepository).save(statistic);

        Optional<Statistic> actual = statisticService.create(statistic);

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
        verify(statisticRepository).save(statistic);
        verifyNoMoreInteractions(statisticRepository, linkRepository, statisticListResponseMapper);
    }

    @Test
    public void whenFindAllThenList() {
        List<Link> linkList = List.of(new Link());
        List<StatisticListResponseDto> statisticListResponse = List.of(new StatisticListResponseDto());

        when(linkRepository.findAll()).thenReturn(linkList);
        when(statisticListResponseMapper.map(linkList.get(0))).thenReturn(statisticListResponse.get(0));

        List<StatisticListResponseDto> actual = statisticService.findAll();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        verify(linkRepository).findAll();
        verify(statisticListResponseMapper).map(linkList.get(0));
        verifyNoMoreInteractions(statisticRepository, linkRepository, statisticListResponseMapper);
    }

    @Test
    public void whenRequestCountIncrement() {
        statisticService.increment(1);

        verify(statisticRepository).increment(1);
        verifyNoMoreInteractions(statisticRepository, linkRepository, statisticListResponseMapper);
    }
}