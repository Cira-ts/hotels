create sequence seq_reservation start with 1000;
grant select, usage on seq_reservation to postgres;

create table reservation
(
    id bigint not null primary key,
    booking_time timestamp(255) not null,
    check_in_date date not null,
    check_out_date date not null,
    room_id bigint not null,
    user_id bigint not null,
    status varchar(255) not null,
    constraint fk_book_room foreign key (room_id) references room(id),
    constraint fk_book_user foreign key (user_id) references app_user(id)
);

