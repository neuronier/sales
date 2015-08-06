-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `address` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `addresId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `city` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `houseNumber` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `sreet` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `zipCode` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;

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
  `userName` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `client_address_billing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `client_address_billing` (
  `Client_id` BIGINT(20) NOT NULL COMMENT '',
  `billingAddress_id` BIGINT(20) NOT NULL COMMENT '',
  INDEX `FKAFAB84BCEE037867` (`billingAddress_id` ASC)  COMMENT '',
  INDEX `FKAFAB84BCC3C9CD28` (`Client_id` ASC)  COMMENT '',
  CONSTRAINT `FKAFAB84BCC3C9CD28`
    FOREIGN KEY (`Client_id`)
    REFERENCES `client` (`id`),
  CONSTRAINT `FKAFAB84BCEE037867`
    FOREIGN KEY (`billingAddress_id`)
    REFERENCES `address` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `client_address_delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `client_address_delivery` (
  `Client_id` BIGINT(20) NOT NULL COMMENT '',
  `deliveryAddress_id` BIGINT(20) NOT NULL COMMENT '',
  INDEX `FK41CA01D3EF99AEA0` (`deliveryAddress_id` ASC)  COMMENT '',
  INDEX `FK41CA01D3C3C9CD28` (`Client_id` ASC)  COMMENT '',
  CONSTRAINT `FK41CA01D3C3C9CD28`
    FOREIGN KEY (`Client_id`)
    REFERENCES `client` (`id`),
  CONSTRAINT `FK41CA01D3EF99AEA0`
    FOREIGN KEY (`deliveryAddress_id`)
    REFERENCES `address` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `salespoint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sales`.`salespoint` (
  `Id` BIGINT(20) NOT NULL COMMENT '',
  `salePointName` VARCHAR(255) NULL COMMENT '',
  `salePointAdress` VARCHAR(255) NULL COMMENT '',
  `salePointPhoneNumber` VARCHAR(255) NULL COMMENT '',
  `salePointId` BIGINT(20) NULL COMMENT '',
  `wareHouseId` BIGINT(20) NULL COMMENT '',
  PRIMARY KEY (`Id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



