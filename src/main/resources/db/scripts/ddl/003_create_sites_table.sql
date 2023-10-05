create table if not exists sites (
    id                  serial           not null    primary key,
    url                 varchar(1024)    not null    unique,
    name                varchar(1024)    not null,
    user_id             int              not null    references users(id),
    statistic_id        int              not null    references statistics(id)
);

comment on table sites is 'Сайты';
comment on column sites.id is 'Идентификатор сайта';
comment on column sites.url is 'URL адреса';
comment on column sites.name is 'Название сайта';
comment on column sites.user_id is 'Идентификатор пользователя';
comment on column sites.statistic_id is 'Идентификатор статистики';