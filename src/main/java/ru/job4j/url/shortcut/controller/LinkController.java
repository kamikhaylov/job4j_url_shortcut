package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.shortcut.dto.request.LinkRequestDto;
import ru.job4j.url.shortcut.dto.response.LinkResponseDto;
import ru.job4j.url.shortcut.service.LinkService;
import ru.job4j.url.shortcut.utlis.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

import static ru.job4j.url.shortcut.security.JwtAuthenticationFilter.HEADER_AUTHORIZATION;

/**
 * Контроллер регистрации url
 */
@RestController
@AllArgsConstructor
@RequestMapping("/link")
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/convert")
    public ResponseEntity<LinkResponseDto> convert(
            @Valid @RequestBody LinkRequestDto request,
            HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(HEADER_AUTHORIZATION);
        String login = TokenUtils.getLogin(token);
        Optional<LinkResponseDto> result = linkService.create(request, login);
        return result.map(response -> new ResponseEntity<>(response, HttpStatus.CREATED))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    }
}
