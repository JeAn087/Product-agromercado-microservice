INSERT INTO `products` (`productoId`, `productDescription`,`productName`) VALUES (1, 'Son verdes', 'Manzanas')
INSERT INTO `products` (`productoId`, `productDescription`,`productName`) VALUES (2, 'Son Rojas', 'Manzanas')
INSERT INTO `products` (`productoId`, `productDescription`,`productName`) VALUES (3, 'De la amazonía', 'Peras')

INSERT INTO `categories` (`categoryId`, `categoryDescription`,`categoryName`) VALUES (1,'Orgánicas de la zona del patía', 'Frutas')
INSERT INTO `categories` (`categoryId`, `categoryDescription`,`categoryName`) VALUES (2, 'Orgánicas de las rosas', 'Verduras')

INSERT INTO `disponibility` (`id`, `disponible`, `stock`,`productId`) VALUES (1, true, 10, 1)
INSERT INTO `disponibility` (`id`, `disponible`, `stock`,`productId`) VALUES (2, false, 10, 2)
INSERT INTO `disponibility` (`id`, `disponible`, `stock`,`productId`) VALUES (3, true, 10, 3)

INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 1)
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 2)
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 3)