-- -----------------------------------------------------
-- Table `offerproducttype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerproducttype` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `productTypeId` BIGINT(20) NOT NULL COMMENT '',
  `quantity` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `offerorder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offerorder` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `orderId` BIGINT(20) NOT NULL COMMENT '',
  `quantity` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `offer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offer` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `offerId` BIGINT(20) NOT NULL COMMENT '',
  `offerPrice` BIGINT(20) NOT NULL COMMENT '',
  `offerId_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `FK4892A3C713E2CB4` (`offerId_id` ASC)  COMMENT '',
  INDEX `FK4892A3C95C997BE` (`id` ASC)  COMMENT '',
  INDEX `FK4892A3C161C6379` (`id` ASC)  COMMENT '',
  CONSTRAINT `FK4892A3C161C6379`
    FOREIGN KEY (`id`)
    REFERENCES `offerproducttype` (`id`),
  CONSTRAINT `FK4892A3C713E2CB4`
    FOREIGN KEY (`offerId_id`)
    REFERENCES `offer` (`id`),
  CONSTRAINT `FK4892A3C95C997BE`
    FOREIGN KEY (`id`)
    REFERENCES `offerorder` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;