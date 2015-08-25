INSERT INTO `address` (`id`, `addressId`, `city`, `houseNumber`, `street`, `zipCode`) VALUES
(1, '8fcc3b03-808d-4ddc-bc06-80470578f547', 'Székesfehérvár', '122', 'Bajcsy', '5676'),
(2, '4794975c-678f-4987-8202-5dd3e3c6192f', 'Pécs', '63', 'Soproni sugárút', '8787'),
(3, '3d1d522f-ec01-4d6c-a139-669740f715b1', 'Debrecen', '32', 'Kartács utca ', '6523'),
(4, 'dbcd728e-db79-4c9d-8aa9-f334e21db185', 'Kecskemét', '8', 'Kossuth utca', '2365'),
(5, '207d6342-b7d3-46c0-be2d-16d1e46483a5', 'Pécs', '52', 'Hungarcumok útja', '5555'),
(6, '99911827-29b6-4ea7-97df-5f49d7215e60', 'Budapest', '45', 'Erzsébet tér', '2332'),
(7, 'b7135b61-c4b1-4350-a487-4f5774dd8880', 'Székesfehérvár', '67', 'Ládmányosi sugárút', '1111'),
(8, 'c5a1ab02-6aa7-448a-ac9a-309c24bc2058', 'Miskolc', '91', 'Vaswáry', '45545'),
(9, '24d9dcaf-e2e4-4207-9b66-562f94cfb0cd', 'Mucsaröcsöge', '32', 'Kisrostélyos köz', '6233'),
(10, 'de058c58-2cf5-495e-81b5-eddb47d97011', 'Kecskemét', '34', 'Munkácsy', '2311'),
(11, 'cd2e0919-cb0d-4af5-b161-6997f2a6c64d', 'Tiszaújváros', '4', 'Kerékgyártó tér', '7563'),
(12, 'b7d133bf-78a7-4c73-a62d-9389cfb427c5', 'Debrecen', '101', 'Rákosnégyesi utca', '6232'),
(13, 'ec89fd8a-6124-49af-ac99-ea5b18509dad', 'Ménespuszta', '33', 'Kürtös utca 3', '3332');

INSERT INTO `warehouse` (`id`, `warehouseId`, `warehouseName`) VALUES
(1, 'dsf987zsd832ih4wiur-sdfwiu3h', 'Központi Raktár Budapest'),
(2, '43kjn3i4nerfgdg-43nbudctbilk67686', 'Nyugati Raktár I'),
(3, 'b9f5ff1e-db28-4fea-9cd8-7ec138b337c7', 'Debreceni II. raktár'),
(4, '8c9b09d3-6c08-4794-9c20-7695ae3e5ffc', 'Keleti Raktár 2 Hajdúszoboszló'),
(5, '55986a86-48e9-4e94-ba31-fbf880f5bc6d', 'Keleti Raktár 3 Hajdúszoboszló'),
(6, '3ad8a017-1d7b-4fe4-841f-db4989cef115', 'Nyugati Alraktár III'),
(7, '3a58eb10-f554-4c8f-b86d-94e5a0203044', 'Újvárosi Raktár I'),
(8, 'ed75ca2d-4224-46e7-b2b6-feba1cbedb9a', 'Pusztai Raktár');

INSERT INTO `salespoint` (`id`, `addressId`, `name`, `salePointId`, `salePointPhoneNumber`, `wareHouseId`) VALUES
(1, 'de058c58-2cf5-495e-81b5-eddb47d97011', 'Flush Bar', '8f9b8fc7-704d-4b7e-9219-610d4fd22fa5', '234234', '3ad8a017-1d7b-4fe4-841f-db4989cef115'),
(2, 'b7d133bf-78a7-4c73-a62d-9389cfb427c5', 'Bodega', '341f8364-af4d-4301-96da-31ec59354a2c', '+3662895456', '8c9b09d3-6c08-4794-9c20-7695ae3e5ffc'),
(3, '99911827-29b6-4ea7-97df-5f49d7215e60', 'Molnár Halászcsárda', 'dd4fa0d6-d83a-46b7-8f55-d0648412c55c', '+3662321546', 'dsf987zsd832ih4wiur-sdfwiu3h'),
(4, 'b7135b61-c4b1-4350-a487-4f5774dd8880', 'HapyHotTruck', 'c1aeae33-ded3-4f02-bfe1-de96483f8e2d', '+36302381324', '55986a86-48e9-4e94-ba31-fbf880f5bc6d'),
(5, '24d9dcaf-e2e4-4207-9b66-562f94cfb0cd', 'Fazekas Panzió', '617d4bc4-6180-4e66-929c-f9de249b1475', '+3695632145', '3ad8a017-1d7b-4fe4-841f-db4989cef115'),
(6, 'cd2e0919-cb0d-4af5-b161-6997f2a6c64d', 'Ab Roncstelep', 'a601bb41-55d1-45bb-8fcd-0738edd9fcd7', '+3623152125', '8c9b09d3-6c08-4794-9c20-7695ae3e5ffc'),
(7, 'c5a1ab02-6aa7-448a-ac9a-309c24bc2058', 'FourRo PontOK', '6ca215e5-31e4-493b-9e02-b6e1a811de82', '0659632152', '8c9b09d3-6c08-4794-9c20-7695ae3e5ffc'),
(8, '4794975c-678f-4987-8202-5dd3e3c6192f', 'Szörpölde', '6d8057c9-2fdd-4a39-b962-8e82eb10df81', '+3692654456', '43kjn3i4nerfgdg-43nbudctbilk67686'),
(9, 'dbcd728e-db79-4c9d-8aa9-f334e21db185', 'Rush Hour Store', 'da311d54-8e17-4113-a266-4c6986b4c154', '+36302689542', 'b9f5ff1e-db28-4fea-9cd8-7ec138b337c7'),
(10, '207d6342-b7d3-46c0-be2d-16d1e46483a5', 'Kertitörpe Központ', '42a3070b-3b70-4f59-a738-32e1e23fe733', '+6323262651', '43kjn3i4nerfgdg-43nbudctbilk67686'),
(11, 'ec89fd8a-6124-49af-ac99-ea5b18509dad', 'Lóakol', 'd5d9ade0-3f91-464c-a3ea-4c1029983095', '+365721322', 'ed75ca2d-4224-46e7-b2b6-feba1cbedb9a');