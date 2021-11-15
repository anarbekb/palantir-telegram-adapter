create table users
(
    id          bigserial    not null,
    telegram_id bigserial    not null,
    first_name  varchar(255) not null,
    user_name   varchar(255),
    state       varchar(255) not null,
    primary key (id)
);

create unique index index_telegram_id on users (telegram_id);