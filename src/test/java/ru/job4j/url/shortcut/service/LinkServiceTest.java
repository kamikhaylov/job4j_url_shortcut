package ru.job4j.url.shortcut.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.url.shortcut.dto.request.LinkRequestDto;
import ru.job4j.url.shortcut.dto.response.LinkResponseDto;
import ru.job4j.url.shortcut.mapper.Mapper;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.model.Statistic;
import ru.job4j.url.shortcut.model.User;
import ru.job4j.url.shortcut.repository.api.LinkRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class LinkServiceTest {

    private LinkService linkService;

    @Mock
    private LinkRepository linkRepository;
    @Mock
    private StatisticService statisticService;
    @Mock
    private UserService userService;
    @Mock
    private Mapper<LinkRequestDto, Link> linkRequestMapper;
    @Mock
    private Mapper<Link, LinkResponseDto> linkResponseMapper;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        linkService = new LinkService(linkRepository, statisticService, userService, linkRequestMapper, linkResponseMapper);
    }

    @AfterEach
    public void after() {
        Mockito.reset(linkRepository, statisticService, userService, linkRequestMapper, linkResponseMapper);
    }

    @Test
    public void whenCreateThenLinkResponseDto() {
        LinkRequestDto request = new LinkRequestDto();
        String login = "login";
        Link link = new Link();
        User user = new User();
        Statistic statistic = new Statistic();
        LinkResponseDto linkResponseDto = new LinkResponseDto();

        when(linkRequestMapper.map(request)).thenReturn(link);
        when(userService.findByLogin(login)).thenReturn(Optional.of(user));
        when(statisticService.create(any())).thenReturn(Optional.of(statistic));
        when(linkRepository.save(any())).thenReturn(link);
        when(linkResponseMapper.map(any())).thenReturn(linkResponseDto);

        Optional<LinkResponseDto> actual = linkService.create(request, login);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        assertNotNull(actual.get());
        assertEquals(linkResponseDto, actual.get());
        verify(linkRequestMapper).map(request);
        verify(userService).findByLogin(any());
        verify(statisticService).create(any());
        verify(linkRepository).save(any());
        verify(linkResponseMapper).map(any());
        verifyNoMoreInteractions(linkRepository, statisticService, userService, linkRequestMapper, linkResponseMapper);
    }

    @Test
    public void whenCreateThenException() {
        LinkRequestDto request = new LinkRequestDto();
        String login = "login";
        Link link = new Link();
        User user = new User();
        Statistic statistic = new Statistic();

        when(linkRequestMapper.map(request)).thenReturn(link);
        when(userService.findByLogin(login)).thenReturn(Optional.of(user));
        when(statisticService.create(any())).thenReturn(Optional.of(statistic));
        doThrow(IllegalStateException.class).when(linkRepository).save(any());

        Optional<LinkResponseDto> actual = linkService.create(request, login);

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
        verify(linkRequestMapper).map(request);
        verify(userService).findByLogin(any());
        verify(statisticService).create(any());
        verify(linkRepository).save(any());
        verifyNoMoreInteractions(linkRepository, statisticService, userService, linkRequestMapper, linkResponseMapper);
    }

    @Test
    public void whenFindByCodeThenLink() {
        String code = "code";
        Link link = new Link();
        when(linkRepository.findByCode(code)).thenReturn(Optional.of(link));

        Optional<Link> actual = linkService.findByCode(code);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        verify(linkRepository).findByCode(code);
        verifyNoMoreInteractions(linkRepository, statisticService, userService, linkRequestMapper, linkResponseMapper);
    }
}