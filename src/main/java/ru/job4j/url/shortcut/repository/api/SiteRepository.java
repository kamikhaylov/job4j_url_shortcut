package ru.job4j.url.shortcut.repository.api;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.model.Site;

/**
 * Репозиторий для взаимодействия с таблицей sites
 */
@Primary
@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {

}
