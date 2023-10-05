create table if not exists statistics (
    id              serial    not null    primary key,
    request_count   int
);

comment on table statistics is 'Статистика сайтов';
comment on column statistics.id is 'Идентификатор статистических данных';
comment on column statistics.request_count is 'Количество вывозов сайта';