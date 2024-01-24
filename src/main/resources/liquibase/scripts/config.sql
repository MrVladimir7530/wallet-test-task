--liquibase formatted sql

--changeset Volkov:create_table_wallet
create table wallet(
    uuid bigint primary key,
    account numeric(19,2) not null
);

