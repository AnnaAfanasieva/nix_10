drop database if exists bank_application;
create database bank_application;
use bank_application;
drop table if exists user_table;
drop table if exists account_table;
drop table if exists category_table;
drop table if exists transactions_table;

create table user_table
(
    id           bigint auto_increment
        primary key,
    created      datetime(6)  null,
    updated      datetime(6)  null,
    user_name varchar(255) not null,
    email varchar(255) not null
);

create table account_table
(
    id        bigint auto_increment
        primary key,
    created   datetime(6)  null,
    updated   datetime(6)  null,
    user_id    bigint not null,
    foreign key (user_id) references user_table (id),
    balance bigint not null
);

create table category_table
(
    id           bigint auto_increment
        primary key,
    created      datetime(6)  null,
    updated      datetime(6)  null,
    category_name varchar(255) not null,
    category_type varchar(255) not null
);

create table transactions_table
(
    id        bigint auto_increment
        primary key,
    created   datetime(6)  null,
    updated   datetime(6)  null,
    account_id    bigint not null,
    foreign key (account_id) references account_table (id),
    category_id    bigint not null,
    foreign key (category_id) references category_table (id),
    sum bigint not null
);