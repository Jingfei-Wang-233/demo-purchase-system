CREATE TABLE if not EXISTS `products`
(
    `id`                bigint(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title`             varchar(25) NOT NULL,
    `price`             DECIMAL(13,2) NOT NULL DEFAULT 0.00,
    `description`       varchar(500) NOT NULL,
    `tier`              int(11) NOT NULL DEFAULT 0,
    `platform`          varchar(25) NOT NULL
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;