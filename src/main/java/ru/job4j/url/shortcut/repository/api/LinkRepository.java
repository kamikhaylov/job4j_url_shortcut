package ru.job4j.url.shortcut.repository.api;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.model.Link;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с таблицей links
 */
@Primary
@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {

    Optional<Link> findByCode(String code);

    List<Link> findAll();
}
