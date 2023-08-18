--liquibase formatted sql logicalFilePath: order_delivery_details.sql
--changeset sumit:order_delivery_details_1
create table if not exists order_delivery_details
(
    order_delivery_id serial,
    delivery_id       int,
    delivery_status   varchar(20),
    create_ts         timestamp,
    last_updated_ts   timestamp,
    primary key (order_delivery_id)
);