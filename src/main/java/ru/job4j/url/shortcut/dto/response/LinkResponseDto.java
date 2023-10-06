package ru.job4j.url.shortcut.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * ДТО ответаа регистрации URL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LinkResponseDto {

    /** Код URL-адрес */
    @NotBlank(message = "Не заполнен код URL-адрес")
    private String code;
}
