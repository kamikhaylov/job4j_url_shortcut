package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.shortcut.dto.request.RegistrationRequestDto;
import ru.job4j.url.shortcut.dto.response.RegistrationResponseDto;
import ru.job4j.url.shortcut.service.RegistrationService;

import java.util.Optional;

/**
 * Контроллер регистрации
 */
@RestController
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/sign-up")
    public ResponseEntity<RegistrationResponseDto> registration(
            @RequestBody RegistrationRequestDto requestDto) {
        Optional<RegistrationResponseDto> result = registrationService.create(requestDto);

        return result.map(response -> new ResponseEntity<>(response, HttpStatus.CREATED))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    }
}
