--liquibase formatted sql logicalFilePath: restaurant.sql
--changeset sumit:restaurant.sql

create table if not exists restaurant_name
(
    id               uuid default uuid_generate_v4(),
    rstrnt_name      varchar(200),
    is_rstrnt_active bool,
    rating           int,
    category         json,
    user_name        varchar(20),
    email_id         varchar(100),
    password         varchar(20),
    meta_info        json,
    create_ts        timestamp,
    last_updated_ts  timestamp,
    create_by        varchar(20),
    updated_by       varchar(20),
    primary key (id)

);

--changeset sumit:restaurant_address

create table if not exists restaurant_address
(
    id                uuid default uuid_generate_v4(),
    restaurant_id     uuid,
    country_code      varchar(5),
    state             varchar(50),
    city              varchar(50),
    area_name         varchar(40),
    street            varchar(20),
    zip_code          varchar(10),
    create_ts         timestamp,
    is_address_active bool,
    last_updated_ts   timestamp,
    create_by         varchar(20),
    updated_by        varchar(20),
    primary key (id)
);

--changeset sumit:restaurant_contact

create table if not exists restaurant_contact
(
    id                      uuid default uuid_generate_v4(),
    restaurant_id           uuid not null,
    address_id              uuid not null,
    primary_mobile          varchar(20),
    secondary_mobile        varchar(20),
    is_mob_notiftn_ative    bool,
    is_email_notiftn_active bool,
    create_ts               timestamp,
    last_updated_ts         timestamp,
    create_by               varchar(20),
    updated_by              varchar(20),
    primary key (id)
);