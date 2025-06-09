create sequence seq_app_user start with 1000;
grant select, usage on seq_app_user to postgres;
create table app_user
(
    id                          bigint         not null primary key,
    first_name                  varchar(255)   not null,
    last_name                   varchar(255)   not null,
    country                     varchar(255)   not null,
    email                       varchar(255)   not null,
    password                    varchar(255)   not null,
    status                      varchar(255)   not null,
    registration_date           timestamp(255) not null,
    role                        varchar(255)   not null,
    constraint unique_email unique (email)
);

create sequence seq_app_user_token start with 1000;
grant select, usage on seq_app_user_token to postgres;
create table app_user_token
(
    id                          bigint       not null primary key,
    token                       varchar(255) not null,
    token_type                  varchar(255) not null,
    revoked                     boolean      not null,
    expired                     boolean      not null,
    app_user_id   bigint     not null,
    foreign key (app_user_id) references app_user (id),
    constraint unique_token unique (token)
);
