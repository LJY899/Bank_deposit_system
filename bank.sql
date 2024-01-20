create table employee
(
    id          bigint        not null comment '主键'
        primary key,
    name        varchar(32)   not null comment '姓名',
    username    varchar(32)   not null comment '用户名',
    password    varchar(64)   not null comment '密码',
    phone       varchar(11)   not null comment '手机号',
    sex         varchar(2)    not null comment '性别',
    id_number   varchar(18)   not null comment '身份证号',
    status      int default 1 not null comment '状态 0:禁用，1:正常',
    create_time datetime      not null comment '创建时间',
    update_time datetime      not null comment '更新时间',
    create_user bigint        not null comment '创建人',
    update_user bigint        not null comment '修改人',
    constraint idx_username
        unique (username)
)
    comment '员工信息' collate = utf8mb3_bin
                       row_format = DYNAMIC;

create table product
(
    product_id           bigint auto_increment comment '产品ID'
        primary key,
    name                 varchar(64)                              not null comment '名称',
    tenure               int                                      not null comment '产品期限（单位：月）',
    annual_interest_rate decimal(5, 2)  default 0.00              not null comment '年化利率（%）',
    min_deposit_amount   decimal(15, 2) default 0.00              not null comment '起存金额（元）',
    single_limit_amount  decimal(15, 2) default 0.00              not null comment '单人限额（元）',
    daily_limit_amount   decimal(15, 2) default 0.00              not null comment '单日限额（元）',
    risk_level           varchar(10)                              not null comment '风险等级',
    start_date           date                                     not null comment '起息日',
    interest_payment     varchar(50)                              null comment '结息方式',
    maturity_date        date                                     null comment '到期日',
    product_status       varchar(50)                              null comment '产品状态',
    sort                 int                                      null,
    create_time          timestamp      default CURRENT_TIMESTAMP null,
    update_time          timestamp      default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    create_user          varchar(255)                             null,
    update_user          varchar(255)                             null,
    constraint idx_product_name
        unique (name)
)
    comment '产品信息' collate = utf8mb3_bin
                       row_format = DYNAMIC;

create table orders
(
    id         bigint auto_increment comment '主键'
        primary key,
    number     varchar(50)    null comment '订单号',
    product_id bigint         null comment '产品',
    user_phone varchar(11)    not null comment '下单账户',
    amount     decimal(10, 2) not null comment '存款',
    time       datetime       null comment '下单时间',
    status     int default 1  not null comment '订单状态 1未到期 2已到期',
    constraint orders___pid
        foreign key (product_id) references product (product_id),
    constraint orders_user_phone_fk
        foreign key (user_phone) references user (phone)
)
    comment '订单表' collate = utf8mb3_bin;

create definer = root@localhost trigger before1_insert_orders
    before insert
    on orders
    for each row
    SET NEW.time = IFNULL(NEW.time, NOW());

create definer = root@localhost trigger before2_insert_orders
    before insert
    on orders
    for each row
BEGIN
    DECLARE next_order_number INT;

    -- 获取当前最大订单号
    SELECT MAX(CAST(number AS SIGNED)) + 1 INTO next_order_number
    FROM orders;

    -- 如果没有订单号，则从1开始
    IF next_order_number IS NULL THEN
        SET next_order_number = 1;
    END IF;

    -- 设置新的订单号
    SET NEW.number = next_order_number;
END;

create table user
(
    id          bigint        not null comment '主键'
        primary key,
    name        varchar(50)   null comment '姓名',
    phone       varchar(11)   not null comment '手机号',
    password    varchar(64)   not null,
    sex         varchar(2)    null comment '性别',
    card_number varchar(19)   not null comment '银行卡号',
    status      int default 0 null comment '状态 0:禁用，1:正常',
    constraint user_pk
        unique (phone)
)
    comment '用户信息' collate = utf8mb3_bin;

create table bankcard
(
    card_id          bigint auto_increment
        primary key,
    phone_number     varchar(11)                 not null,
    card_number      varchar(19)                 not null,
    payment_password varchar(64)                 not null,
    balance          decimal(10, 2) default 0.00 null,
    constraint card_number
        unique (card_number)
);

create definer = root@localhost view product_sale as
select `bank`.`orders`.`product_id`        AS `product_id`,
       `bank`.`product`.`name`             AS `name`,
       count(`bank`.`orders`.`product_id`) AS `total_sale`,
       sum(`bank`.`orders`.`amount`)       AS `total_amount`
from `bank`.`orders`
         join `bank`.`product`
where (`bank`.`orders`.`product_id` = `bank`.`product`.`product_id`)
group by `bank`.`orders`.`product_id`;

-- comment on column product_sale.product_id not supported: 产品

-- comment on column product_sale.name not supported: 名称

