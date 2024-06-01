
CREATE TABLE `orders_1`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `order_type`   bigint(20),
    `customer_id`  bigint(20),
    `amount`       double,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE `orders_2`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `order_type`   bigint(20),
    `customer_id`  bigint(20),
    `amount`       double,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


