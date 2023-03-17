-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.31 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para card
CREATE DATABASE IF NOT EXISTS `card` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `card`;

-- Volcando estructura para tabla card.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoria` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla card.categorias: ~4 rows (aproximadamente)
INSERT INTO `categorias` (`id`, `categoria`) VALUES
	(1, 'Hombre'),
	(2, 'Mujer'),
	(3, 'Niño'),
	(4, 'Niña');

-- Volcando estructura para procedimiento card.producto
DELIMITER //
CREATE PROCEDURE `producto`(cod INT)
SELECT productos.id,productos.nombre,productos.descripcion//
DELIMITER ;

-- Volcando estructura para tabla card.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `precio_normal` decimal(10,2) NOT NULL,
  `precio_rebajado` decimal(10,2) NOT NULL,
  `cantidad` int NOT NULL,
  `imagen` varchar(50) NOT NULL,
  `id_categoria` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla card.productos: ~16 rows (aproximadamente)
INSERT INTO `productos` (`id`, `nombre`, `descripcion`, `precio_normal`, `precio_rebajado`, `cantidad`, `imagen`, `id_categoria`) VALUES
	(1, 'Zapatilla hombre negra', 'Zapatilla color negro amarillo', 350.00, 299.00, 20, 'hombre1.jpg', 1),
	(2, 'Zapatilla hombre blanco y negro', 'Zapatilla negro blanco', 400.00, 349.00, 30, 'hombre2.jpg', 1),
	(3, 'Zapatilla hombre negro', 'Zapatilla negro', 200.00, 189.00, 50, 'hombre3.jpg', 1),
	(4, 'Zapatilla hombre nike', 'Zapatilla nike', 260.00, 239.00, 80, 'hombre4.jpg', 1),
	(5, 'Zapatilla rosa y negro', 'Nike', 200.00, 189.00, 0, 'mujer1.jpg', 2),
	(6, 'Zapatilla celeste y gris', 'Puma', 350.00, 299.00, 0, 'mujer2.jpg', 2),
	(7, 'Zapatilla rosa y purpura', 'Adidas', 250.00, 199.00, 0, 'mujer3.jpg', 2),
	(8, 'Zapatilla rosada', 'Puma', 120.00, 99.00, 0, 'mujer4.jpg', 2),
	(9, 'Zapatilla niño negro y celeste', 'Nike', 450.00, 399.00, 0, 'niño1.jpg', 3),
	(10, 'Zapatilla negro y rojo', 'Nike', 550.00, 499.00, 0, 'niño2.jpg', 3),
	(11, 'Zapatilla diseño spiderman', 'Puma', 360.00, 319.00, 0, 'niño3.jpg', 3),
	(12, 'Zapatilla diseño transformer', 'Puma', 150.00, 129.00, 0, 'niño4.jpg', 3),
	(13, 'Zapatilla rosada', 'Nike', 100.00, 89.00, 0, 'niña1.jpg', 4),
	(14, 'Zapatilla diseño unicornios', 'Nike', 450.00, 399.00, 0, 'niña2.jpg', 4),
	(15, 'Zapatilla rosa', 'Puma', 200.00, 199.00, 0, 'niña3.jpg', 4),
	(16, 'Zapatilla rosa y blanco', 'Adidas', 210.00, 179.00, 0, 'niña4.jpg', 4);

-- Volcando estructura para tabla card.tbboleta
CREATE TABLE IF NOT EXISTS `tbboleta` (
  `serie` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `numero` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fecha` date DEFAULT NULL,
  `dnicliente` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `dniempleado` varchar(8) DEFAULT NULL,
  `estado` varchar(15) DEFAULT NULL,
  `igv` decimal(20,6) DEFAULT NULL,
  `TOTAL` decimal(20,6) DEFAULT NULL,
  PRIMARY KEY (`serie`,`numero`),
  KEY `dniempleado` (`dniempleado`),
  KEY `dnicliente` (`dnicliente`) USING BTREE,
  CONSTRAINT `FK_tbboleta_tbcliente` FOREIGN KEY (`dnicliente`) REFERENCES `tbcliente` (`dni`),
  CONSTRAINT `FK_tbboleta_tbempleado` FOREIGN KEY (`dniempleado`) REFERENCES `tbempleado` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla card.tbboleta: ~3 rows (aproximadamente)
