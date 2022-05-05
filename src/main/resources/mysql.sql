create database spring_transaction;
use spring_transaction;
create table t_order
(
    id          bigint(20) auto_increment,
    order_no    varchar(64)    not null,
    amount      decimal(10, 2) not null,
    create_time datetime       not null,
    primary key (id),
    unique uk_order_no (order_no)
);

create table t_order_detail
(
    id          bigint(20) auto_increment,
    order_id    bigint(20)   not null,
    good_name   varchar(128) not null,
    create_time datetime     not null,
    primary key (id),
    unique uk_order_id (order_id)
);