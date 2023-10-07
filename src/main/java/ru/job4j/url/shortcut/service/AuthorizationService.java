package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.User;
import ru.job4j.url.shortcut.repository.api.UserRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;

/**
 * Сервис авторизации
 */
@Service
@AllArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(login);
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getLogin(),
                user.get().getPassword(),
                emptyList());
    }
}