INSERT INTO `tbboleta` (`serie`, `numero`, `fecha`, `dnicliente`, `dniempleado`, `estado`, `igv`, `TOTAL`) VALUES
	('B001', '0000001', '2023-02-20', '72684943', '12345678', 'ACTIVO', NULL, 5000.000000),
	('B001', '0000002', '2023-02-20', '72684943', '12345678', 'ACTIVO', 630.000000, 4130.000000),
	('B001', '0000007', '2023-02-20', '72684943', '12345678', 'ACTIVO', 630.000000, 4130.000000);

-- Volcando estructura para tabla card.tbcargo
CREATE TABLE IF NOT EXISTS `tbcargo` (
  `idcargo` int NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idcargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla card.tbcargo: ~4 rows (aproximadamente)
INSERT INTO `tbcargo` (`idcargo`, `nombre`) VALUES
	(1, 'ADMINISTRADOR'),
	(2, 'CAJERO'),
	(3, 'VENDEDOR'),
	(4, 'USUARIO');

-- Volcando estructura para tabla card.tbcliente
CREATE TABLE IF NOT EXISTS `tbcliente` (
  `dni` varchar(50) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `direccion` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `clave` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `celular` varchar(50) DEFAULT NULL,
  `estado` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla card.tbcliente: ~2 rows (aproximadamente)
INSERT INTO `tbcliente` (`dni`, `nombre`, `apellido`, `direccion`, `email`, `clave`, `celular`, `estado`) VALUES
	('72684943', 'Josue', 'Chambilla', 'Jose callees', 'josue.200297@hotmail.com', '1234', '982531296', 'ACTIVO'),
	('admin', 'admin', 'admin', 'admin', 'admin', 'admin', 'admin', 'ACTIVO');

-- Volcando estructura para tabla card.tbdetalleboleta
CREATE TABLE IF NOT EXISTS `tbdetalleboleta` (
  `serie` varchar(50) DEFAULT NULL,
  `numero` varchar(50) DEFAULT NULL,
  `codprodcuto` int DEFAULT NULL,
  `unidadcompra` int DEFAULT NULL,
  `preciounitario` decimal(20,6) DEFAULT NULL,
  `subtotal` decimal(20,6) DEFAULT NULL,
  KEY `serie` (`serie`),
  KEY `codprodcuto` (`codprodcuto`),
  KEY `numero` (`numero`),
  KEY `FK_tbdetalleboleta_tbboleta` (`serie`,`numero`),
  CONSTRAINT `FK_tbdetalleboleta_productos` FOREIGN KEY (`codprodcuto`) REFERENCES `productos` (`id`),
  CONSTRAINT `FK_tbdetalleboleta_tbboleta` FOREIGN KEY (`serie`, `numero`) REFERENCES `tbboleta` (`serie`, `numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla card.tbdetalleboleta: ~1 rows (aproximadamente)
INSERT INTO `tbdetalleboleta` (`serie`, `numero`, `codprodcuto`, `unidadcompra`, `preciounitario`, `subtotal`) VALUES
	('B001', '0000001', 1, 4, 20.000000, 80.000000);

-- Volcando estructura para tabla card.tbempleado
CREATE TABLE IF NOT EXISTS `tbempleado` (
  `dni` varchar(50) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `idcargo` int DEFAULT NULL,
  `clave` varchar(50) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dni`),
  KEY `idcargo` (`idcargo`),
  CONSTRAINT `FK_tbempleado_tbcargo` FOREIGN KEY (`idcargo`) REFERENCES `tbcargo` (`idcargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla card.tbempleado: ~2 rows (aproximadamente)
INSERT INTO `tbempleado` (`dni`, `nombre`, `apellido`, `direccion`, `telefono`, `idcargo`, `clave`, `estado`) VALUES
	('123', 'Josue', 'Chambilla', 'ABC', '982531263', 2, '123', 'ACTIVO'),
	('12345678', 'Jhonny', 'Rivera', 'Los cristales', '95214589', 1, '123456', 'ACTIVO');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
