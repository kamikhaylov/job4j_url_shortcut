create table if not exists sites (
    id                  serial           not null    primary key,
    site                varchar          not null    unique,
    name                varchar          not null,
    user_id             int              not null    references users(id)
);

comment on table sites is 'Сайты';
comment on column sites.id is 'Идентификатор сайта';
comment on column sites.site is 'Сайт';
comment on column sites.name is 'Название сайта';