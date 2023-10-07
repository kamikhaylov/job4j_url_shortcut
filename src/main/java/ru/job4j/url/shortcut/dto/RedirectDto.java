package ru.job4j.url.shortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * ДТО переадресации
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RedirectDto {

    /** URL-адрес */
    @NotBlank(message = "Не заполнен URL-адрес")
    private String url;
}
