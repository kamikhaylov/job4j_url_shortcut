package ru.job4j.url.shortcut.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * ДТО запроса регистрации
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationRequestDto {

    /** Логин */
    @NotBlank(message = "Не заполнен логин")
    private String login;

    /** Пароль */
    @NotBlank(message = "Не заполнен пароль")
    private String password;

    /** Сайт */
    @NotBlank(message = "Не заполнен сайт")
    private String site;

    /** Название сайта */
    @NotBlank(message = "Не заполнено название сайта")
    private String name;

}
