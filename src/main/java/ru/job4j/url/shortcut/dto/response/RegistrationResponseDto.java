package ru.job4j.url.shortcut.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * ДТО ответа регистрации
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationResponseDto {

    /** Сайт */
    @NotBlank(message = "Не заполнен сайт")
    private String site;

}
