package ru.job4j.url.shortcut.repository;

public class Queries {

    public static final String REQUEST_COUNT_INCREMENT =
            "update Statistic set requestCount = requestCount + 1 where id = :id";
}
