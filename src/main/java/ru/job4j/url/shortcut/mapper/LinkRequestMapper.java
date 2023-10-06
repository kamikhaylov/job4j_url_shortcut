package ru.job4j.url.shortcut.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import ru.job4j.url.shortcut.dto.request.LinkRequestDto;
import ru.job4j.url.shortcut.model.Link;

/**
 * Маппер запроса регистрации URL LinkRequestDto в модель Link
 */
@Component
public class LinkRequestMapper implements Mapper<LinkRequestDto, Link> {

    private final ModelMapper mapper;

    public LinkRequestMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setSkipNullEnabled(true);
        TypeMap<LinkRequestDto, Link> propertyMapper =
                this.mapper.createTypeMap(LinkRequestDto.class, Link.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(LinkRequestDto::getUrl, Link::setUrl));
    }

    @Override
    public Link map(LinkRequestDto request) {
        return this.mapper.map(request, Link.class);
    }
}
