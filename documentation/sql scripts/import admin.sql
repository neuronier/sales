-- felhasználónév: admin
-- jelszó: welcome1

INSERT INTO `user_role_sw` (`id`, `roleId`, `userId`) VALUES (4, 3, 8);
INSERT INTO `role` (`id`, `name`, `roleId`) VALUES (3, 'ROLE_ADMIN', NULL);
INSERT INTO `user` (`id`, `email`, `name`, `password`, `phoneNumber`, `userId`, `userName`) VALUES (8, 'admin@ier.hu', 'admin', '$2a$10$uTWmD2.90w0H71wLIMgtMeaYko5JmnsNSSjqFglabbSbPggl2xhJi', '', NULL, 'admin');