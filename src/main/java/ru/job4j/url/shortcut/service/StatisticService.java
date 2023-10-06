package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.model.Statistic;
import ru.job4j.url.shortcut.repository.api.StatisticRepository;

import java.util.Optional;

import static ru.job4j.url.shortcut.logging.UrlShortcutLogEvent.URL_CUT_0003;

/**
 * Сервис статистики
 */
@Service
@AllArgsConstructor
public class StatisticService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticService.class.getName());

    private final StatisticRepository statisticRepository;

    public Optional<Statistic> create(Statistic statistic) {
        Optional<Statistic> result = Optional.empty();
        try {
            result = Optional.of(statisticRepository.save(statistic));
        } catch (Exception ex) {
            LOGGER.error(URL_CUT_0003.toString(), ex);
        }
        return result;
    }
}
