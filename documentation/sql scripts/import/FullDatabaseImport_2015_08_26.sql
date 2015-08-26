SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

INSERT INTO `address` (`id`, `addressId`, `city`, `houseNumber`, `street`, `zipCode`) VALUES
(7, '8fcc3b03-808d-4ddc-bc06-80470578f547', 'Székesfehérvár', '122', 'Bajcsy', '5676'),
(8, 'de058c58-2cf5-495e-81b5-eddb47d97011', 'Kecskemét', '34', 'Munkácsy', '2311'),
(9, 'b7d133bf-78a7-4c73-a62d-9389cfb427c5', 'Debrecen', '101', 'Rákosnégyesi utca', '6231'),
(10, 'b24d6bdc-e6f9-44e2-bfca-e4cddb00afe7', 'Miskolc', '9', 'Vaswáry', '4554'),
(11, '99911827-29b6-4ea7-97df-5f49d7215e60', 'Budapest', '45', 'Erzsébet tér', '2332'),
(12, 'b7135b61-c4b1-4350-a487-4f5774dd8880', 'Székesfehérvár', '67', 'Ládmányosi sugárút', '1111'),
(13, 'bcea1800-b547-4def-8d12-389b89bdaeac', 'Nyíregyháza', '4', 'Zuglói körút', '7898'),
(14, '9d84b59b-c307-4cf8-909d-77d45ebf04ba', 'Nyíregyháza', '32', 'Rakpart utca', '1244'),
(15, '0f413768-4673-49cd-9e9d-3b58bd5965d5', 'Budapest', '01', 'Party street', '4444'),
(16, 'b668c725-2c49-46e5-9006-f8ebafe8b6e6', 'Debrecen', '77', 'Piac utca', '4029'),
(17, '11f30922-be3c-4550-9843-719e9ffc962c', 'Kecskemét', '1', 'Rézangyal utca', '6544');

