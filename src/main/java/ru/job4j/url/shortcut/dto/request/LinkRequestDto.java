package ru.job4j.url.shortcut.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * ДТО запроса регистрации URL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LinkRequestDto {

    /** URL-адрес */
    @NotBlank(message = "Не заполнен URL-адрес")
    private String url;
}
