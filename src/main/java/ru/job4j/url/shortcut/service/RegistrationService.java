package ru.job4j.url.shortcut.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.url.shortcut.dto.request.RegistrationRequestDto;
import ru.job4j.url.shortcut.dto.response.RegistrationResponseDto;
import ru.job4j.url.shortcut.mapper.Mapper;
import ru.job4j.url.shortcut.model.Site;
import ru.job4j.url.shortcut.repository.api.SiteRepository;

import java.util.Optional;

import static ru.job4j.url.shortcut.logging.UrlShortcutLogEvent.URL_CUT_0001;

/**
 * Сервис регистрации
 */
@Service
@AllArgsConstructor
public class RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class.getName());

    private final SiteRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper<RegistrationRequestDto, Site> registrationRequestMapper;
    private final Mapper<Site, RegistrationResponseDto> registrationResponseMapper;

    /**
     * Создать сайт.
     *
     * @param requestDto ДТО запроса регистрации
     * @return ДТО ответа регистрации
     */
    public Optional<RegistrationResponseDto> create(RegistrationRequestDto requestDto) {
        Site site = registrationRequestMapper.map(requestDto);

        System.out.println(site);

        site.getUser().setPassword(passwordEncoder.encode(site.getUser().getPassword()));
        Optional<RegistrationResponseDto> result = Optional.empty();

        try {
            result = Optional.of(registrationResponseMapper.map(personRepository.save(site)));
        } catch (Exception ex) {
            LOGGER.error(URL_CUT_0001.toString(), ex);
        }
        return result;
    }
}
