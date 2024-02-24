--liquibase formatted sql

--changeset Danilov Alexey:1
create table product
(
    id           uuid primary key,
    name         varchar(255),
    description  varchar(1000),
    image_url     varchar(255),
    price        int,
    protein      int,
    fat          int,
    carbohydrate int
);
--rollback drop table product;
