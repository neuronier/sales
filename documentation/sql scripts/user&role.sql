-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `roleId` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 3
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
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `user_role_sw`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_role_sw` (
  `User_id` BIGINT(20) NOT NULL COMMENT '',
  `roles_id` BIGINT(20) NOT NULL COMMENT '',
  INDEX `FKAD012E599634FD21` (`roles_id` ASC)  COMMENT '',
  INDEX `FKAD012E599D4748A8` (`User_id` ASC)  COMMENT '',
  CONSTRAINT `FKAD012E599D4748A8`
    FOREIGN KEY (`User_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `FKAD012E599634FD21`
    FOREIGN KEY (`roles_id`)
    REFERENCES `role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;