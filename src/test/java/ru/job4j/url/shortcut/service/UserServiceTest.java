package ru.job4j.url.shortcut.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.url.shortcut.model.User;
import ru.job4j.url.shortcut.repository.api.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

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
    public void whenCreateThenUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        Optional<User> actual = userService.create(user);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        assertNotNull(actual.get());
        assertEquals(user, actual.get());
        verify(userRepository).save(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void whenCreateThenException() {
        User user = new User();
        doThrow(IllegalStateException.class).when(userRepository).save(user);

        Optional<User> actual = userService.create(user);

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
        verify(userRepository).save(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void whenFindByLoginThenUser() {
        User user = new User();
        String login = "login";
        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));

        Optional<User> actual = userService.findByLogin(login);

        assertNotNull(actual);
        assertTrue(actual.isPresent());
        assertNotNull(actual.get());
        assertEquals(user, actual.get());
        verify(userRepository).findByLogin(login);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void whenFindByLoginThenException() {
        String login = "login";

        doThrow(IllegalStateException.class).when(userRepository).findByLogin(login);

        Optional<User> actual = userService.findByLogin(login);

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
        verify(userRepository).findByLogin(login);
        verifyNoMoreInteractions(userRepository);
    }
}