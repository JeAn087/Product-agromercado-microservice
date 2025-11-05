-- =======================
-- TABLA: products
-- =======================
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (1, 'Son verdes y crocantes', 'Manzanas Verdes');
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (2, 'Son rojas y dulces', 'Manzanas Rojas');
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (3, 'De la amazonía, jugosas', 'Peras');
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (4, 'Cítricas del Valle del Cauca', 'Naranjas');
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (5, 'Ricas en potasio, suaves', 'Bananas');
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (6, 'De la región andina, dulces', 'Uvas');
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (7, 'Frescos del campo', 'Tomates');
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (8, 'De la huerta familiar', 'Zanahorias');
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (9, 'Cultivo orgánico de Popayán', 'Lechugas');
INSERT INTO `products` (`productoId`, `productDescription`, `productName`) VALUES (10, 'Sembradas sin pesticidas', 'Papayas');

-- =======================
-- TABLA: categories
-- =======================
INSERT INTO `categories` (`categoryId`, `categoryDescription`, `categoryName`) VALUES (1, 'Productos comestibles de origen vegetal, generalmente dulces', 'Frutas');
INSERT INTO `categories` (`categoryId`, `categoryDescription`, `categoryName`) VALUES (2, 'Plantas cultivadas para consumo, no dulces', 'Verduras');
INSERT INTO `categories` (`categoryId`, `categoryDescription`, `categoryName`) VALUES (3, 'Plantas cultivadas de tallo blando, comestibles', 'Hortalizas');

-- =======================
-- TABLA: disponibility
-- =======================
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (1, true, 10, 1);
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (2, true, 15, 2);
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (3, true, 8, 3);
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (4, true, 20, 4);
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (5, false, 0, 5);
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (6, true, 12, 6);
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (7, true, 25, 7);
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (8, true, 18, 8);
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (9, false, 5, 9);
INSERT INTO `disponibility` (`id`, `disponible`, `stock`, `productId`) VALUES (10, true, 30, 10);

-- =======================
-- TABLA: product_category (relación producto-categoría)
-- =======================
-- Frutas
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 1);
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 2);
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 3);
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 4);
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 5);
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 6);
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (1, 10);

-- Verduras
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (2, 9);

-- Hortalizas
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (3, 7);
INSERT INTO `product_category` (`categoryId`, `productoId`) VALUES (3, 8);

--Zonas Veredales
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (1, 'Vereda El Rosario', 'Zona agrícola con cultivos de hortalizas y frutas tropicales', 'Municipio de Timbío, Cauca');
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (2, 'Vereda San Isidro', 'Área rural con producción de café y plátano', 'Municipio de Morales, Cauca');
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (3, 'Vereda El Tambo', 'Territorio con clima templado ideal para la producción de frutas cítricas', 'Municipio de El Tambo, Cauca');
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (4, 'Vereda Las Brisas', 'Zona dedicada al cultivo de hortalizas bajo invernadero', 'Municipio de Cajibío, Cauca');
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (5, 'Vereda La Esperanza', 'Comunidad campesina enfocada en la agricultura orgánica', 'Municipio de Popayán, Cauca');
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (6, 'Vereda San Antonio', 'Territorio con producción mixta de legumbres y frutas', 'Municipio de Sotará, Cauca');
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (7, 'Vereda El Porvenir', 'Área montañosa con cultivos tradicionales de maíz y yuca', 'Municipio de Piendamó, Cauca');
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (8, 'Vereda Santa Rosa', 'Zona de producción ecológica y sostenible de hortalizas', 'Municipio de Silvia, Cauca');
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (9, 'Vereda La Florida', 'Región con producción de papa criolla y frutas de clima frío', 'Municipio de Puracé, Cauca');
INSERT INTO `zonaVeredal` (`zonaId`, `nombre`, `descripcion`, `ubicacion`) VALUES (10, 'Vereda El Carmen', 'Territorio de producción diversificada con enfoque agroecológico', 'Municipio de Caldono, Cauca');

INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (1, 1);
INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (1, 5);
INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (2, 2);
INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (2, 4);
INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (3, 3);
INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (3, 8);
INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (4, 7);
INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (5, 9);
INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (6, 6);
INSERT INTO `products_zones` (`productoId`, `zonaId`) VALUES (7, 10);


