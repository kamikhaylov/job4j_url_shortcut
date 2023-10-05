package ru.job4j.url.shortcut.repository.api;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.model.User;

import java.util.Optional;

/**
 * Репозиторий для взаимодействия с таблицей users
 */
@Primary
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByLogin(String login);
}
