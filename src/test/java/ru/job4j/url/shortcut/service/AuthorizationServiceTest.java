package ru.job4j.url.shortcut.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.job4j.url.shortcut.model.User;
import ru.job4j.url.shortcut.repository.api.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class AuthorizationServiceTest {

    @InjectMocks
    private AuthorizationService authorizationService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void after() {
        Mockito.reset(userRepository);
    }

    @Test
    public void whenLoadUserByUsernameThenUserDetails() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));

        UserDetails actual = authorizationService.loadUserByUsername(user.getLogin());

        assertNotNull(actual);
        assertEquals(user.getLogin(), actual.getUsername());
        assertEquals(user.getPassword(), actual.getPassword());
        verify(userRepository).findByLogin(user.getLogin());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void whenLoadUserByUsernameThenException() {
        String login = "login";

        when(userRepository.findByLogin(login)).thenReturn(Optional.empty());

        try {
            authorizationService.loadUserByUsername(login);
            fail();
        } catch (UsernameNotFoundException ex) {
            verify(userRepository).findByLogin(login);
            verifyNoMoreInteractions(userRepository);
        }
    }
}