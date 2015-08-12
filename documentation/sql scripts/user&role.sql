-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `roleId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `email` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `password` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `phoneNumber` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `userId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `userName` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `user_role_sw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_role_sw` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `roleId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `userId` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;