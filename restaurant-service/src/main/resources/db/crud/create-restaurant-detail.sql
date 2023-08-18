--liquibase formatted sql logicalFilePath: create-restaurant-detail.sql
--changeset sumit:create-restaurant-detail.sql
create table if not exists restaurant
(
    order_id                varchar(50) not null
        constraint restaurant_details_pk
            primary key,
    restaurant_id           varchar(50),
    delivery_id             varchar(20),
    delivery_status         varchar(20),
    payment_status          varchar(20),
    restaurant_order_status varchar(20),
    create_ts               timestamp,
    last_updated_ts         timestamp,
    create_by               varchar(20),
    updated_by              varchar(20)
);

