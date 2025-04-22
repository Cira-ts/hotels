CREATE SEQUENCE seq_room START WITH 1000;
GRANT SELECT, USAGE ON seq_room TO postgres;

CREATE TABLE room
(
    id BIGINT PRIMARY KEY,
    number INT NOT NULL,
    type VARCHAR(255) NOT NULL,
    price NUMERIC NOT NULL,
    balcony BOOLEAN NOT NULL,
    active BOOLEAN NOT NULL,
    hotel_id BIGINT NOT NULL,
    CONSTRAINT fk_hotel FOREIGN KEY (hotel_id) REFERENCES hotel(id)
);
