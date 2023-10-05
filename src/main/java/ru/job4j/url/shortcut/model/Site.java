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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * Модель сайта
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "sites")
public class Site {

    /** Идентификатор сайта */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** URL адреса */
    @NotBlank(message = "Не заполнен URL")
    private String url;

    /** Название сайта */
    @NotBlank(message = "Не заполнено название сайта")
    private String name;

    /** Пользователь */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /** Статистика */
    @ManyToOne
    @JoinColumn(name = "statistic_id")
    private Statistic statistic;
}
