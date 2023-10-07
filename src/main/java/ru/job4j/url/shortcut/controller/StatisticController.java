package ru.job4j.url.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url.shortcut.dto.response.StatisticListResponseDto;
import ru.job4j.url.shortcut.service.StatisticService;

import java.util.List;

/**
 * Контроллер статистики
 */
@RestController
@AllArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/list")
    public ResponseEntity<List<StatisticListResponseDto>> getList() {
        List<StatisticListResponseDto> result = statisticService.findAll();
        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }
}
