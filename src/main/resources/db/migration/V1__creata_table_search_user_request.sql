create table search_user_request
(
    id             bigserial    not null,
    query          varchar(255) not null,
    correlation_id varchar(255) not null,
    is_complete    boolean      not null,
    primary key (id)
);