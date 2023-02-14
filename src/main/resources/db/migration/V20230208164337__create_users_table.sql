CREATE TABLE if not EXISTS `users`
(
    `id`                bigint(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role_id`           bigint(11)  NOT NULL,
    `username`          varchar(16) NOT NULL UNIQUE,
    `password`          varchar(100) NOT NULL,
    `level`           int(11) NOT NULL DEFAULT 0
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;