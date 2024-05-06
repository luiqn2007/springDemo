# Dumping database structure for spring_bank_app_db
DROP DATABASE IF EXISTS `spring_bank_app_db`;
CREATE DATABASE IF NOT EXISTS `spring_bank_app_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `spring_bank_app_db`;


# Dumping structure for table spring_bank_app_db.bank_account_details
DROP TABLE IF EXISTS `bank_account_details`;
CREATE TABLE IF NOT EXISTS `bank_account_details`
(
    `ACCOUNT_ID`          int(10)  NOT NULL AUTO_INCREMENT,
    `BALANCE_AMOUNT`      float    NOT NULL,
    `LAST_TRANSACTION_TS` datetime NOT NULL,
    PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1 COMMENT ='Contains information about bank accounts of customers';


# Dumping structure for table spring_bank_app_db.fixed_deposit_details
DROP TABLE IF EXISTS `fixed_deposit_details`;
CREATE TABLE IF NOT EXISTS `fixed_deposit_details`
(
    `FIXED_DEPOSIT_ID` int(10)     NOT NULL AUTO_INCREMENT,
    `ACCOUNT_ID`       int(10)     NOT NULL,
    `FD_CREATION_DATE` date        NOT NULL DEFAULT (CURDATE()),
    `FD_MATURITY_DATE` date        NOT NULL,
    `AMOUNT`           long        NOT NULL,
    `TENURE`           int(10)     NOT NULL,
    `ACTIVE`           char(1)     NOT NULL DEFAULT 'N',
    `EMAIL`            varchar(50) NOT NULL,
    PRIMARY KEY (`FIXED_DEPOSIT_ID`),
    KEY `FK_fixed_deposit_details_bank_account_details` (`ACCOUNT_ID`),
    CONSTRAINT `FK_fixed_deposit_details_bank_account_details` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `bank_account_details` (`ACCOUNT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;


DROP TABLE IF EXISTS customer_registration_details;
CREATE TABLE IF NOT EXISTS customer_registration_details
(
    ACCOUNT_NUMBER varchar(10) not null,
    ADDRESS        varchar(20) not null,
    CARD_NUMBER    varchar(10) not null
);


INSERT INTO bank_account_details(BALANCE_AMOUNT, LAST_TRANSACTION_TS) VALUE (99999, now());
INSERT INTO fixed_deposit_details(ACCOUNT_ID, FD_MATURITY_DATE, AMOUNT, TENURE, ACTIVE, EMAIL)
    VALUE (last_insert_id(), date_add(now(), interval 12 month), 10000, 12, 'Y', 'test_account@email.com');

CREATE DATABASE IF NOT EXISTS `securitydb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `securitydb`;

CREATE TABLE acl_sid
(
    id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    principal BOOLEAN         NOT NULL,
    sid       VARCHAR(100)    NOT NULL,
    UNIQUE KEY unique_acl_sid (sid, principal)
) ENGINE = InnoDB;

CREATE TABLE acl_class
(
    id    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class VARCHAR(100)    NOT NULL,
    UNIQUE KEY uk_acl_class (class)
) ENGINE = InnoDB;

CREATE TABLE acl_object_identity
(
    id                 BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    object_id_class    BIGINT UNSIGNED NOT NULL,
    object_id_identity BIGINT          NOT NULL,
    parent_object      BIGINT UNSIGNED,
    owner_sid          BIGINT UNSIGNED,
    entries_inheriting BOOLEAN         NOT NULL,
    UNIQUE KEY uk_acl_object_identity (object_id_class, object_id_identity),
    CONSTRAINT fk_acl_object_identity_parent FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_object_identity_class FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
    CONSTRAINT fk_acl_object_identity_owner FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
) ENGINE = InnoDB;

CREATE TABLE acl_entry
(
    id                  BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acl_object_identity BIGINT UNSIGNED  NOT NULL,
    ace_order           INTEGER          NOT NULL,
    sid                 BIGINT UNSIGNED  NOT NULL,
    mask                INTEGER UNSIGNED NOT NULL,
    granting            BOOLEAN          NOT NULL,
    audit_success       BOOLEAN          NOT NULL,
    audit_failure       BOOLEAN          NOT NULL,
    UNIQUE KEY unique_acl_entry (acl_object_identity, ace_order),
    CONSTRAINT fk_acl_entry_object FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_entry_acl FOREIGN KEY (sid) REFERENCES acl_sid (id)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `fixed_deposit_details`;
CREATE TABLE IF NOT EXISTS `fixed_deposit_details`
(
    `id`     bigint(20)  NOT NULL AUTO_INCREMENT,
    `user`   varchar(50) NOT NULL,
    `amount` varchar(50) NOT NULL,
    `tenure` varchar(50) NOT NULL,
    `email`  varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1 COMMENT ='Contains fixed deposit details';

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users`
(
    `username` varchar(50)  NOT NULL,
    `password` varchar(200) NOT NULL,
    `enabled`  tinyint(1)   NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE IF NOT EXISTS `authorities`
(
    `username`  varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;


INSERT INTO `acl_class` (`id`, `class`)
VALUES (1, 'com.example.mybank.domain.FixedDepositDetails'),
       (2, 'com.example.mybank_xml.domain.FixedDepositDetails');

INSERT INTO `acl_sid` (`id`, `principal`, `sid`)
VALUES (3, 1, 'admin'),
       (1, 1, 'cust1'),
       (2, 1, 'cust2');

INSERT INTO `users` (`username`, `password`, `enabled`)
VALUES ('admin', '$2a$10$tedljrxGtyQ/IjpfhTYblOSgBhtCYUMFVoWBdhzCtsGWxwxR2aCZu', 1),
       ('cust1', '$2a$10$GAyoJ5qh2tejzVzW8jYPQetLLbvHyZwM5WprAzk4Uthh.LqhSIW9G', 1),
       ('cust2', '$2a$10$CX7U5Q4gShRnPMVPq6n43O16D9yYC3fRc6BeuRW5mNqGj6aN596ai', 1);

INSERT INTO `authorities` (`username`, `authority`)
VALUES ('admin', 'ROLE_ADMIN'),
       ('cust1', 'ROLE_CUSTOMER'),
       ('cust2', 'ROLE_CUSTOMER');