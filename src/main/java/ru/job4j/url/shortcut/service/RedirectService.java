package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.dto.RedirectDto;
import ru.job4j.url.shortcut.mapper.Mapper;
import ru.job4j.url.shortcut.model.Link;

import java.util.Optional;

/**
 * Сервис переадресации
 */
@Service
@AllArgsConstructor
public class RedirectService {

    private LinkService linkService;
    private StatisticService statisticService;
    private Mapper<Link, RedirectDto> redirectMapper;

    public Optional<RedirectDto> findByCode(String code) {
        Optional<RedirectDto> result = Optional.empty();
        Optional<Link> link = linkService.findByCode(code);
        if (link.isPresent()) {
            statisticService.requestCountIncrement(link.get().getStatistic().getId());
            result = Optional.of(redirectMapper.map(link.get()));
        }
        return result;
    }
}
