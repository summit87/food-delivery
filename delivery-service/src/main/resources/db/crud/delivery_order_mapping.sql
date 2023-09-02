--liquibase formatted sql logicalFilePath: delivery_order_mapping.sql
--changeset sumit:delivery_order_mapping_1
create table if not exists delivery_order_mapping
(
    delivery_id
    serial,
    order_id
    varchar
(
    100
),
    restaurant_id varchar
(
    100
),
    primary key
(
    delivery_id
)
    );