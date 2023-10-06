package ru.job4j.url.shortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * Модель линка
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "links")
public class Link {

    /** Идентификатор линка */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** URL адреса */
    @NotBlank(message = "Не заполнен URL")
    private String url;

    /** Код */
    @NotBlank(message = "Не заполнен код")
    private String code;

    /** Статистика */
    @OneToOne
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;

    /** Пользователь */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
