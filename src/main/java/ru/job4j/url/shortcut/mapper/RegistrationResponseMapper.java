package ru.job4j.url.shortcut.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.job4j.url.shortcut.dto.response.RegistrationResponseDto;
import ru.job4j.url.shortcut.model.Site;

@Component
public class RegistrationResponseMapper implements Mapper<Site, RegistrationResponseDto> {

    private final ModelMapper mapper;

    public RegistrationResponseMapper() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public RegistrationResponseDto map(Site site) {
        return this.mapper.map(site, RegistrationResponseDto.class);
    }
}
