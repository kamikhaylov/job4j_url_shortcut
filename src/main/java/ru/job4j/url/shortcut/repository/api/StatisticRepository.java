package ru.job4j.url.shortcut.repository.api;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.url.shortcut.model.Statistic;

/**
 * Репозиторий для взаимодействия с таблицей statistics
 */
@Primary
@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Integer> {

    @Modifying
    @Query("update Statistic set requestCount = requestCount + 1 where id = :id")
    void increment(@Param("id") int id);
}
