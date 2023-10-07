package ru.job4j.url.shortcut.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.job4j.url.shortcut.dto.RedirectDto;
import ru.job4j.url.shortcut.model.Link;

/**
 * Маппер модель Link в RedirectDto
 */
@Component
public class RedirectMapper implements Mapper<Link, RedirectDto> {

    private final ModelMapper mapper;

    public RedirectMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setSkipNullEnabled(true);
        this.mapper.createTypeMap(Link.class, RedirectDto.class)
                .addMappings(mapper -> mapper.map(Link::getUrl, RedirectDto::setUrl));
    }

    @Override
    public RedirectDto map(Link link) {
        return this.mapper.map(link, RedirectDto.class);
    }
}
