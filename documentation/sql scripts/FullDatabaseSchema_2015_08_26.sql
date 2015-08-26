-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
-- a sales helyett írd be az adatbázisod nevét!!!
-- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 
ALTER DATABASE sales DEFAULT CHARACTER SET utf8;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `addressId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `city` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `houseNumber` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `street` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `zipCode` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `client` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `clientId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `emailAddress` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `password` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `phoneNumber` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `registrationDate` DATETIME NULL DEFAULT NULL COMMENT '',
  `userName` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `clientoffer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clientoffer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `clientId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `clientOfferId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `date` DATETIME NULL DEFAULT NULL COMMENT '',
  `offerId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `count`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `count` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `count` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `dayOfMonth` INT(11) NULL DEFAULT NULL COMMENT '',
  `type` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `issuemessage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuemessage` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `date` DATETIME NULL DEFAULT NULL COMMENT '',
  `messageId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `owner` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `text` LONGTEXT NULL DEFAULT NULL COMMENT '',
  `threadId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `issuethread`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuethread` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `clientId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `lastUpdate` DATETIME NULL DEFAULT NULL COMMENT '',
  `status` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `subject` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `threadId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `offerId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `offerPrice` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `offerorder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerorder` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `offerId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `orderId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `quantity` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `offerproducttype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerproducttype` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `offerId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `productTypeId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `quantity` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orderproducttype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orderproducttype` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `orderId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `productTypeId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `quantity` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `date` DATETIME NULL DEFAULT NULL COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `orderId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `status` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `warehouseId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `producttype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `producttype` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `productTypeId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `roleId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `salespoint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salespoint` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `addressId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `salePointId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `salePointPhoneNumber` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `wareHouseId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `email` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `password` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `phoneNumber` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `registrationDate` DATETIME NULL DEFAULT NULL COMMENT '',
  `salePointId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `userId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `userName` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user_role_sw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_role_sw` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `roleId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `userId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `warehouseId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `warehouseName` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
