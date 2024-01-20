--liquibase formatted sql logicalFilePath: create-restaurant-detail.sql
--changeset sumit:create-restaurant-profile
create table if not exists restaurant_profile
(
    restaurant_id
                    varchar(50) not null
        constraint restaurant_details_profile_pk
            primary key,
    restaurant_name varchar(50) not null unique,
    prmry_mob_num   varchar(20) not null unique,
    secndy_mob_num  varchar(20),
    email_address   varchar(20),
    create_ts       timestamp,
    last_updated_ts timestamp,
    create_by       varchar(20),
    updated_by      varchar(20)
);

--changeset sumit:create-restaurant-profile-address
create table if not exists restaurant_address
(
    restaurant_address_id
                    varchar(50) not null
        constraint restaurant_details_address_pk
            primary key,
    restaurant_id   varchar(50) not null,
    address         varchar(50),
    street_name     varchar(20),
    landmark        varchar(20),
    pin_code        varchar(20),
    create_ts       timestamp,
    last_updated_ts timestamp,
    create_by       varchar(20),
    updated_by      varchar(20),
    foreign key (restaurant_id) references restaurant_profile (restaurant_id)
);

