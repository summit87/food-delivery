--liquibase formatted sql logicalFilePath: create-order-item-details.sql
--changeset sumit:create-order-item-details.sql
create table if not exists order_item_details
(
    id uuid DEFAULT gen_random_uuid
(
) PRIMARY KEY,
    order_id varchar
(
    200
),
    item_id varchar
(
    200
),
    FOREIGN KEY
(
    order_id
) REFERENCES order_details
(
    order_id
)

    );