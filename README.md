# job4j_url_shortcut
Проект "Сокращение URL-адресов"

[![github actions][actions-image]][actions-url]
[![coverage][codecov-image]][codecov-url]

Сервис для безопасного предоставления сокращенных URL-адресов сайтов.
Работает через REST API.

Сервис предоставляет:
- Регистрацию сайта. Сервисом могут пользоваться разные сайты. Каждому сайту выдается пара логин и пароль.
- Авторизация сайта через JWT.
- Регистрация URL. После того, как пользователь зарегистрировал свой сайт, он может отправлять на сайт
ссылки и получать преобразованные ссылки.
- Переадресация сайта выполняется без авторизации.
- Статистика сайта. В сервисе считается количество вызовов каждого адреса.

### Используемые технологии
- Java 17
- Spring Boot 3.1.4
- Maven 3.8
- Git
- Lombok 1.18.22
- Junit 5

### Требуемое окружение
- JDK 17
- Apache Maven 3.8.5
- PostgreSQL 13
- Браузер

### Подготовка к запуску приложения
- Создать БД accidents хост `jdbc:postgresql://localhost:5432/url_shortcut`
- Собрать jar с приложением, выполнив команду `mvn install`
- Запустить приложение из папки target, выполнив команду: `java -jar job4j_url_shortcut-1.0-SNAPSHOT.jar`

### Контакты
kanmikhaylov@gmail.com

[actions-image]: https://github.com/kamikhaylov/job4j_url_shortcut/actions/workflows/maven.yml/badge.svg
[actions-url]: https://github.com/kamikhaylov/job4j_url_shortcut/actions/workflows/maven.yml
[codecov-image]: https://codecov.io/gh/kamikhaylov/job4j_url_shortcut/graph/badge.svg?token=
[codecov-url]: https://codecov.io/gh/kamikhaylov/job4j_url_shortcut