package ru.job4j.url.shortcut.logging;

/**
 * Информация о логируемом событие.
 */
public enum UrlShortcutLogEvent implements LogEvent {

    URL_CUT_0001("Ошибка регистрации сайта"),
    URL_CUT_0002("Ошибка создания пользователя"),
    URL_CUT_0003("Ошибка создания статистики сайта"),
    URL_CUT_0004("Пользователь не найден"),
    URL_CUT_0005("Ошибка сохранения линка");

    private final String title;

    UrlShortcutLogEvent(String title) {
        this.title = title;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return getCode() + ". " + getTitle();
    }
}
