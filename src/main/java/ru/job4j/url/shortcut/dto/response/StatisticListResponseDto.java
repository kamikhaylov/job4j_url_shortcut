package ru.job4j.url.shortcut.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * ДТО ответа списка статистик
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatisticListResponseDto {

    /** URL-адрес */
    @NotBlank(message = "Не заполнен URL-адрес")
    private String url;

    /** Количество вызовов адреса */
    private Integer total;

}
