package ru.job4j.url.shortcut.dto.response;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import ru.job4j.url.shortcut.mapper.Mapper;
import ru.job4j.url.shortcut.model.Link;

/**
 * Маппер модели Link в ответ регистрации URL LinkResponseDto
 */
@Component
public class LinkResponseMapper implements Mapper<Link, LinkResponseDto> {

    private final ModelMapper mapper;

    public LinkResponseMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setSkipNullEnabled(true);
        TypeMap<Link, LinkResponseDto> propertyMapper =
                this.mapper.createTypeMap(Link.class, LinkResponseDto.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(Link::getCode, LinkResponseDto::setCode));
    }

    @Override
    public LinkResponseDto map(Link link) {
        return this.mapper.map(link, LinkResponseDto.class);
    }
}
