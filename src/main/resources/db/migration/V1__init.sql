create table search_user_request
(
    id               bigserial    not null,
    query            varchar(255) not null,
    correlation_id   varchar(255) not null,
    is_complete      boolean      not null,
    search_result_id bigint,
    primary key (id)
);

create table search_result
(
    id      bigserial not null,
    is_find boolean   not null,
    primary key (id)
);

create table search_result_finds
(
    search_result_id bigint,
    source           varchar(255),
    link             varchar(500)
);

alter table search_user_request
    add constraint fk_search_user_request_search_result_id foreign key (search_result_id) references search_result (id);
alter table search_result_finds
    add constraint fk_search_result_finds_search_result_id foreign key (search_result_id) references search_result (id);