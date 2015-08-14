-- -----------------------------------------------------
-- Table `salespoint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `salespoint` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `salePointAdress` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `salePointId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `salePointPhoneNumber` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `wareHouseId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;