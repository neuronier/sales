-- felhasználónév: admin
-- jelszó: welcome1

INSERT INTO `user_role_sw` (`roleId`, `userId`) VALUES ('5fecd179-0310-4e9f-b910-e1a8b7987e3f', '29d192b2-0d90-4515-b771-8d75f8492ac2');
INSERT INTO `role` (`name`, `roleId`) VALUES ('ROLE_ADMIN', '5fecd179-0310-4e9f-b910-e1a8b7987e3f');
INSERT INTO `user` (`email`, `name`, `password`, `phoneNumber`, `userId`, `userName`) VALUES ('admin@ier-sales.hu', 'admin', '$2a$10$eSsBU3zZmDlU85WKPHf.DutzzefiUXyOIbzeD2ZiNy8A2DQVZ9YIO', '+36303636363', '29d192b2-0d90-4515-b771-8d75f8492ac2', 'admin');