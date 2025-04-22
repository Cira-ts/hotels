CREATE SEQUENCE seq_user START WITH 1000;
GRANT SELECT, USAGE ON seq_user TO postgres;

CREATE TABLE _user (
    id          BIGINT PRIMARY KEY,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    email       VARCHAR(255) UNIQUE NOT NULL,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(255) NOT NULL
);