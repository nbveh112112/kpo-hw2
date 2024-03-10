--liquibase formatted sql

--changeset Timur:1
create table users
(
    id           uuid primary key,
    username         varchar(255) unique,
    password  varchar(255),
    type     varchar(255),
    token   varchar(255) unique
);
--rollback drop table users;

--changeset Timur:2
create table products
(
    id           uuid primary key,
    name         varchar(255),
    description  varchar(1000),
    price        int,
    time         int
);
--rollback drop table product;

--changeset Timur:3
create table menu
(
    product_id   uuid primary key references products (id)
);
--rollback drop table product;

--changeset Timur:4
create table orders
(
    id           uuid primary key,
    owner_id     uuid references users (id),
    status      varchar(255)
);
--rollback drop table product;

--changeset Timur:5
create table order_content
(
    id           uuid primary key,
    order_id     uuid references orders (id),
    product_id   uuid references products (id),
    start_time   timestamp
);
--rollback drop table product;
