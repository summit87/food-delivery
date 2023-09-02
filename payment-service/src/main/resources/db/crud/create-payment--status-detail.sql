--liquibase formatted sql logicalFilePath: create-payment--status-detail.sql
--changeset sumit:create-payment--status-detail.sql

create table if not exists payment_status_details
(
    transaction_id varchar
(
    200
) not null
    constraint payment_status_details_pk
    primary key,
    order_id varchar
(
    50
),
    restaurant_id varchar
(
    50
),
    item_price float,
    payment_status varchar
(
    20
)
    );

