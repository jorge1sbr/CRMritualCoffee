-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-12-2025 a las 16:25:03
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_cafe`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id_factura` int(11) NOT NULL,
  `numero_factura` varchar(50) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_emision` datetime DEFAULT current_timestamp(),
  `cliente_nombre` varchar(200) NOT NULL,
  `cliente_nif_cif` varchar(50) DEFAULT NULL,
  `cliente_direccion_fiscal` varchar(255) NOT NULL,
  `base_imponible` decimal(10,2) NOT NULL,
  `tipo_iva` decimal(5,2) NOT NULL,
  `importe_iva` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_pedido`
--

CREATE TABLE `linea_pedido` (
  `id_linea_pedido` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `numero_pedido` varchar(50) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_pedido` datetime DEFAULT current_timestamp(),
  `importe_total` decimal(10,2) NOT NULL,
  `direccion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `categoria` varchar(50) DEFAULT NULL,
  `tipo` varchar(50) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `descripcion`, `categoria`, `tipo`, `precio`, `imagen`, `activo`) VALUES
(2, 'Café Natural 100% Arábica 1 kg', 'Grano 100% Arábica, tueste natural, aroma equilibrado y cuerpo medio. Ideal para hostelería premium.', 'Café en grano', '100% Arábica Natural', 16.50, '/assets/1kgArabica.png', 1),
(3, 'Café Blend Hostelería 70/30 1 kg', 'Mezcla equilibrada Arábica/Robusta, crema intensa y sabor potente. El más usado en bares.', 'Café en grano', 'Blend 70/30', 13.90, '/assets/1kgBlendHosteleria.png', 1),
(4, 'Café Descafeinado Natural en Grano 1 kg', 'Descafeinado de tueste natural, sabor suave sin perder matices aromáticos.', 'Café en grano', 'Descafeinado Natural', 14.50, '/assets/1kgDescafeinado.png', 1),
(5, 'Café de Origen Colombia 1 kg', 'Café 100% Arábica de altura, acidez media y notas afrutadas. Perfecto para cafeterías gourmet.', 'Café en grano', 'Origen', 18.20, '/assets/1kgOrigenColombia.png', 1),
(6, 'Café Especial Baristas 1 kg', 'Selección premium tostada lentamente, pensado para bebidas especiales y espresso de alta calidad.', 'Café en grano', 'Premium / Barista', 19.90, '/assets/1kgEspecialBarista.png', 1),
(7, 'Café Natural 100% Arábica Molido 250 g', 'Molido fino para espresso, sabor equilibrado y aroma intenso.', 'Café molido profesional', '100% Arábica Natural', 4.50, '/assets/250gArabica.png', 1),
(8, 'Café Blend Hostelería 70/30 Molido 250 g', 'Mezcla potente ideal para bares sin molino profesional.', 'Café molido profesional', 'Blend 70/30', 3.80, '/assets/250gBlendHosteleria.png', 1),
(9, 'Café Descafeinado Natural Molido 250 g', 'Descafeinado suave y equilibrado, preparado para espresso o cafetera italiana.', 'Café molido profesional', 'Descafeinado Natural', 4.00, '/assets/250gDescafeinado.png', 1),
(10, 'Café Especial Espresso Fino 250 g', 'Molido específico para un espresso con cuerpo y crema persistente.', 'Café molido profesional', 'Especialidad', 5.20, '/assets/250gExpressoFino.png', 1),
(11, 'Café de Origen Etiopía Molido 250 g', 'Café floral con notas a jazmín y cítricos, ideal para hostelería gourmet.', 'Café molido profesional', 'Origen', 5.60, '/assets/250gOrigenColombia.png', 1),
(12, 'Monodosis 100% Arábica Natural 100 uds', 'Cápsulas ESE con café Arábica de tueste natural, aroma equilibrado y crema suave.', 'Monodosis', '100% Arábica Natural', 21.90, '/assets/MonodosisArabicaNatural100uds.png', 1),
(13, 'Monodosis Blend Hostelería 70/30 100 uds', 'Monodosis intensas con crema espesa y cuerpo fuerte, ideales para cafeterías.', 'Monodosis', 'Blend 70/30', 18.90, '/assets/MonodosisBlendHost100uds.png', 1),
(14, 'Monodosis Descafeinado Natural 100 uds', 'Café descafeinado en cápsula ESE, suave y equilibrado.', 'Monodosis', 'Descafeinado Natural', 19.50, '/assets/MonodosisDescafeinado.png', 1),
(15, 'Monodosis Ristretto Intenso 100 uds', 'Cápsulas muy intensas con molido fino, perfectas para un ristretto potente.', 'Monodosis', 'Intenso', 22.90, '/assets/MonodosisRistrettoIntenso.png', 1),
(16, 'Monodosis Origen Colombia 100 uds', 'Cápsulas ESE con café colombiano de montaña, aroma afrutado y equilibrado.', 'Monodosis', 'Origen', 23.50, '/assets/MonodosisiOrigenColombia.png', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `rol` enum('CLIENTE','ADMIN') NOT NULL DEFAULT 'CLIENTE',
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `codigo_postal` varchar(10) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `rol`, `nombre`, `apellidos`, `email`, `password_hash`, `direccion`, `codigo_postal`, `fecha_alta`) VALUES
(7, 'CLIENTE', 'Jorge', 'Martín', 'jorge@test.com', '$2a$10$Q7aTcx9LJHg3wpqQe/dZruY9QiTKuGdPXoVB80FDB1F41Enr33SXG', 'Mi calle 1', '28000', '2025-11-25 12:37:51'),
(8, 'ADMIN', 'Super', 'Admin', 'admin@test.com', '$2a$10$xHSjDVaqCIaiWwMZ5H8Kj.7JLoR2wPMXvhjWbaRV13wXiushDOeiq', 'Calle Admin 1', '28000', '2025-11-25 13:55:05');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id_factura`),
  ADD UNIQUE KEY `numero_factura` (`numero_factura`),
  ADD KEY `fk_factura_pedido` (`id_pedido`),
  ADD KEY `fk_factura_usuario` (`id_usuario`);

--
-- Indices de la tabla `linea_pedido`
--
ALTER TABLE `linea_pedido`
  ADD PRIMARY KEY (`id_linea_pedido`),
  ADD KEY `fk_linea_pedido_pedido` (`id_pedido`),
  ADD KEY `fk_linea_pedido_producto` (`id_producto`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD UNIQUE KEY `numero_pedido` (`numero_pedido`),
  ADD KEY `fk_pedido_usuario` (`id_usuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `linea_pedido`
--
ALTER TABLE `linea_pedido`
  MODIFY `id_linea_pedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `fk_factura_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_factura_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `linea_pedido`
--
ALTER TABLE `linea_pedido`
  ADD CONSTRAINT `fk_linea_pedido_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_linea_pedido_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_pedido_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
