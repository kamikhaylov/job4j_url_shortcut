package ru.job4j.url.shortcut.logging;

/**
 * Информация о логируемом событие.
 */
public enum UrlShortcutLogEvent implements LogEvent {

    URL_CUT_0001("Ошибка создания пользователя");

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
