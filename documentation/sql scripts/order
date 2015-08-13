-- -----------------------------------------------------
-- Table `orderproducttype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orderproducttype` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `orderId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `productTypeId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `quantity` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `orderId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `orderuser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orderuser` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `complete` INT(11) NOT NULL COMMENT '',
  `orderId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `userId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;