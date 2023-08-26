--liquibase formatted sql logicalFilePath: customer.sql
--changeset sumit:customer_1.sql


create table if not exists customer_profile
(
    id      serial,
    user_id varchar(100) not null unique,
    f_name  varchar(20),
    l_name  varchar(40),
    user_password       varchar(100),
    pr_mobnbr           varchar(20) not null unique,
    sec_mobnbr          varchar(20),
    cntry_code          varchar(5),
    house_nbr           varchar(20),
    street_name         varchar(20),
    land_mark           varchar(30),
    zip_code            varchar(8),
    is_account_verified bool,
    create_ts           timestamp,
    last_updated_ts     timestamp,
    primary key (id)
);


