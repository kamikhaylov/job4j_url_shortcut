package ru.job4j.url.shortcut.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.job4j.url.shortcut.dto.request.RegistrationRequestDto;
import ru.job4j.url.shortcut.dto.response.RegistrationResponseDto;
import ru.job4j.url.shortcut.mapper.Mapper;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.model.User;
import ru.job4j.url.shortcut.repository.api.SiteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class RegistrationServiceTest {

    private RegistrationService registrationService;

    @Mock
    private SiteRepository personRepository;
    @Mock
    private UserService userService;
    @Mock
    private Mapper<RegistrationRequestDto, Site> registrationRequestMapper;
    @Mock
    private Mapper<Site, RegistrationResponseDto> registrationResponseMapper;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        registrationService = new RegistrationService(personRepository, userService, new BCryptPasswordEncoder(),
                registrationRequestMapper, registrationResponseMapper);
    }

    @AfterEach
    public void after() {
        Mockito.reset(personRepository, userService, registrationRequestMapper, registrationResponseMapper);
    }

    @Test
    public void whenCreateThenRegistrationResponse() {
        RegistrationRequestDto request = new RegistrationRequestDto();
        request.setLogin("login");
        request.setPassword("password");
        Site site = new Site();
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        site.setUser(user);
        RegistrationResponseDto response = new RegistrationResponseDto();

        when(registrationRequestMapper.map(request)).thenReturn(site);
        when(userService.create(any())).thenReturn(Optional.of(user));
        when(personRepository.save(any())).thenReturn(site);
        when(registrationResponseMapper.map(site)).thenReturn(response);

        Optional<RegistrationResponseDto> actual = registrationService.create(request);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        assertNotNull(actual.get());
        assertEquals(response, actual.get());
        verify(registrationRequestMapper).map(request);
        verify(userService).create(any());
        verify(personRepository).save(any());
        verify(registrationResponseMapper).map(site);
        verifyNoMoreInteractions(personRepository, userService, registrationRequestMapper, registrationResponseMapper);
    }

    @Test
    public void whenCreateThenException() {
        RegistrationRequestDto request = new RegistrationRequestDto();
        request.setLogin("login");
        request.setPassword("password");
        Site site = new Site();
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        site.setUser(user);

        when(registrationRequestMapper.map(request)).thenReturn(site);
        when(userService.create(any())).thenReturn(Optional.of(user));
        doThrow(IllegalStateException.class).when(personRepository).save(any());

        Optional<RegistrationResponseDto> actual = registrationService.create(request);

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
        verify(registrationRequestMapper).map(request);
        verify(userService).create(any());
        verify(personRepository).save(any());
        verifyNoMoreInteractions(personRepository, userService, registrationRequestMapper, registrationResponseMapper);
    }
}