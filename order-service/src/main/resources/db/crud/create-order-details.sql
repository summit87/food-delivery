--liquibase formatted sql logicalFilePath: create-order-details.sql
--changeset sumit:create-order-details.sql
create table if not exists order_details
(
    order_id        varchar(200) not null
        constraint order_id_pk
            primary key,
    user_id         varchar(200),
    order_status    varchar(50),
    total_amount    float,
    payment_status  varchar(30),
    restaurant_id varchar(26),
    create_ts       timestamp,
    last_updated_ts timestamp
);