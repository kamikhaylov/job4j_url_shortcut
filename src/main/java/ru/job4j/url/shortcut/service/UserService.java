package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.User;
import ru.job4j.url.shortcut.repository.api.UserRepository;

import java.util.Optional;

import static ru.job4j.url.shortcut.logging.UrlShortcutLogEvent.URL_CUT_0002;

/**
 * Сервис пользователя
 */
@Service
@AllArgsConstructor
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class.getName());

    private final UserRepository userRepository;

    public Optional<User> create(User user) {
        Optional<User> result = Optional.empty();
        try {
            result = Optional.of(userRepository.save(user));
        } catch (Exception ex) {
            LOGGER.error(URL_CUT_0002.toString(), ex);
        }
        return result;
    }
}
