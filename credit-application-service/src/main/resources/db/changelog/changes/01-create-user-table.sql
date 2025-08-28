--liquibase formatted sql
--changeset mrkotyaka:1
CREATE TABLE IF NOT EXISTS credit_applications (
                                     id BIGINT PRIMARY KEY,
                                     amount NUMERIC(19,2) NOT NULL,
                                     term INTEGER NOT NULL,
                                     income NUMERIC(19,2) NOT NULL,
                                     current_credit_load NUMERIC(19,2) NOT NULL,
                                     credit_rating INTEGER NOT NULL,
                                     status VARCHAR(20) NOT NULL DEFAULT 'PROCESSING'
);
--rollback DROP TABLE ORDERS;
--rollback DROP TABLE CUSTOMERS;

