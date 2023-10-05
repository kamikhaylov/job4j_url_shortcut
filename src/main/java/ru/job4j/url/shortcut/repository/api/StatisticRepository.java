package ru.job4j.url.shortcut.repository.api;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.model.Statistic;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с таблицей statistics
 */
@Primary
@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Integer> {

}
