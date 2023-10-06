create table if not exists links (
    id                  serial           not null    primary key,
    url                 varchar          not null    unique,
    code                varchar          not null,
    statistic_id        int              not null    references statistics(id),
    user_id             int              not null    references users(id)
);

comment on table links is 'Линки';
comment on column links.id is 'Идентификатор линка';
comment on column links.url is 'URL адреса';
comment on column links.code is 'Код сайта';
comment on column links.statistic_id is 'Идентификатор статистики';
comment on column links.user_id is 'Идентификатор пользователя';