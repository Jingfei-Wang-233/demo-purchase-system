CREATE TABLE if not EXISTS `role`
(
    `id`                bigint(11)  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role_name`              varchar(16) NOT NULL
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;