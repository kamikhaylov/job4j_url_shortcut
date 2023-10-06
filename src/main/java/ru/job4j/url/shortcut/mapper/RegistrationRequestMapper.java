package ru.job4j.url.shortcut.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import ru.job4j.url.shortcut.dto.request.RegistrationRequestDto;
import ru.job4j.url.shortcut.model.Site;

/**
 * Маппер запроса регистрации RegistrationRequestDto в модель Site
 */
@Component
public class RegistrationRequestMapper implements Mapper<RegistrationRequestDto, Site> {

    private final ModelMapper mapper;

    public RegistrationRequestMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setSkipNullEnabled(true);
        TypeMap<RegistrationRequestDto, Site> propertyMapper =
                this.mapper.createTypeMap(RegistrationRequestDto.class, Site.class);
        propertyMapper.addMapping(
                RegistrationRequestDto::getLogin,
                (site, login) -> site.getUser().setLogin((String) login));
        propertyMapper.addMapping(
                RegistrationRequestDto::getPassword,
                (site, password) -> site.getUser().setPassword((String) password));
        propertyMapper.addMappings(
                mapper -> mapper.map(RegistrationRequestDto::getSite, Site::setSite));
        propertyMapper.addMappings(
                mapper -> mapper.map(RegistrationRequestDto::getName, Site::setName));
    }

    @Override
    public Site map(RegistrationRequestDto requestDto) {
        return this.mapper.map(requestDto, Site.class);
    }
}
