--liquibase formatted sql logicalFilePath: create_delivery_details.sql
--changeset sumit:create_delivery_details.sql
create table if not exists delivery_details
(
    order_id                       varchar(200),
    restaurant_id                  varchar(50),
    delivery_status                varchar(20),
    delivery_charge_payment_status varchar(26),
    delivery_charge                DECIMAL(7, 3),
    create_ts                      timestamp,
    last_updated_ts                timestamp,
    primary key (order_id, restaurant_id)
);