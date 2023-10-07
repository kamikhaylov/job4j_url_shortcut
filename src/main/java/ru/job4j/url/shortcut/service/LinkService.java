package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.dto.request.LinkRequestDto;
import ru.job4j.url.shortcut.dto.response.LinkResponseDto;
import ru.job4j.url.shortcut.mapper.Mapper;
import ru.job4j.url.shortcut.model.Link;
import ru.job4j.url.shortcut.model.Statistic;
import ru.job4j.url.shortcut.model.User;
import ru.job4j.url.shortcut.repository.api.LinkRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static ru.job4j.url.shortcut.logging.UrlShortcutLogEvent.URL_CUT_0005;

/**
 * Сервис регистрации
 */
@Service
@AllArgsConstructor
public class LinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkService.class.getName());

    private final LinkRepository linkRepository;
    private final StatisticService statisticService;
    private final UserService userService;
    private final Mapper<LinkRequestDto, Link> linkRequestMapper;
    private final Mapper<Link, LinkResponseDto> linkResponseMapper;

    @Transactional
    public Optional<LinkResponseDto> create(LinkRequestDto request, String login) {
        Optional<LinkResponseDto> result = Optional.empty();
        Link link = linkRequestMapper.map(request);
        link.setCode(UUID.randomUUID().toString());

        Optional<User> user = userService.findByLogin(login);
        Optional<Statistic> statistic = statisticService.create(new Statistic(0, 0));

        if (user.isPresent() && statistic.isPresent()) {
            link.setUser(user.get());
            link.setStatistic(statistic.get());

            try {
                result =  Optional.of(linkResponseMapper.map(linkRepository.save(link)));
            } catch (Exception ex) {
                LOGGER.error(URL_CUT_0005.toString(), ex);
            }
        }

        return result;
    }

    public Optional<Link> findByCode(String code) {
        return linkRepository.findByCode(code);
    }
}
