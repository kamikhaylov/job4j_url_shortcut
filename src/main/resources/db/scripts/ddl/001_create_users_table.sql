create table if not exists users (
    id          serial           not null    primary key,
    login       varchar(36)      not null    unique,
    password    varchar(36)      not null
);

comment on table users is 'Пользователи';
comment on column users.id is 'Идентификатор пользователя';
comment on column users.login is 'Логин';
comment on column users.password is 'Пароль';