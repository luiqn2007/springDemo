CREATE DATABASE IF NOT EXISTS spring_bank_app_db /*!40100 DEFAULT CHARACTER SET latin1 */;
USE spring_bank_app_db;

drop table if exists customer_registration_details;
create table if not exists customer_registration_details
(
    ACCOUNT_NUMBER varchar(10) not null,
    ADDRESS        varchar(20) not null,
    CARD_NUMBER    varchar(10) not null
);
