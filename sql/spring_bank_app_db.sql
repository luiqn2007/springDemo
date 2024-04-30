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