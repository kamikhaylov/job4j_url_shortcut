package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.dto.response.StatisticListResponseDto;
import ru.job4j.url.shortcut.mapper.Mapper;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.model.Statistic;
import ru.job4j.url.shortcut.repository.api.LinkRepository;
import ru.job4j.url.shortcut.repository.api.StatisticRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.job4j.url.shortcut.logging.UrlShortcutLogEvent.URL_CUT_0003;

/**
 * Сервис статистики
 */
@Service
@AllArgsConstructor
public class StatisticService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticService.class.getName());

    private final StatisticRepository statisticRepository;
    private final LinkRepository linkRepository;
    private final Mapper<Link, StatisticListResponseDto> statisticListResponseMapper;

    @Transactional
    public Optional<Statistic> create(Statistic statistic) {
        Optional<Statistic> result = Optional.empty();
        try {
            result = Optional.of(statisticRepository.save(statistic));
        } catch (Exception ex) {
            LOGGER.error(URL_CUT_0003.toString(), ex);
        }
        return result;
    }

    public List<StatisticListResponseDto> findAll() {
        List<Link> linkList = linkRepository.findAll();
        return linkList.stream()
                       .map(statisticListResponseMapper::map)
                       .collect(Collectors.toList());
    }

    @Transactional
    public void requestCountIncrement(int id) {
        statisticRepository.requestCountIncrement(id);
    }
}
