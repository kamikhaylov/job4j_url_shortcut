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

    /** URL адреса */
    @NotBlank(message = "Не заполнен URL")
    private String url;

}
