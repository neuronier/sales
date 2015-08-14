-- -----------------------------------------------------
-- Table `clientorder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clientorder` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `clientId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `orderId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;