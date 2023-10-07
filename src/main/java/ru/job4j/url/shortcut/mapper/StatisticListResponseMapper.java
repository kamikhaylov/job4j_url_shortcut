package ru.job4j.url.shortcut.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.job4j.url.shortcut.dto.response.StatisticListResponseDto;
import ru.job4j.url.shortcut.model.Link;

/**
 * Маппер модели Link в ДТО ответа статистики StatisticListResponseDto
 */
@Component
public class StatisticListResponseMapper implements Mapper<Link, StatisticListResponseDto> {

    private final ModelMapper mapper;

    public StatisticListResponseMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setSkipNullEnabled(true);
        this.mapper.createTypeMap(Link.class, StatisticListResponseDto.class)
                .addMappings(mapper -> mapper.map(Link::getUrl, StatisticListResponseDto::setUrl))
                .addMappings(mapper -> mapper.map(
                        link -> link.getStatistic().getRequestCount(), StatisticListResponseDto::setTotal));
    }

    @Override
    public StatisticListResponseDto map(Link link) {
        return this.mapper.map(link, StatisticListResponseDto.class);
    }
}
