create sequence seq_hotel start with 1000;
grant select, usage on seq_hotel to postgres;

CREATE TABLE hotel
(
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(25) NOT NULL,
    email VARCHAR(255) UNIQUE,
    website VARCHAR(255) UNIQUE,
    active BOOLEAN NOT NULL,
    description TEXT,
    price NUMERIC,
    author_id BIGINT NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES app_user(id)
);



