package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.shortcut.service.AuthorizationService;

/**
 * Контроллер авторизации
 */
@RestController
@AllArgsConstructor
@RequestMapping("/authorization")
public class AuthorizationController {

    private final AuthorizationService authorizationService;


}