INSERT INTO `client` (`id`, `clientId`, `emailAddress`, `name`, `password`, `phoneNumber`, `registrationDate`, `userName`) VALUES
(2, '7fdea050-8748-4119-b90b-d85354a035d6', 'jkovacs@ier-sales.hu', 'Kovács János', '$2a$04$Kd7MLBSmJUKVP24.4isZ0OLTOYGx1AwHbkfrQugPp5ReV71NDOAzG', '+36101231234', '2015-08-01 09:19:27', 'jkovacs'),
(3, '296b4419-bc16-4bf4-969e-87b8fd0e299d', 'knagy@ier-sales.hu', 'Nagy Kincső', '$2a$04$kdgQ6BQ.DTymyz615iR0veQpXpr0oiTIu8bUxNRNcRpHotLy8X9vu', '+36123456789', '2015-08-03 13:24:29', 'knagy'),
(4, '880f3614-edc4-462b-a49b-a202d2b10421', 'bhorvath@ier-sales.hu', 'Horváth Bendegúz', '$2a$04$H0wCJWW54/N90t7F4L6H8eNASN5UXx6X0NjlWLWTX8IokHWN8.lFu', '+36123456798', '2015-06-10 11:36:24', 'bhorvath'),
(6, '13cd4b67-9302-4bfa-b494-0060293b4fea', 'akiss@ier-sales.hu', 'Kiss Annamária', '$2a$04$dn02/FQQsgn97LTOIUu/G.wCaKR9ZT08gsEomoeD9IC5Ol1l.AMne', '+36213456798', '2015-08-13 17:35:28', 'akiss'),
(7, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', 'rfazekas@ier-sales.hu', 'Fazekas Róbert', '$2a$04$8aNLQQp3wnqyf/K9L9pYo.FIPGX8CGeXo/ryhSP7wO.DiY6as1DGa', '+36219876798', '2015-07-22 10:29:31', 'rfazekas'),
(8, 'f8c4c892-a954-4473-8cdc-310a62db86a3', 'cnathan@ier-sales.hu', 'Nathan Collins', '$2a$04$DYOEPf3YcDUt9mZM./AVyer6ldpJIfENu6SGei5UBlxXQhUJBygQi', '+36219876798', '2015-07-15 02:16:58', 'cnathan'),
(9, '5df51c91-b9ae-4213-b28b-7a38b5780486', 'gpatricia@ier-sales.hu', 'Patricia Garcia', '$2a$04$klcvLzukJgvgEOSzINGBpe4LUpYgI.g00K7tfyh3D3Hk0pygHaTVW', '+36219876798', '2015-08-05 20:02:25', 'gpatricia'),
(10, '552743d5-8d3e-4273-b36d-24e501c87eba', 'cdonnie@ier-sales.hu', 'Donnie Chavez', '$2a$04$CaTNb8nYdAInaKkw3rNQ/OPNPBhcQQb8.jURZ1yRH0nFRMCpxRP4C', '+36219876798', '2015-08-12 17:38:20', 'cdonnie'),
(11, '18b8625c-a911-4359-8cbd-4ff637e3e129', 'edebra@ier-sales.hu', 'Debra Evans', '$2a$04$vUVRXTDKmRO9IpZSBeAt3uVQ8W45xe57NZuX.ySCXu6x8nsR6puR.', '+36921846798', '2015-07-02 13:50:44', 'edebra');

INSERT INTO `clientoffer` (`id`, `clientId`, `clientOfferId`, `date`, `offerId`) VALUES
(1, '5df51c91-b9ae-4213-b28b-7a38b5780486', 'B3F11829-9204-A81D-27FB-91C2AB6663B6', '2015-08-04 00:12:30', '47EB6DDA-1977-2115-E0DB-D0E0F124E60A'),
(2, '5df51c91-b9ae-4213-b28b-7a38b5780486', 'AD96AA43-1AEE-96CF-4F41-F4A439F623D0', '2016-07-17 21:39:11', '8979D5B3-F797-E4F0-9397-2F83795A3526'),
(3, '552743d5-8d3e-4273-b36d-24e501c87eba', 'B884149F-2751-B2D1-D4B7-4B38DCEF5540', '2015-06-19 01:47:15', 'D8BF469F-4338-D334-F3D7-667EFD78B8AA'),
(4, '296b4419-bc16-4bf4-969e-87b8fd0e299d', 'A082A3A5-81C7-6CB8-3775-98BA7F787283', '2015-11-21 23:14:35', '28F00A46-57B1-725D-F0B4-0C748EBBE9B6'),
(5, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '15C20533-B069-397A-20D6-AF54A4867272', '2016-08-18 00:29:17', 'D7FF6950-02EC-26CF-4CB6-9C8ADF522761'),
(6, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '3D5D296C-6C56-15CF-8BC1-733B3005BE2A', '2014-10-25 02:48:07', '2192BE2A-65C2-7E0F-5AA7-33161022F423'),
(7, '13cd4b67-9302-4bfa-b494-0060293b4fea', '817D0DC1-86DB-26B3-5D53-B4F2E4F27318', '2015-08-16 06:18:19', 'E6063894-6E8E-6434-A8C0-3C4285FCD1EF'),
(8, '552743d5-8d3e-4273-b36d-24e501c87eba', 'C0A7CC99-756E-A04A-BBC3-6A61FC56DEB0', '2015-11-19 15:47:31', '661CFEFD-12CE-1CC6-D90E-CBA99D8AD6BA'),
(9, '7fdea050-8748-4119-b90b-d85354a035d6', '5C6007E8-B6E7-80F9-BC74-668524CD7DCC', '2015-03-08 07:45:12', 'E948140B-C755-A40C-0F2F-D5122547C53E'),
(10, '7fdea050-8748-4119-b90b-d85354a035d6', 'EBB97B12-879D-B8C7-FF5A-38322807DA7F', '2015-09-22 18:31:23', '1ADD2DA3-7011-1C61-8BC8-E589460E996A'),
(11, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '69A58482-C90A-9233-2F16-8986849696BE', '2015-08-21 16:10:14', '1515A729-1F04-A786-8DB5-1F298AA966E0'),
(12, '7fdea050-8748-4119-b90b-d85354a035d6', '10D459A7-661C-AA7E-7064-DD4B251DF494', '2014-10-03 08:16:26', '482967B1-C897-9D6E-DC3A-8D3BD737FC07'),
(13, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '28FF873C-41AC-0614-47F6-37FA2E68A0A5', '2015-10-18 09:53:54', '40B73C9E-6C86-BB56-1EF5-11EF1254E82C'),
(14, '880f3614-edc4-462b-a49b-a202d2b10421', '02965B14-5BBD-DCBF-88D6-7718A506E0EA', '2015-09-08 13:18:27', '661CFEFD-12CE-1CC6-D90E-CBA99D8AD6BA'),
(15, '7fdea050-8748-4119-b90b-d85354a035d6', '4AD7C5A9-52AF-BD65-FA58-212D82C28E1D', '2015-11-07 20:14:55', '8C2ED95F-32AA-C93A-8203-B203C38FA082'),
(16, 'f8c4c892-a954-4473-8cdc-310a62db86a3', 'B6EE5372-B5E4-E411-7C21-C0084FC54DBB', '2015-01-03 08:43:41', '8489F2B4-DE39-0C58-A2A0-EFACE3DB4821'),
(17, '13cd4b67-9302-4bfa-b494-0060293b4fea', '966B0871-10BA-94B4-10BF-89AC0C9791C8', '2016-02-17 20:02:48', '8DCE9268-EF70-88D3-EA83-84F3FFFBB1CE'),
(18, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '6413A327-523A-7F64-0F1A-D53F26C4561A', '2014-09-14 10:12:05', '584BE76F-0ABC-752A-EFCE-1970CE12BAF0'),
(19, '5df51c91-b9ae-4213-b28b-7a38b5780486', 'D0776818-8CA2-0B52-EDFF-C635B5BC3B36', '2014-11-17 23:05:40', '957493AA-9C6B-7E28-E9D0-A22ABE5EE326'),
(20, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '3C0B6B79-5CC1-70FA-65CD-6A88B1FAF88E', '2014-11-26 22:39:49', '56646CF2-AC86-82A0-C98F-E5DDA876387A'),
(21, '880f3614-edc4-462b-a49b-a202d2b10421', 'B8BFA81C-C206-338B-19BE-FDA7770DF11A', '2015-01-31 11:23:41', '79D0C22E-C781-F1DC-B885-F96043ADFD8A'),
(22, '880f3614-edc4-462b-a49b-a202d2b10421', '46F75ACE-99F4-4202-4197-1DDCE82BD74D', '2015-09-01 13:38:42', '994E4D75-451B-5DD8-43AB-7DFA3E0C21B5'),
(23, '880f3614-edc4-462b-a49b-a202d2b10421', 'CA1A7CF7-A45C-21E8-9137-C4BCA203FDF2', '2015-05-27 03:00:43', 'E0EF6707-081A-388C-C956-64092C8F501C'),
(24, '5df51c91-b9ae-4213-b28b-7a38b5780486', '9AE9F980-65A8-5640-CFB5-8B044E0F14A9', '2016-08-17 10:58:02', '482967B1-C897-9D6E-DC3A-8D3BD737FC07'),
(25, '18b8625c-a911-4359-8cbd-4ff637e3e129', 'CED9AA2D-5F6F-8FBD-579C-9E55E1C2E317', '2015-05-27 05:08:06', 'A2F61B8A-5B1E-1968-BEDB-BBBD2ECF20A7'),
(26, '13cd4b67-9302-4bfa-b494-0060293b4fea', '94102E41-E18A-1E7D-3939-CDC29A931EE9', '2016-06-28 13:33:29', 'D43138F3-3AA0-774B-7FFE-A748951C2609'),
(27, '5df51c91-b9ae-4213-b28b-7a38b5780486', '62E6864A-CD90-20FE-A318-9999C583B3FF', '2014-11-11 08:39:32', '1FD7F17D-4890-42F7-DD47-F8667FFE5DCF'),
(28, 'f8c4c892-a954-4473-8cdc-310a62db86a3', '5FECB1AD-47E7-007A-5917-AA0BF04C7BCF', '2016-03-13 10:25:35', '69C224B2-22FE-B2AD-2E54-682ACE384887'),
(29, '552743d5-8d3e-4273-b36d-24e501c87eba', '3D8320DC-5EC1-01E7-4393-A76D478AD0B2', '2014-11-15 08:42:17', '69C224B2-22FE-B2AD-2E54-682ACE384887'),
(30, '18b8625c-a911-4359-8cbd-4ff637e3e129', '831C6201-DF28-1196-D0A4-E5E4CC1DBF38', '2015-10-19 20:05:02', '856F6F6D-8E2F-53C0-E7AE-4B1AF905131C'),
(31, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '65FA6C3B-4654-D378-5646-AD2D9068FA89', '2015-11-14 01:06:40', '8489F2B4-DE39-0C58-A2A0-EFACE3DB4821'),
(32, '296b4419-bc16-4bf4-969e-87b8fd0e299d', 'FAD56B51-3E7D-39F5-D350-FAD24A278E49', '2016-08-06 21:15:42', '8979D5B3-F797-E4F0-9397-2F83795A3526'),
(33, '18b8625c-a911-4359-8cbd-4ff637e3e129', 'A9D11182-1731-22C8-9781-08060AFC13D1', '2015-01-17 17:30:40', '1ADD2DA3-7011-1C61-8BC8-E589460E996A'),
(34, '552743d5-8d3e-4273-b36d-24e501c87eba', 'A8A4ED4B-6E76-AC09-2897-D477865D0864', '2014-09-28 06:21:57', '2192BE2A-65C2-7E0F-5AA7-33161022F423'),
(35, '552743d5-8d3e-4273-b36d-24e501c87eba', 'A3CA61EC-8437-DDA0-B936-8423B4EF28D5', '2016-02-08 13:45:10', '1CAACE94-BA4D-10EE-0DEE-662C454126F9'),
(36, '18b8625c-a911-4359-8cbd-4ff637e3e129', 'F4D8D7B6-F128-5CE7-B3F2-245DBC108719', '2016-03-26 08:54:42', 'F2C0C180-DAE9-D531-15C9-66DD2D5C96F4'),
(37, '7fdea050-8748-4119-b90b-d85354a035d6', '51F7A0E7-B29B-121E-A9B3-06B26FC14133', '2015-02-03 20:37:05', 'D43138F3-3AA0-774B-7FFE-A748951C2609'),
(38, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '703B700A-28A3-09B9-AAE6-DF88EEAE5012', '2015-03-09 00:37:37', '40B73C9E-6C86-BB56-1EF5-11EF1254E82C'),
(39, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '11D19445-6308-ECE4-6C53-F61299BE809B', '2015-05-21 12:53:42', '1D815E8A-B25C-6CD9-7B99-D14EE072178D'),
(40, '880f3614-edc4-462b-a49b-a202d2b10421', '2C33A037-F407-83D9-7E1C-EE3EDBD620E3', '2016-01-28 15:59:43', 'E948140B-C755-A40C-0F2F-D5122547C53E'),
(41, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '3050CCF7-F86F-D782-568F-10A6D97408E7', '2015-10-28 05:42:52', '1FD7F17D-4890-42F7-DD47-F8667FFE5DCF'),
(42, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '2BD3C261-871C-EB07-AA48-B20013B427A7', '2016-08-13 15:47:58', '47EB6DDA-1977-2115-E0DB-D0E0F124E60A'),
(43, '552743d5-8d3e-4273-b36d-24e501c87eba', 'D8E32B02-EF79-7D5F-F50E-C582B8E3E3DD', '2014-10-03 02:59:48', '856F6F6D-8E2F-53C0-E7AE-4B1AF905131C'),
(44, '5df51c91-b9ae-4213-b28b-7a38b5780486', '50FFCFF6-BF9D-C242-540E-013C720219D6', '2015-05-03 16:51:49', '1FD7F17D-4890-42F7-DD47-F8667FFE5DCF'),
(45, '13cd4b67-9302-4bfa-b494-0060293b4fea', 'A434F782-0BBF-3640-A571-5116FCEA95D5', '2015-10-03 00:37:20', '79D0C22E-C781-F1DC-B885-F96043ADFD8A'),
(46, '18b8625c-a911-4359-8cbd-4ff637e3e129', '79EE1672-1FBB-C337-8471-4038B1C57EBC', '2015-06-02 04:17:41', 'E0EF6707-081A-388C-C956-64092C8F501C'),
(47, '880f3614-edc4-462b-a49b-a202d2b10421', '8806CD8B-19F3-D06A-1FCC-E80BBDA1093C', '2014-11-12 05:56:12', '1D815E8A-B25C-6CD9-7B99-D14EE072178D'),
(48, '7fdea050-8748-4119-b90b-d85354a035d6', 'F725CCC6-3399-675D-87CA-534C9FBF8C29', '2014-10-23 01:11:09', '4AEF8A1F-3932-3062-8E95-616F4AEAD34D'),
(49, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '4F2B4BC3-5760-2F67-E977-E5BBD86A1109', '2015-01-01 04:02:18', '80D94822-CAD5-DA87-1B5B-34827AE43952'),
(50, '18b8625c-a911-4359-8cbd-4ff637e3e129', '6F154F69-A99F-2D37-8A94-FBD836897280', '2016-01-26 07:48:25', '3FBEAC54-CBB2-6830-0F83-74CC906363D6'),
(51, '7fdea050-8748-4119-b90b-d85354a035d6', '2A471D66-7B83-4040-8F5F-5AE517E93823', '2016-06-25 10:42:34', '1D815E8A-B25C-6CD9-7B99-D14EE072178D'),
(52, '18b8625c-a911-4359-8cbd-4ff637e3e129', 'D4A5E495-3986-4C38-2A1F-05A950DDABE2', '2015-07-31 03:06:34', '79EB72AA-37AC-64D3-2ED0-BBBE38A9E9D1'),
(53, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '807B82C7-0382-2C69-7CE2-7E2DD0EDE246', '2016-01-17 04:52:52', '994E4D75-451B-5DD8-43AB-7DFA3E0C21B5'),
(54, '13cd4b67-9302-4bfa-b494-0060293b4fea', '9031A33E-CAD9-BE6E-86FF-1D500848A352', '2015-06-07 20:27:50', '8C2ED95F-32AA-C93A-8203-B203C38FA082'),
(55, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '724E3187-AA53-53FF-33B3-9CACCD59CE65', '2016-02-23 13:27:47', '80D94822-CAD5-DA87-1B5B-34827AE43952'),
(56, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '3A6C89E7-7CAB-2B02-D6A5-30EFADE7D222', '2015-09-14 18:56:47', 'E0EF6707-081A-388C-C956-64092C8F501C'),
(57, 'f8c4c892-a954-4473-8cdc-310a62db86a3', '528054E3-0554-417E-9CDA-F30BA1DE5493', '2014-10-08 17:23:29', 'E0EF6707-081A-388C-C956-64092C8F501C'),
(58, '13cd4b67-9302-4bfa-b494-0060293b4fea', 'B02AD9BB-1DD0-82A3-AA48-FD3B2C734086', '2016-03-19 07:16:46', '9DC419C7-E641-D7FF-BD76-3B3AE44F27C6'),
(59, '7fdea050-8748-4119-b90b-d85354a035d6', '00961811-F7D8-BCAA-7370-C6546F7E84A7', '2016-01-03 03:12:16', '856F6F6D-8E2F-53C0-E7AE-4B1AF905131C'),
(60, 'f8c4c892-a954-4473-8cdc-310a62db86a3', 'EEF767F3-0C09-FE04-5AD0-DD805E7EA2D5', '2016-05-20 00:25:36', '8C2ED95F-32AA-C93A-8203-B203C38FA082'),
(61, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '30945E15-F679-418D-D813-376FEAC09E40', '2015-09-07 03:55:37', 'D43138F3-3AA0-774B-7FFE-A748951C2609'),
(62, '18b8625c-a911-4359-8cbd-4ff637e3e129', '01DDA582-50D7-E615-A2E1-686D41755E1D', '2015-03-27 07:29:29', '856F6F6D-8E2F-53C0-E7AE-4B1AF905131C'),
(63, '552743d5-8d3e-4273-b36d-24e501c87eba', '56778A49-E95A-EEA0-B459-91F76B89DBCD', '2015-09-14 06:19:47', 'F2C0C180-DAE9-D531-15C9-66DD2D5C96F4'),
(64, '13cd4b67-9302-4bfa-b494-0060293b4fea', '1670E287-E4F2-255B-31F5-64C7FA6517D8', '2015-03-03 21:34:34', 'CA04D74A-DF14-EC3F-B4EA-9A499163C82B'),
(65, '552743d5-8d3e-4273-b36d-24e501c87eba', '261ACCB2-EC92-39DD-E09E-8130657BCFC5', '2014-08-26 13:58:20', '8C2ED95F-32AA-C93A-8203-B203C38FA082'),
(66, '552743d5-8d3e-4273-b36d-24e501c87eba', 'D4BC8CC3-7D75-EB64-6BD5-00FDEFFAD241', '2015-09-06 17:01:19', '622B8A64-D660-BB52-ABD8-38F162E80761'),
(67, '18b8625c-a911-4359-8cbd-4ff637e3e129', '0E6CBF31-7944-945E-0FFD-9AD52C93D67F', '2015-06-10 04:08:53', '79EB72AA-37AC-64D3-2ED0-BBBE38A9E9D1'),
(68, '5df51c91-b9ae-4213-b28b-7a38b5780486', '43020493-EB1F-1095-1AAA-6AA020D63C58', '2015-05-27 00:50:18', '79D0C22E-C781-F1DC-B885-F96043ADFD8A'),
(69, '552743d5-8d3e-4273-b36d-24e501c87eba', '55B414E2-4078-2E3D-C5F6-0FAB768881BB', '2014-10-09 16:41:54', '8979D5B3-F797-E4F0-9397-2F83795A3526'),
(70, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '2CCA89C4-4389-94E3-B2D8-7ABB0BF2338A', '2015-05-27 16:36:37', '1ADD2DA3-7011-1C61-8BC8-E589460E996A'),
(71, '18b8625c-a911-4359-8cbd-4ff637e3e129', 'D605BC52-0EA5-4B1D-8990-C05A350A6A2B', '2016-06-25 05:27:06', '40B73C9E-6C86-BB56-1EF5-11EF1254E82C'),
(72, '5df51c91-b9ae-4213-b28b-7a38b5780486', 'CB8E8A34-571C-86C6-ABED-64951C1CC090', '2016-01-21 10:34:46', '3D8DBC76-48DA-AB5B-6387-72E1875923DC'),
(73, '880f3614-edc4-462b-a49b-a202d2b10421', '54D72E36-F848-44E4-4BE4-1EEB5F4102D8', '2015-06-22 05:07:56', 'A924AB10-D3BC-928D-9F7C-B47B68375E12'),
(74, '18b8625c-a911-4359-8cbd-4ff637e3e129', '6BA6CCE0-86F9-5342-0AF6-DE9F98C398E3', '2015-01-07 14:52:24', '8DCE9268-EF70-88D3-EA83-84F3FFFBB1CE'),
(75, '18b8625c-a911-4359-8cbd-4ff637e3e129', '48F92CAC-747D-88F6-A039-D966D236FDC5', '2015-12-10 21:00:20', '584BE76F-0ABC-752A-EFCE-1970CE12BAF0'),
(76, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '761C161E-FE83-D98F-15F5-6C3A62C759FE', '2015-06-10 14:05:26', 'B75FBBC3-D2E9-8F88-666C-B71DDAC86529'),
(77, 'f8c4c892-a954-4473-8cdc-310a62db86a3', 'A28B839C-119E-6EA9-CAB7-0F764394532C', '2015-04-10 16:07:34', '56BFAF72-6F20-0D63-7023-2FF6E16C7593'),
(78, 'f8c4c892-a954-4473-8cdc-310a62db86a3', '66891EB4-C369-5879-6460-6E0AEC9D1042', '2014-12-01 22:14:29', '287A6AAA-9482-4918-C1D1-B6C0E851F9E8'),
(79, '552743d5-8d3e-4273-b36d-24e501c87eba', '68408958-1FB5-FC77-F02A-E7F325A42F8F', '2015-06-01 04:52:20', 'D7FF6950-02EC-26CF-4CB6-9C8ADF522761'),
(80, '7fdea050-8748-4119-b90b-d85354a035d6', 'D03FD9B0-EAD5-7F02-1480-2E3967548198', '2016-02-04 13:16:15', '661CFEFD-12CE-1CC6-D90E-CBA99D8AD6BA'),
(81, '5df51c91-b9ae-4213-b28b-7a38b5780486', '40C6C0D3-4393-D23E-6227-87D081CC9553', '2015-08-18 06:10:24', '341AFC3B-68CC-558D-1D9E-9FF485E86A8A'),
(82, '5df51c91-b9ae-4213-b28b-7a38b5780486', '3DCF10E1-1CA3-F6AD-E2B6-966AE7074B34', '2014-11-18 06:36:56', '28F00A46-57B1-725D-F0B4-0C748EBBE9B6'),
(83, '880f3614-edc4-462b-a49b-a202d2b10421', 'B1C8A51B-3B4D-EAD1-FA4A-7454C6C9C83A', '2016-05-20 02:53:49', '79EB72AA-37AC-64D3-2ED0-BBBE38A9E9D1'),
(84, '552743d5-8d3e-4273-b36d-24e501c87eba', 'F4DAF2E8-8CC1-F910-4428-AF19EDD03AD7', '2016-07-06 14:21:45', '622B8A64-D660-BB52-ABD8-38F162E80761'),
(85, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '8C2F35CD-EB08-C5B7-33F8-4B6E4F3A0FC2', '2014-10-03 22:23:56', '40B73C9E-6C86-BB56-1EF5-11EF1254E82C'),
(86, 'f8c4c892-a954-4473-8cdc-310a62db86a3', '85F17C98-72AF-1D6F-E63D-9C3C8B3222B0', '2016-07-25 00:01:59', '1FD7F17D-4890-42F7-DD47-F8667FFE5DCF'),
(87, 'f8c4c892-a954-4473-8cdc-310a62db86a3', '6F0F12AB-BA6F-6253-5036-DEA8B04179FA', '2015-03-30 14:48:08', '9DC419C7-E641-D7FF-BD76-3B3AE44F27C6'),
(88, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '5DF38B80-CB0D-E9E4-2B04-9BAB2C2F98E4', '2016-07-05 22:26:20', 'E948140B-C755-A40C-0F2F-D5122547C53E'),
(89, '18b8625c-a911-4359-8cbd-4ff637e3e129', 'B820FDAC-619D-B1BE-42A1-B8C35025BA9B', '2015-02-06 21:37:27', 'D8BF469F-4338-D334-F3D7-667EFD78B8AA'),
(90, '296b4419-bc16-4bf4-969e-87b8fd0e299d', 'EF5470FB-C87D-8181-C7B8-5FC6E15ABE6C', '2014-11-01 12:08:08', '1FD7F17D-4890-42F7-DD47-F8667FFE5DCF'),
(91, '296b4419-bc16-4bf4-969e-87b8fd0e299d', '6CB1205B-11E2-01BE-8B3B-FFEC61179B1D', '2014-12-24 03:08:10', '80D94822-CAD5-DA87-1B5B-34827AE43952'),
(92, '13cd4b67-9302-4bfa-b494-0060293b4fea', 'D4895B07-0B5E-ACF9-7B3E-61DE838A1C7B', '2015-11-11 11:49:47', 'A924AB10-D3BC-928D-9F7C-B47B68375E12'),
(93, '880f3614-edc4-462b-a49b-a202d2b10421', '605DD76C-F5BD-D583-CBEA-574191182F46', '2016-04-06 20:49:51', 'E221C2CB-B038-AC0B-0685-5D4038956455'),
(94, '13cd4b67-9302-4bfa-b494-0060293b4fea', '85E684DA-E1D1-D652-E9DC-73B100C7B50A', '2015-09-24 07:57:20', '856F6F6D-8E2F-53C0-E7AE-4B1AF905131C'),
(95, '880f3614-edc4-462b-a49b-a202d2b10421', '4A92680B-29AC-3B34-3779-B04CF0CE040D', '2015-07-27 05:18:16', '465C86C3-8D05-4EA6-8F2F-4C718CD667FF'),
(96, '18b8625c-a911-4359-8cbd-4ff637e3e129', '11C86D3E-73BE-A384-A775-003B8094568E', '2016-01-03 09:23:37', '341AFC3B-68CC-558D-1D9E-9FF485E86A8A'),
(97, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', 'FA8ED963-598D-72F9-F851-99474A269022', '2014-12-19 10:14:43', '584BE76F-0ABC-752A-EFCE-1970CE12BAF0'),
(98, '5df51c91-b9ae-4213-b28b-7a38b5780486', '818E2BA3-7B7E-530B-4DA5-92E36986AE62', '2016-02-20 13:14:37', '1515A729-1F04-A786-8DB5-1F298AA966E0'),
(99, '296b4419-bc16-4bf4-969e-87b8fd0e299d', 'BEC31C48-0764-3C99-FE05-C91B1DFE00C0', '2016-02-29 18:26:34', '3FBEAC54-CBB2-6830-0F83-74CC906363D6'),
(100, '18b8625c-a911-4359-8cbd-4ff637e3e129', '2BDA860D-3305-9292-A32D-42938C5A913F', '2015-06-29 19:49:50', 'A2F61B8A-5B1E-1968-BEDB-BBBD2ECF20A7');

INSERT INTO `issuemessage` (`id`, `date`, `messageId`, `owner`, `text`, `threadId`) VALUES
(4, '2015-08-24 13:19:14', '2e0ce21c-2963-428a-aaec-e045c903f73c', 'jkovacs', 'hibajegyteszt', '49685c57-7ea9-4566-bf27-b7451c2ed407'),
(5, '2015-08-24 13:19:34', '372069f2-ea73-497d-9b7e-117ec14a47a1', 'admin', 'működik, árvíztűrőtükörfűrógép ÁRVÍZTŰRŐTÜKÖRFÚRÓGÉP', '49685c57-7ea9-4566-bf27-b7451c2ed407');

INSERT INTO `issuethread` (`id`, `clientId`, `lastUpdate`, `status`, `subject`, `threadId`) VALUES
(2, '6cd7e00b-4053-4ad5-ae68-b7f0a4a9207c', '2015-08-24 13:19:34', 'Ongoing', 'test issue', '49685c57-7ea9-4566-bf27-b7451c2ed407');

INSERT INTO `offer` (`id`, `name`, `offerId`, `offerPrice`) VALUES
(1, 'offer1', 'D8BF469F-4338-D334-F3D7-667EFD78B8AA', 26345),
(2, 'offer2', '1FD7F17D-4890-42F7-DD47-F8667FFE5DCF', 50967),
(3, 'offer3', '4AEF8A1F-3932-3062-8E95-616F4AEAD34D', 24546),
(4, 'offer4', '8DCE9268-EF70-88D3-EA83-84F3FFFBB1CE', 66102),
(5, 'offer5', '79D0C22E-C781-F1DC-B885-F96043ADFD8A', 95799),
(6, 'offer6', '9DC419C7-E641-D7FF-BD76-3B3AE44F27C6', 58202),
(7, 'offer7', 'D7FF6950-02EC-26CF-4CB6-9C8ADF522761', 67793),
(8, 'offer8', '56BFAF72-6F20-0D63-7023-2FF6E16C7593', 87271),
(9, 'offer9', '8CC8845E-A0C0-F5EF-25EB-433D533FE367', 57678),
(10, 'offer10', '287A6AAA-9482-4918-C1D1-B6C0E851F9E8', 56562),
(11, 'offer11', 'E6063894-6E8E-6434-A8C0-3C4285FCD1EF', 38544),
(12, 'offer12', 'BBC723B4-9A33-EA41-FB04-A41475858075', 63568),
(13, 'offer13', '3FBEAC54-CBB2-6830-0F83-74CC906363D6', 96312),
(14, 'offer14', '1D815E8A-B25C-6CD9-7B99-D14EE072178D', 43591),
(15, 'offer15', 'F2C0C180-DAE9-D531-15C9-66DD2D5C96F4', 95586),
(16, 'offer16', '1CAACE94-BA4D-10EE-0DEE-662C454126F9', 38972),
(17, 'offer17', '79EB72AA-37AC-64D3-2ED0-BBBE38A9E9D1', 73652),
(18, 'offer18', 'A2F61B8A-5B1E-1968-BEDB-BBBD2ECF20A7', 57822),
(19, 'offer19', 'E0EF6707-081A-388C-C956-64092C8F501C', 86218),
(20, 'offer20', '482967B1-C897-9D6E-DC3A-8D3BD737FC07', 61010),
(21, 'offer21', '994E4D75-451B-5DD8-43AB-7DFA3E0C21B5', 57833),
(22, 'offer22', '80D94822-CAD5-DA87-1B5B-34827AE43952', 45223),
(23, 'offer23', '47EB6DDA-1977-2115-E0DB-D0E0F124E60A', 91121),
(24, 'offer24', '1ADD2DA3-7011-1C61-8BC8-E589460E996A', 48568),
(25, 'offer25', '2192BE2A-65C2-7E0F-5AA7-33161022F423', 70334),
(26, 'offer26', '3D8DBC76-48DA-AB5B-6387-72E1875923DC', 80675),
(27, 'offer27', 'D43138F3-3AA0-774B-7FFE-A748951C2609', 34053),
(28, 'offer28', '3EF24FE8-B01A-2F97-B1CE-A3D145DD54CB', 68906),
(29, 'offer29', '80464BEA-F394-E127-7CC3-19CFF64D8EA2', 49902),
(30, 'offer30', 'CA04D74A-DF14-EC3F-B4EA-9A499163C82B', 51228),
(31, 'offer31', '17F00D50-E28A-7286-0ABA-6F9A46B2C9E2', 31562),
(32, 'offer32', '341AFC3B-68CC-558D-1D9E-9FF485E86A8A', 40736),
(33, 'offer33', '8C2ED95F-32AA-C93A-8203-B203C38FA082', 67686),
(34, 'offer34', '40B73C9E-6C86-BB56-1EF5-11EF1254E82C', 85895),
(35, 'offer35', '8489F2B4-DE39-0C58-A2A0-EFACE3DB4821', 96317),
(36, 'offer36', '69C224B2-22FE-B2AD-2E54-682ACE384887', 64149),
(37, 'offer37', 'B59BA3F1-7FE6-72C3-59B9-466AF221866E', 94490),
(38, 'offer38', '661CFEFD-12CE-1CC6-D90E-CBA99D8AD6BA', 37902),
(39, 'offer39', '28F00A46-57B1-725D-F0B4-0C748EBBE9B6', 28235),
(40, 'offer40', '584BE76F-0ABC-752A-EFCE-1970CE12BAF0', 94549),
(41, 'offer41', '856F6F6D-8E2F-53C0-E7AE-4B1AF905131C', 20154),
(42, 'offer42', '8979D5B3-F797-E4F0-9397-2F83795A3526', 49880),
(43, 'offer43', 'B75FBBC3-D2E9-8F88-666C-B71DDAC86529', 91116),
(44, 'offer44', 'E221C2CB-B038-AC0B-0685-5D4038956455', 95735),
(45, 'offer45', 'A924AB10-D3BC-928D-9F7C-B47B68375E12', 50352),
(46, 'offer46', '622B8A64-D660-BB52-ABD8-38F162E80761', 22765),
(47, 'offer47', '5586721E-FE01-1BE3-085C-A9636439EF13', 55210),
(48, 'offer48', '957493AA-9C6B-7E28-E9D0-A22ABE5EE326', 65204),
(49, 'offer49', '465C86C3-8D05-4EA6-8F2F-4C718CD667FF', 60238),
(50, 'offer50', '56646CF2-AC86-82A0-C98F-E5DDA876387A', 42560),
(51, 'offer51', '884A4D42-B37D-9650-14BF-2355157AC2E8', 44169),
(52, 'offer52', '1515A729-1F04-A786-8DB5-1F298AA966E0', 52266),
(53, 'offer53', 'E948140B-C755-A40C-0F2F-D5122547C53E', 67566);

INSERT INTO `offerproducttype` (`id`, `offerId`, `productTypeId`, `quantity`) VALUES
(1, 'D8BF469F-4338-D334-F3D7-667EFD78B8AA', 'D777E941-8A52-BE3C-6A0C-F5B48579C741', 2),
(2, '1FD7F17D-4890-42F7-DD47-F8667FFE5DCF', 'F067BBC0-39F9-DA1B-3B08-4B0D56FE0EA0', 3),
(3, '4AEF8A1F-3932-3062-8E95-616F4AEAD34D', '37F57406-DB4F-6108-5375-950EEA628D37', 1),
(4, '8DCE9268-EF70-88D3-EA83-84F3FFFBB1CE', '6020D6EB-CC9C-BE60-D218-6A778CE430A1', 3),
(5, '79D0C22E-C781-F1DC-B885-F96043ADFD8A', '24556183-1B42-F073-221D-ACF7D6749090', 2),
(6, '9DC419C7-E641-D7FF-BD76-3B3AE44F27C6', 'F7927304-D323-0B52-C411-BDA63BA645D1', 3),
(7, 'D7FF6950-02EC-26CF-4CB6-9C8ADF522761', 'EDAB634D-A081-0CDD-6A94-798C5C21FD61', 2),
(8, '56BFAF72-6F20-0D63-7023-2FF6E16C7593', '71D2F3E7-926D-F875-FA1F-527C251FF7C7', 1),
(9, '8CC8845E-A0C0-F5EF-25EB-433D533FE367', 'F21582B6-7449-3904-A2C9-1559DE5F57D8', 3),
(10, '287A6AAA-9482-4918-C1D1-B6C0E851F9E8', 'C229C596-AB34-D991-32DF-CD7BD356A5B7', 3),
(11, 'E6063894-6E8E-6434-A8C0-3C4285FCD1EF', '18A09A34-8DDE-ADEC-DF10-5B1339F6EDAB', 3),
(12, 'BBC723B4-9A33-EA41-FB04-A41475858075', '804044B9-F9B1-5A21-7A85-6D2BAB3404F9', 3),
(13, '3FBEAC54-CBB2-6830-0F83-74CC906363D6', 'E269D24E-A478-5094-FC2B-E1A7B80E71AA', 1),
(14, '1D815E8A-B25C-6CD9-7B99-D14EE072178D', '59D3549C-C806-9AFC-491B-CD2285998B94', 1),
(15, 'F2C0C180-DAE9-D531-15C9-66DD2D5C96F4', '4355E3B7-7674-45BB-A215-49731B7AF19E', 3),
(16, '1CAACE94-BA4D-10EE-0DEE-662C454126F9', 'B2F3CE45-660F-AF57-10C4-66F807D9FC98', 3),
(17, '79EB72AA-37AC-64D3-2ED0-BBBE38A9E9D1', '05BEC05C-FD1A-02B0-644F-7C637ABAA850', 2),
(18, 'A2F61B8A-5B1E-1968-BEDB-BBBD2ECF20A7', '288744B6-288A-D77D-9256-2425E013C477', 3),
(19, 'E0EF6707-081A-388C-C956-64092C8F501C', 'C26B7362-DB66-2EE9-0F21-34E65946EF35', 1),
(20, '482967B1-C897-9D6E-DC3A-8D3BD737FC07', '8BCAAB84-0EE8-36C6-F864-2E2AA5D26FF0', 3),
(21, '994E4D75-451B-5DD8-43AB-7DFA3E0C21B5', 'B2E58495-9A5A-6813-465A-E21A54C32715', 3),
(22, '80D94822-CAD5-DA87-1B5B-34827AE43952', '457BF06A-937B-9BDD-3389-59061ADA4C2C', 2),
(23, '47EB6DDA-1977-2115-E0DB-D0E0F124E60A', '5D948647-009A-21DC-4B7E-4CBEE9D7583A', 2),
(24, '1ADD2DA3-7011-1C61-8BC8-E589460E996A', '9BFA833D-C5D0-6FE1-1759-CBC1C10B98E9', 2),
(25, '2192BE2A-65C2-7E0F-5AA7-33161022F423', '9B9B382A-3168-E419-A317-853F34C8E7F5', 1),
(26, '3D8DBC76-48DA-AB5B-6387-72E1875923DC', '1C75F78B-B5DD-3900-C101-313B7380B8D1', 3),
(27, 'D43138F3-3AA0-774B-7FFE-A748951C2609', '2E12D922-A619-7A8C-A07E-403CA274C076', 3),
(28, '3EF24FE8-B01A-2F97-B1CE-A3D145DD54CB', '6FC870B6-5C23-2B95-7A6E-889488250174', 3),
(29, '80464BEA-F394-E127-7CC3-19CFF64D8EA2', '3AF8C20A-6859-5AE2-F29B-5208F615A16A', 1),
(30, 'CA04D74A-DF14-EC3F-B4EA-9A499163C82B', '4C8E4D94-097E-F1BE-B71C-5593665B24ED', 1),
(31, '17F00D50-E28A-7286-0ABA-6F9A46B2C9E2', '9FA23E65-7BC4-30BB-4CF8-8E364BD19CE0', 1),
(32, '341AFC3B-68CC-558D-1D9E-9FF485E86A8A', '5182B36B-10A9-3DC4-3680-E5B34D6DC577', 3),
(33, '8C2ED95F-32AA-C93A-8203-B203C38FA082', '76541B57-1FCB-5365-7A3D-711F955769BD', 2),
(34, '40B73C9E-6C86-BB56-1EF5-11EF1254E82C', '594E02BF-38E7-316C-6540-C0BAE300836C', 2),
(35, '8489F2B4-DE39-0C58-A2A0-EFACE3DB4821', 'ABD2AA4B-FE61-F907-EC65-78D8D93EA75E', 3),
(36, '69C224B2-22FE-B2AD-2E54-682ACE384887', 'E17FF066-E67F-A281-FE95-482BBA6E2C4C', 3),
(37, 'B59BA3F1-7FE6-72C3-59B9-466AF221866E', '975D366D-77CD-F6B4-2370-DD9238E7AD96', 1),
(38, '661CFEFD-12CE-1CC6-D90E-CBA99D8AD6BA', '9F40A829-2E22-3DB2-DFAE-2F1C3B932ED0', 1),
(39, '28F00A46-57B1-725D-F0B4-0C748EBBE9B6', '93A636B9-1990-5E15-56F5-EB1F60053667', 3),
(40, '584BE76F-0ABC-752A-EFCE-1970CE12BAF0', 'F67F1B08-39D5-B470-7AA4-363A54ADA05A', 3),
(41, '856F6F6D-8E2F-53C0-E7AE-4B1AF905131C', '73CD4456-CB95-439F-4C1B-F56FCBB4D2E1', 1),
(42, '8979D5B3-F797-E4F0-9397-2F83795A3526', 'B88004E9-8208-71A6-CE1E-877204C45BDA', 3),
(43, 'B75FBBC3-D2E9-8F88-666C-B71DDAC86529', '185BF286-18BE-EAA3-1057-F9AB78B771ED', 1),
(44, 'E221C2CB-B038-AC0B-0685-5D4038956455', '28D1CB52-60CA-6FBB-DF8E-67004FA0BCB1', 3),
(45, 'A924AB10-D3BC-928D-9F7C-B47B68375E12', '070C8603-41F1-4065-D59B-46AC9BC8DB73', 1),
(46, '622B8A64-D660-BB52-ABD8-38F162E80761', '6294C5C0-C4C7-3A4D-0B95-CC192466803A', 3),
(47, '5586721E-FE01-1BE3-085C-A9636439EF13', '689912B9-7690-E31E-F89C-A0CBBB47D448', 2),
(48, '957493AA-9C6B-7E28-E9D0-A22ABE5EE326', '6A21840A-6BCC-B328-0A56-BDC0ACF9F5A1', 2),
(49, '465C86C3-8D05-4EA6-8F2F-4C718CD667FF', '9DB5D782-66BB-F0EB-F9D1-33B228EA1130', 3),
(50, '56646CF2-AC86-82A0-C98F-E5DDA876387A', 'E732F762-23DA-8F1B-518E-3480201EE0C7', 3),
(51, '884A4D42-B37D-9650-14BF-2355157AC2E8', 'FE9CF98A-4B87-34C2-97E2-AB3E91953B68', 3),
(52, '1515A729-1F04-A786-8DB5-1F298AA966E0', '3AD39C14-DC2B-BAD0-34C2-84A97F79E8A6', 3),
(53, 'E948140B-C755-A40C-0F2F-D5122547C53E', '6025892C-66F6-EF63-3F62-C046CE6658B4', 1);

INSERT INTO `producttype` (`id`, `name`, `productTypeId`) VALUES
(1, 'Samsung Galaxy Y DUOS', 'D777E941-8A52-BE3C-6A0C-F5B48579C741'),
(2, 'Samsung Galaxy Nexus', 'F067BBC0-39F9-DA1B-3B08-4B0D56FE0EA0'),
(3, 'Samsung Exhibit 4G', '37F57406-DB4F-6108-5375-950EEA628D37'),
(4, 'Samsung Galaxy Round', '6020D6EB-CC9C-BE60-D218-6A778CE430A1'),
(5, 'Samsung Galaxy K', '24556183-1B42-F073-221D-ACF7D6749090'),
(6, 'Samsung Galaxy Stellar', 'F7927304-D323-0B52-C411-BDA63BA645D1'),
(7, 'Samsung Galaxy Fit', 'EDAB634D-A081-0CDD-6A94-798C5C21FD61'),
(8, 'Samsung Galaxy S III', '71D2F3E7-926D-F875-FA1F-527C251FF7C7'),
(9, 'Samsung Galaxy Pocket Plus', 'F21582B6-7449-3904-A2C9-1559DE5F57D8'),
(10, 'Samsung Galaxy Prevail', 'C229C596-AB34-D991-32DF-CD7BD356A5B7'),
(12, 'Samsung Galaxy S4 Zoom', '18A09A34-8DDE-ADEC-DF10-5B1339F6EDAB'),
(13, 'Samsung Galaxy Europa', '804044B9-F9B1-5A21-7A85-6D2BAB3404F9'),
(15, 'Samsung Galaxy Core', 'E269D24E-A478-5094-FC2B-E1A7B80E71AA'),
(16, 'Samsung Galaxy S III Mini', '59D3549C-C806-9AFC-491B-CD2285998B94'),
(17, 'Samsung Galaxy Pocket Neo', '4355E3B7-7674-45BB-A215-49731B7AF19E'),
(18, 'Samsung Galaxy Victory 4G LTE', 'B2F3CE45-660F-AF57-10C4-66F807D9FC98'),
(20, 'Samsung Galaxy Grand ', '05BEC05C-FD1A-02B0-644F-7C637ABAA850'),
(21, 'Samsung Galaxy Note', '288744B6-288A-D77D-9256-2425E013C477'),
(24, 'Samsung Galaxy Fame', 'C26B7362-DB66-2EE9-0F21-34E65946EF35'),
(26, 'Samsung Galaxy M', '8BCAAB84-0EE8-36C6-F864-2E2AA5D26FF0'),
(27, 'Samsung Galaxy Rugby', 'B2E58495-9A5A-6813-465A-E21A54C32715'),
(28, 'Samsung Galaxy Rush', '457BF06A-937B-9BDD-3389-59061ADA4C2C'),
(29, 'Samsung Galaxy', '5D948647-009A-21DC-4B7E-4CBEE9D7583A'),
(30, 'Samsung Galaxy Mega', '9BFA833D-C5D0-6FE1-1759-CBC1C10B98E9'),
(32, 'Samsung Galaxy Apollo', '9B9B382A-3168-E419-A317-853F34C8E7F5'),
(33, 'Samsung Galaxy S4', '1C75F78B-B5DD-3900-C101-313B7380B8D1'),
(34, 'Samsung Galaxy Y Pro Duos', '2E12D922-A619-7A8C-A07E-403CA274C076'),
(36, 'Samsung Galaxy Neo', '6FC870B6-5C23-2B95-7A6E-889488250174'),
(37, 'Samsung Galaxy Y Plus', '3AF8C20A-6859-5AE2-F29B-5208F615A16A'),
(40, 'Samsung Galaxy S Duos', '4C8E4D94-097E-F1BE-B71C-5593665B24ED'),
(41, 'Samsung Galaxy Y', '9FA23E65-7BC4-30BB-4CF8-8E364BD19CE0'),
(42, 'Samsung Galaxy Ace', '5182B36B-10A9-3DC4-3680-E5B34D6DC577'),
(43, 'Samsung Galaxy SL', '76541B57-1FCB-5365-7A3D-711F955769BD'),
(44, 'Samsung Galaxy Express 2', '594E02BF-38E7-316C-6540-C0BAE300836C'),
(45, 'Samsung Galaxy Pocket Duos', 'ABD2AA4B-FE61-F907-EC65-78D8D93EA75E'),
(47, 'Samsung Galaxy Ace Plus', 'E17FF066-E67F-A281-FE95-482BBA6E2C4C'),
(50, 'Samsung Galaxy S4 Mini', '975D366D-77CD-F6B4-2370-DD9238E7AD96'),
(52, 'Samsung Galaxy Precedent', '9F40A829-2E22-3DB2-DFAE-2F1C3B932ED0'),
(53, 'Samsung Galaxy Appeal', '93A636B9-1990-5E15-56F5-EB1F60053667'),
(54, 'Samsung Galaxy Mini', 'F67F1B08-39D5-B470-7AA4-363A54ADA05A'),
(61, 'Samsung Galaxy Ch@t', '73CD4456-CB95-439F-4C1B-F56FCBB4D2E1'),
(64, 'Samsung Galaxy Win', 'B88004E9-8208-71A6-CE1E-877204C45BDA'),
(66, 'Samsung Stratosphere', '185BF286-18BE-EAA3-1057-F9AB78B771ED'),
(67, 'Samsung Galaxy Pocket', '28D1CB52-60CA-6FBB-DF8E-67004FA0BCB1'),
(68, 'Samsung Galaxy Spica', '070C8603-41F1-4065-D59B-46AC9BC8DB73'),
(77, 'Samsung Galaxy Express', '6294C5C0-C4C7-3A4D-0B95-CC192466803A'),
(79, 'Samsung Galaxy S4 Active', '689912B9-7690-E31E-F89C-A0CBBB47D448'),
(81, 'Samsung Galaxy Mini 2', '6A21840A-6BCC-B328-0A56-BDC0ACF9F5A1'),
(82, 'Samsung Galaxy R', '9DB5D782-66BB-F0EB-F9D1-33B228EA1130'),
(83, 'Samsung Galaxy Beam', 'E732F762-23DA-8F1B-518E-3480201EE0C7'),
(88, 'Samsung Galaxy S Advance', 'FE9CF98A-4B87-34C2-97E2-AB3E91953B68'),
(96, 'Samsung Galaxy Pro', '3AD39C14-DC2B-BAD0-34C2-84A97F79E8A6'),
(98, 'Samsung Galaxy Xcover 2', '6025892C-66F6-EF63-3F62-C046CE6658B4');

INSERT INTO `role` (`id`, `name`, `roleId`) VALUES
(1, 'ROLE_ADMIN', '5fecd179-0310-4e9f-b910-e1a8b7987e3f'),
(2, 'ROLE_USER', '45db6c89-4f65-4e48-82b7-440d68908bc2'),
(3, 'ROLE_SALESMAN', 'a9d837ef-bbde-4eeb-9c94-f6ef5e7d7ec6'),
(4, 'ROLE_ISSUEMANAGER', '9433a008-d608-4790-bf77-c96fe4d49f7e'),
(5, 'ROLE_REPORT', '9ce0786e-9554-4dca-9959-50ef3edc00d6'),
(6, 'ROLE_SELL', '93ff5c69-9850-486f-acb8-a2e34a1178a3'),
(7, 'ROLE_OFFERMAKER', 'bd379c7e-1bcc-4e2d-96eb-6a0805f880d2'),
(8, 'ROLE_USERMANAGER', '530f4715-a5ba-4809-a46b-8f978e3d68b4');

INSERT INTO `salespoint` (`id`, `addressId`, `name`, `salePointId`, `salePointPhoneNumber`, `wareHouseId`) VALUES
(1, '8fcc3b03-808d-4ddc-bc06-80470578f547', 'Club Mozi', 'd9f943a7-7281-4383-8d3f-55e260af54c2', NULL, NULL),
(2, 'de058c58-2cf5-495e-81b5-eddb47d97011', 'Flush Bar', '8f9b8fc7-704d-4b7e-9219-610d4fd22fa5', NULL, NULL),
(3, 'b7d133bf-78a7-4c73-a62d-9389cfb427c5', 'Bodega', '341f8364-af4d-4301-96da-31ec59354a2c', NULL, NULL),
(4, 'b24d6bdc-e6f9-44e2-bfca-e4cddb00afe7', 'FourRo Pont', '44ff0d70-0b95-479e-915b-b40ffbc0d02b', NULL, NULL),
(5, '99911827-29b6-4ea7-97df-5f49d7215e60', 'Molnár Halászcsárda', 'dd4fa0d6-d83a-46b7-8f55-d0648412c55c', NULL, NULL),
(6, 'b7135b61-c4b1-4350-a487-4f5774dd8880', 'HapyHotTruck', 'c1aeae33-ded3-4f02-bfe1-de96483f8e2d', NULL, NULL),
(7, 'bcea1800-b547-4def-8d12-389b89bdaeac', 'DreamGSM Service', '8eb73c4c-00a8-416b-a87c-d414d2d1deef', NULL, NULL),
(8, '9d84b59b-c307-4cf8-909d-77d45ebf04ba', 'Müvház Rendezvényház', '08436ac5-d38b-4cda-9914-5269aa1d79d1', NULL, NULL),
(9, '0f413768-4673-49cd-9e9d-3b58bd5965d5', 'Club Disco', '0ecb6ec5-4856-46a6-bd23-8bff7b745304', NULL, NULL),
(10, 'b668c725-2c49-46e5-9006-f8ebafe8b6e6', 'DevOps Center', 'c07d21c6-ecf2-4ab4-84ae-7dc17abac6e1', NULL, NULL),
(11, '11f30922-be3c-4550-9843-719e9ffc962c', 'Lakatos Fémlerakó', 'ca1f97ec-5673-4464-a7cb-7110e375846a', NULL, NULL);

INSERT INTO `user` (`id`, `email`, `name`, `password`, `phoneNumber`, `registrationDate`, `salePointId`, `userId`, `userName`) VALUES
(1, 'admin@ier-sales.hu', 'admin', '$2a$10$eSsBU3zZmDlU85WKPHf.DutzzefiUXyOIbzeD2ZiNy8A2DQVZ9YIO', '+36303636363', '2015-05-01 10:20:17', NULL, '29d192b2-0d90-4515-b771-8d75f8492ac2', 'admin'),
(2, 'bkovacs@ier-sales.hu', 'Kovács Béla', '$2a$10$qOzyW2ZiOzbpND2h0/mcNekf3sLEsv1DJw.QbtTWqMExUihVsujoO', '+36786582198', '2015-05-29 10:09:25', NULL, 'd5ed4344-3c2e-44e5-afa0-ae11a3b45ea7', 'bkovacs'),
(3, 'jnagy@ier-sales.hu', 'Nagy Jázmin', '$2a$10$5BJv2Bhyd8xPynxwG9ziV.laqOX7I1QyAB.zLiGUJK9ouFIbeGyTO', '+36257242835', '2015-06-01 15:20:41', NULL, '1cef1f8a-01c4-488e-962f-e12324d8cb8a', 'jnagy'),
(4, 'gkantor@ier-sales.hu', 'Kántor Gábor', '$2a$10$xZd4xRnFFUOsT1BQkS7yPOBhz1QiiYq.98wonfTGi90rJWboyMA..', '+36859143215', '2015-06-19 17:25:16', NULL, 'eb043a85-1e0a-4160-9ab5-3a4aac70c0ba', 'gkantor'),
(5, 'imolnar@ier-sales.hu', 'Molnár Izabella', '$2a$10$7AQc.LrMT1A5CZ2zsWha7OyECo7xouX7N29Wsgby2SnYzVLVA54W.', '+36302547258', '2015-06-25 14:09:58', NULL, '927c2277-9ba5-492c-91a9-ba93f6ebe338', 'imolnar'),
(6, 'ajona@ier-sales.hu', 'Jóna András', '$2a$10$PNHUYf8YzT/0gEgPZXdFRuPEJubfasll5jUj.gNbvE3PgKx7y788G', '+36248963214', '2015-06-30 10:35:35', NULL, '8fbd25a4-b255-4008-a6b7-45a9477f49b4', 'ajona'),
(7, 'dkardos@ier-sales.hu', 'Kardos Dóra', '$2a$10$hYJ0Xr8/XJJxmD0kTUDG1..n.4W265RxUYfCnNevQdRkGKKeUEhxu', '+36214785236', '2015-07-06 18:32:17', NULL, 'eda70261-3894-49a0-ac81-37b882974e3b', 'dkardos'),
(8, 'gszabados@ier-sales.hu', 'Szabados Gergő', '$2a$10$lLAL07sv.AmqlugeNQ70oOhLVBPGiDzg5au1yRvQUx/hvNMO.BQgG', '+36728512574', '2015-08-28 14:22:34', NULL, '5edf4224-4dc9-4a2e-8fcd-81782381cd04', 'gszabados'),
(9, 'bbalogh@ier-sales.hu', 'Balogh Boglárka', '$2a$10$uZmTPj2mSiZ0qXDwBba2D.9dlce57PgaDdQKm3FGmbpVifalKmoN6', '+36547893214', '2015-08-11 16:27:05', NULL, 'c7789bda-b8df-44d8-b9ce-863a5cab70bc', 'bbalogh'),
(10, 'abodi@ier-sales.hu', 'Bódi Ágota', '$2a$10$t5g4d7ahPZrsOpxKK5sX1Os5FJ29vlZBHfguZjXyeUK/wZXPnWCQa', '+36201451397', '2015-08-21 21:36:15', NULL, 'aa78dd8d-83dc-4a30-8beb-8233a60116e7', 'abodi');

INSERT INTO `user_role_sw` (`id`, `roleId`, `userId`) VALUES
(1, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', '29d192b2-0d90-4515-b771-8d75f8492ac2'),
(2, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', '0d9db561-50d7-432c-b32a-64e525cd9301'),
(3, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', 'dfc78489-c5b4-4752-9e6b-1aaa6a1dafff'),
(4, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', 'd2e7ce06-f34a-490b-b063-86440fcb23af'),
(5, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', '34d56d3d-e274-4dc1-adf5-2eb8e16b8bda'),
(6, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', '05094c22-49a7-4d6b-bcf2-f9fe3386e932'),
(7, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', 'ed20ac97-ac48-47af-8fb0-9a0bd574be0a'),
(8, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', 'd6068b74-08b5-4577-95c2-60117dce59f5'),
(9, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', '76a83faa-f995-40b3-8542-d5dad25d10dd'),
(10, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', '1eec631c-763d-4065-89cc-707ce23b53fc'),
(11, '5fecd179-0310-4e9f-b910-e1a8b7987e3f', '87b197d5-fe55-4c0e-895f-479262b1e683'),
(12, 'a9d837ef-bbde-4eeb-9c94-f6ef5e7d7ec6', 'd5ed4344-3c2e-44e5-afa0-ae11a3b45ea7'),
(13, '93ff5c69-9850-486f-acb8-a2e34a1178a3', 'd5ed4344-3c2e-44e5-afa0-ae11a3b45ea7'),
(14, 'bd379c7e-1bcc-4e2d-96eb-6a0805f880d2', 'd5ed4344-3c2e-44e5-afa0-ae11a3b45ea7'),
(15, 'bd379c7e-1bcc-4e2d-96eb-6a0805f880d2', '1cef1f8a-01c4-488e-962f-e12324d8cb8a'),
(16, 'a9d837ef-bbde-4eeb-9c94-f6ef5e7d7ec6', 'eb043a85-1e0a-4160-9ab5-3a4aac70c0ba'),
(17, '9433a008-d608-4790-bf77-c96fe4d49f7e', 'eb043a85-1e0a-4160-9ab5-3a4aac70c0ba'),
(18, '93ff5c69-9850-486f-acb8-a2e34a1178a3', 'eb043a85-1e0a-4160-9ab5-3a4aac70c0ba'),
(19, '530f4715-a5ba-4809-a46b-8f978e3d68b4', '927c2277-9ba5-492c-91a9-ba93f6ebe338'),
(20, '45db6c89-4f65-4e48-82b7-440d68908bc2', '8fbd25a4-b255-4008-a6b7-45a9477f49b4'),
(21, '530f4715-a5ba-4809-a46b-8f978e3d68b4', '8fbd25a4-b255-4008-a6b7-45a9477f49b4'),
(22, '9ce0786e-9554-4dca-9959-50ef3edc00d6', '8fbd25a4-b255-4008-a6b7-45a9477f49b4'),
(23, '9ce0786e-9554-4dca-9959-50ef3edc00d6', 'eda70261-3894-49a0-ac81-37b882974e3b'),
(24, 'bd379c7e-1bcc-4e2d-96eb-6a0805f880d2', 'eda70261-3894-49a0-ac81-37b882974e3b'),
(25, 'bd379c7e-1bcc-4e2d-96eb-6a0805f880d2', '5edf4224-4dc9-4a2e-8fcd-81782381cd04'),
(26, '9ce0786e-9554-4dca-9959-50ef3edc00d6', '5edf4224-4dc9-4a2e-8fcd-81782381cd04'),
(27, '45db6c89-4f65-4e48-82b7-440d68908bc2', '5edf4224-4dc9-4a2e-8fcd-81782381cd04'),
(28, '45db6c89-4f65-4e48-82b7-440d68908bc2', 'c7789bda-b8df-44d8-b9ce-863a5cab70bc'),
(29, '9ce0786e-9554-4dca-9959-50ef3edc00d6', 'aa78dd8d-83dc-4a30-8beb-8233a60116e7');