package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.shortcut.dto.RedirectDto;
import ru.job4j.url.shortcut.service.RedirectService;

import java.net.URI;
import java.util.Optional;

/**
 * Контроллер переадресации url
 */
@RestController
@AllArgsConstructor
@RequestMapping("/redirect")
public class RedirectController {

    private RedirectService redirectService;

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        Optional<RedirectDto> redirect = redirectService.findByCode(code);
        return redirect.<ResponseEntity<Void>>map(
                redirectDto ->
                        ResponseEntity
                                .status(HttpStatus.FOUND)
                                .location(URI.create(redirectDto.getUrl())).build())
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
