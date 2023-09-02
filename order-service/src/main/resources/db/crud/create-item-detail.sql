--liquibase formatted sql logicalFilePath: item_detials.sql
--changeset sumit:item_detials.sql

create table if not exists item_details
(
    item_name varchar
(
    200
),
    item_id varchar
(
    50
) not null
    constraint item_details_pk
    primary key,
    item_price float,
    item_type varchar
(
    100
),
    availability_status varchar
(
    200
)
    );

