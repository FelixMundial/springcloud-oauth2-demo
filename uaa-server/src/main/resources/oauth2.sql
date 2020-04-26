USE `security`;

-- customized oauth_client_details table
CREATE TABLE IF NOT EXISTS `clientdetails`
(
    `appid`                  VARCHAR(128) NOT NULL,
    `resourceids`            VARCHAR(256)  DEFAULT NULL,
    `appsecret`              VARCHAR(256)  DEFAULT NULL,
    `scope`                  VARCHAR(256)  DEFAULT NULL,
    `granttypes`             VARCHAR(256)  DEFAULT NULL,
    `redirecturl`            VARCHAR(256)  DEFAULT NULL,
    `authorities`            VARCHAR(256)  DEFAULT NULL,
    `access_token_validity`  INT(11)       DEFAULT NULL,
    `refresh_token_validity` INT(11)       DEFAULT NULL,
    `additionalinformation`  VARCHAR(4096) DEFAULT NULL,
    `autoapprovescopes`      VARCHAR(256)  DEFAULT NULL,
    PRIMARY KEY (`appid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = `utf8`;

CREATE TABLE IF NOT EXISTS `oauth_client_details`
(
    `client_id`               VARCHAR(256) NOT NULL,
    `resource_ids`            VARCHAR(256)  DEFAULT NULL,
    `client_secret`           VARCHAR(256)  DEFAULT NULL,
    `scope`                   VARCHAR(256)  DEFAULT NULL,
    `authorized_grant_types`  VARCHAR(256)  DEFAULT NULL,
    `web_server_redirect_uri` VARCHAR(256)  DEFAULT NULL,
    `authorities`             VARCHAR(256)  DEFAULT NULL,
    `access_token_validity`   INT(11)       DEFAULT NULL,
    `refresh_token_validity`  INT(11)       DEFAULT NULL,
    `additional_information`  VARCHAR(4096) DEFAULT NULL,
    `autoapprove`             VARCHAR(256)  DEFAULT NULL,
    PRIMARY KEY (`client_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = `utf8`;

# INSERT INTO `oauth_client_details`
# VALUES ('client', 'resource1', '$2a$10$kKy.SFlyOQcf0xzQ7/sy/OFiHUxcQ9H8QL8dZxafP64tERSwXctuO', 'role_admin0,role_admin,role_vip', 'authorization_code', 'https://www.baidu.com', NULL, NULL, NULL, NULL, NULL);

CREATE TABLE IF NOT EXISTS `oauth_client_token`
(
    `token_id`          VARCHAR(256)   DEFAULT NULL,
    `token`             VARBINARY(512) DEFAULT NULL,
    `authentication_id` VARCHAR(128) NOT NULL,
    `user_name`         VARCHAR(256)   DEFAULT NULL,
    `client_id`         VARCHAR(256)   DEFAULT NULL,
    PRIMARY KEY (`authentication_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = `utf8`;

CREATE TABLE IF NOT EXISTS `oauth_access_token`
(
    `token_id`          VARCHAR(256) DEFAULT NULL,
    `token`             BLOB,
    `authentication_id` VARCHAR(128) NOT NULL,
    `user_name`         VARCHAR(256) DEFAULT NULL,
    `client_id`         VARCHAR(256) DEFAULT NULL,
    `authentication`    BLOB,
    `refresh_token`     VARCHAR(256) DEFAULT NULL,
    PRIMARY KEY (`authentication_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = `utf8`;

CREATE TABLE IF NOT EXISTS `oauth_refresh_token`
(
    `token_id`       VARCHAR(256) DEFAULT NULL,
    `token`          BLOB,
    `authentication` BLOB
) ENGINE = InnoDB
  DEFAULT CHARSET = `utf8`;

CREATE TABLE IF NOT EXISTS `oauth_code`
(
    `code`           VARCHAR(256) DEFAULT NULL,
    `authentication` BLOB
) ENGINE = InnoDB
  DEFAULT CHARSET = `utf8`;

CREATE TABLE IF NOT EXISTS `oauth_approvals`
(
    `userid`         VARCHAR(256)   DEFAULT NULL,
    `clientid`       VARCHAR(256)   DEFAULT NULL,
    `scope`          VARCHAR(256)   DEFAULT NULL,
    `status`         VARCHAR(10)    DEFAULT NULL,
    `expiresat`      TIMESTAMP NULL DEFAULT NULL,
    `lastmodifiedat` TIMESTAMP NULL DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = `utf8`;