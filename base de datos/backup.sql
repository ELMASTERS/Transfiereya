-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.30 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para bd_transfiereya
CREATE DATABASE IF NOT EXISTS `bd_transfiereya` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_transfiereya`;

-- Volcando estructura para tabla bd_transfiereya.tb_cliente
CREATE TABLE IF NOT EXISTS `tb_cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `cedula` varchar(15) NOT NULL,
  `pais` varchar(30) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `estado` int NOT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_transfiereya.tb_cliente: ~28 rows (aproximadamente)
INSERT INTO `tb_cliente` (`idCliente`, `nombre`, `apellido`, `cedula`, `pais`, `telefono`, `direccion`, `estado`) VALUES
	(1, 'Sofia', 'cabral', '22400401950', 'RD', '45345554', 'los alcarrizos2', 0),
	(2, 'kathia', 'graciano', '12345678', 'rd', '56415465', 'km14', 0),
	(3, 'justo', 'pow', '00123456789', 'República Dominicana', '8091234567', 'Calle 1, Zona 1', 0),
	(4, 'david', 'Gomez', '01234567890', 'Santiago', '8092345678', 'Calle 2, Zona 2', 0),
	(5, 'kathia', 'Santana', '02345678901', 'cuba', '8093456789', 'Calle 3, Zona 3', 0),
	(6, 'perez', 'Lopez', '03456789012', 'República Dominicana', '8094567890', 'Calle 4, Zona 4', 0),
	(7, 'Carlos', 'Diaz', '04567890123', 'HAITI', '8095678901', 'Calle 5, Zona 5', 0),
	(8, 'Laura 62', 'Martinez', '05678901234', 'República Dominicana', '8096789012', 'Calle 6, Zona 6', 0),
	(9, 'Francisco d', 'Rodriguez', '06789012345', 'belice', '8097890123', 'Calle 7, Zona 7', 0),
	(10, 'Sofia', 'Hernandez', '07890123456', 'estados unidos', '8098901234', 'Calle 8, Zona 8', 0),
	(11, 'Daniel', 'Garcia', '08901234567', 'República Dominicana', '8099012345', 'Calle 9, Zona 9', 0),
	(12, 'Valeria', 'Fernandez', '09012345678', 'República Dominicana', '8090123456', 'Calle 10, Zona 10', 0),
	(13, 'Emilio', 'Moreno', '10123456789', 'República Dominicana', '8092345678', 'Calle 11, Zona 11', 0),
	(14, 'Camila', 'Ramirez', '11234567890', 'santo domingo', '8093456789', 'Calle 12, Zona 12', 0),
	(15, 'Diego', 'Vasquez', '12345678901', 'República Dominicana', '8094567890', 'Calle 13, Zona 13', 0),
	(16, 'Isabella', 'Acosta', '23456789012', 'República Dominicana', '8095678901', 'Calle 14, Zona 14', 0),
	(17, 'julio', 'castro', '34567890123', 'RD', '8096789012', 'Calle 15, Zona 15', 0),
	(18, 'Valentina', 'Ortega', '45678901234', 'República Dominicana', '8097890123', 'Calle 16, Zona 16', 0),
	(19, 'Sebastian', 'Torres', '56789012345', 'BELICE', '8098901234', 'Calle 17, Zona 17', 0),
	(20, 'jose ad3', 'Castillo', '67890123456', 'HAITI', '8099012345', 'Calle 18, Zona 18', 0),
	(21, 'Pedro', 'Gutierrez', '78901234567', 'República Dominicana', '8090123456', 'Calle 19, Zona 19', 0),
	(22, 'Luciana', 'Lara', '89012345678', 'RD', '8092345678', 'Calle 20, Zona 20', 0),
	(23, 'mario s23', 'beltre', '123456789', 'puerto rico', '6545456', 'el almirante', 0),
	(24, 'maicol 2', 'guiterrez', '2245515412', 'Santiago', '531516', 'los mamelles', 0),
	(25, 'felix', 'tejada debora', '158468465', 'PUERTO RICO', '65416846', 'santo domingo', 0),
	(26, 'Cristian Miguel', 'Casanovas', '00112125114', 'BELICE', '8494080658', 'C.Rogelio Roselle, Bayona.', 0),
	(27, 'ayeisa', 'Santana A.', '789456123', 'República Dominicana', '654164545', 'sabana perdida', 0),
	(28, 'Felipe', 'Villanueva', '402-0000000-1', 'República Dominicana', '809-256-5656', 'Calle No me acuerdo', 0);

-- Volcando estructura para tabla bd_transfiereya.tb_transaccion
CREATE TABLE IF NOT EXISTS `tb_transaccion` (
  `idTransaccion` int NOT NULL AUTO_INCREMENT,
  `idClienteRemitente` int DEFAULT NULL,
  `idClienteDestinatario` int DEFAULT NULL,
  `montoTransaccion` decimal(20,2) DEFAULT NULL,
  `monedaRemitente` varchar(10) DEFAULT NULL,
  `monedaDestinatario` varchar(10) DEFAULT NULL,
  `tipoCambio` decimal(10,2) DEFAULT NULL,
  `totalPagar` decimal(10,2) DEFAULT NULL,
  `fechaHoraTransaccion` datetime DEFAULT NULL,
  `estado` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTransaccion`),
  KEY `idClienteRemitente` (`idClienteRemitente`),
  KEY `idClienteDestinatario` (`idClienteDestinatario`),
  CONSTRAINT `tb_transaccion_ibfk_1` FOREIGN KEY (`idClienteRemitente`) REFERENCES `tb_cliente` (`idCliente`),
  CONSTRAINT `tb_transaccion_ibfk_2` FOREIGN KEY (`idClienteDestinatario`) REFERENCES `tb_cliente` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_transfiereya.tb_transaccion: ~92 rows (aproximadamente)
INSERT INTO `tb_transaccion` (`idTransaccion`, `idClienteRemitente`, `idClienteDestinatario`, `montoTransaccion`, `monedaRemitente`, `monedaDestinatario`, `tipoCambio`, `totalPagar`, `fechaHoraTransaccion`, `estado`) VALUES
	(1, 2, 1, 145.00, 'USD', 'DOP', 8410.00, 43534.00, '2024-05-14 11:06:40', 1),
	(2, 1, 2, 100.00, 'USD', 'EUR', 93.66, 76.80, '2024-05-09 14:20:20', 1),
	(3, 1, 1, 300.00, 'USD', 'DOP', 17400.00, 14268.00, '2024-05-10 09:15:34', 1),
	(4, 20, 21, 500.00, 'USD', 'EUR', 468.32, 384.02, '2024-05-02 19:03:20', 1),
	(5, 25, 1, 500.00, 'USD', 'EUR', 468.32, 384.02, '2024-05-15 13:55:13', 1),
	(6, 20, 21, 500.00, 'USD', 'EUR', 468.32, 384.02, '2024-05-02 19:05:08', 1),
	(7, 5, 6, 800.00, 'USD', 'DOP', 46400.00, 38048.00, '2024-05-02 19:07:13', 1),
	(9, 6, 21, 1500.00, 'USD', 'EUR', 1404.96, 1152.07, '2024-05-14 11:06:15', 1),
	(10, 5, 20, 800.00, 'EUR', 'DOP', 48800.00, 40016.00, '2024-05-02 19:14:05', 1),
	(11, 1, 1, 350.00, 'DOP', 'USD', 5.95, 4.88, '2024-05-08 13:31:01', 1),
	(14, 1, 4, 300.00, 'DOP', 'USD', 5.10, 4.18, '2024-05-02 19:37:13', 1),
	(21, 4, 24, 33.00, 'USD', 'USD', 33.00, 27.06, '2024-05-02 19:59:32', 1),
	(22, 10, 19, 2500.00, 'USD', 'DOP', 145000.00, 118900.00, '2024-05-10 09:14:05', 1),
	(23, 1, 2, 100.00, 'USD', 'DOP', 5800.00, 4756.00, '2024-05-09 13:51:27', 1),
	(30, 3, 3, 600.00, 'USD', 'USD', 600.00, 570.00, '2024-05-09 14:58:58', 1),
	(31, 25, 25, 100.00, 'USD', 'DOP', 5800.00, 5510.00, '2024-05-09 14:59:41', 1),
	(32, 23, 23, 800.00, 'USD', 'USD', 800.00, 760.00, '2024-05-09 15:00:32', 1),
	(33, 4, 4, 650.00, 'USD', 'USD', 650.00, 617.50, '2024-05-09 15:13:26', 1),
	(34, 6, 6, 777.00, 'USD', 'USD', 777.00, 738.15, '2024-05-09 15:16:31', 1),
	(43, 4, 5, 1555.00, 'USD', 'USD', 1555.00, 1477.25, '2024-05-09 15:22:21', 1),
	(44, 5, 4, 600.00, 'USD', 'USD', 600.00, 570.00, '2024-05-09 15:22:56', 1),
	(47, 4, 3, 888.00, 'USD', 'USD', 888.00, 843.60, '2024-05-10 08:29:11', 1),
	(48, 1, 2, 999.00, 'USD', 'DOP', 57942.00, 55044.90, '2024-05-10 08:30:49', 1),
	(49, 2, 4, 444.00, 'USD', 'DOP', 25752.00, 24464.40, '2024-05-10 08:35:40', 1),
	(50, 3, 5, 555.00, 'USD', 'DOP', 32190.00, 30580.50, '2024-05-10 08:42:00', 1),
	(51, 1, 2, 111.00, 'USD', 'DOP', 6438.00, 6116.10, '2024-05-10 08:46:28', 1),
	(52, 4, 14, 333.00, 'USD', 'USD', 333.00, 316.35, '2024-05-14 11:17:23', 1),
	(53, 5, 4, 222.00, 'DOP', 'USD', 3.77, 3.59, '2024-05-10 09:09:41', 1),
	(54, 4, 3, 444.00, 'USD', 'DOP', 25752.00, 24464.40, '2024-05-10 09:55:54', 1),
	(55, 3, 4, 800.00, 'USD', 'DOP', 46400.00, 44080.00, '2024-05-10 10:50:37', 1),
	(56, 3, 3, 333.00, 'USD', 'DOP', 19314.00, 18348.30, '2024-05-10 10:54:27', 1),
	(57, 2, 2, 2222.00, 'USD', 'DOP', 128876.00, 122432.20, '2024-05-10 11:00:28', 1),
	(58, 3, 3, 11111.00, 'USD', 'DOP', 644438.00, 612216.10, '2024-05-10 11:03:29', 1),
	(59, 3, 5, 7777.00, 'USD', 'DOP', 451066.00, 428512.70, '2024-05-10 11:05:07', 1),
	(60, 3, 3, 66666.00, 'USD', 'DOP', 3866628.00, 3673296.60, '2024-05-10 11:10:12', 1),
	(61, 2, 4, 999111.00, 'USD', 'DOP', 57948438.00, 55051016.10, '2024-05-10 11:20:49', 1),
	(62, 4, 4, 8888.00, 'USD', 'USD', 8888.00, 8443.60, '2024-05-10 11:21:50', 1),
	(63, 4, 4, 123456.00, 'USD', 'DOP', 7160448.00, 6802425.60, '2024-05-10 11:22:56', 1),
	(64, 3, 4, 456456.00, 'USD', 'DOP', 26474448.00, 25150725.60, '2024-05-10 11:34:56', 1),
	(65, 3, 5, 23423.00, 'USD', 'USD', 23423.00, 22251.85, '2024-05-10 11:40:14', 1),
	(66, 3, 3, 5676578.00, 'USD', 'USD', 5676578.00, 5392749.10, '2024-05-10 11:41:15', 1),
	(67, 23, 22, 888222.00, 'USD', 'DOP', 51516876.00, 48941032.20, '2024-05-10 12:13:03', 1),
	(68, 3, 3, 333333.00, 'USD', 'DOP', 19333314.00, 18366648.30, '2024-05-10 12:17:10', 1),
	(69, 3, 5, 454545.00, 'USD', 'DOP', 26363610.00, 25045429.50, '2024-05-10 12:32:33', 1),
	(70, 2, 4, 467567.00, 'USD', 'DOP', 27118886.00, 25762941.70, '2024-05-10 12:40:50', 1),
	(71, 2, 3, 22222.00, 'USD', 'DOP', 1288876.00, 1224432.20, '2024-05-10 12:52:30', 1),
	(72, 1, 4, 450.00, 'USD', 'DOP', 26100.00, 24795.00, '2024-05-13 14:37:09', 1),
	(73, 4, 4, 455.00, 'USD', 'DOP', 26390.00, 25070.50, '2024-05-13 15:06:39', 1),
	(74, 2, 4, 666.00, 'USD', 'DOP', 38628.00, 36696.60, '2024-05-13 15:09:37', 1),
	(75, 2, 4, 555.00, 'USD', 'USD', 555.00, 527.25, '2024-05-13 15:14:00', 1),
	(76, 1, 5, 333.00, 'USD', 'DOP', 19314.00, 18348.30, '2024-05-13 15:15:54', 1),
	(77, 3, 3, 4444.00, 'USD', 'DOP', 257752.00, 244864.40, '2024-05-13 15:32:14', 1),
	(78, 4, 4, 555323.00, 'USD', 'DOP', 32208734.00, 30598297.30, '2024-05-13 15:35:54', 1),
	(79, 2, 5, 1212.00, 'USD', 'EUR', 1135.21, 1078.45, '2024-05-13 15:39:25', 1),
	(80, 4, 4, 456456.00, 'USD', 'DOP', 26474448.00, 25150725.60, '2024-05-13 15:51:59', 1),
	(81, 2, 4, 34243.00, 'USD', 'DOP', 1986094.00, 1886789.30, '2024-05-13 15:53:22', 1),
	(82, 1, 3, 54645.00, 'USD', 'DOP', 3169410.00, 3010939.50, '2024-05-13 15:58:59', 1),
	(83, 2, 6, 111.00, 'USD', 'DOP', 6438.00, 6116.10, '2024-05-14 08:14:45', 1),
	(84, 3, 6, 222.00, 'USD', 'DOP', 12876.00, 12232.20, '2024-05-14 08:18:25', 1),
	(85, 4, 5, 888.00, 'USD', 'DOP', 51504.00, 48928.80, '2024-05-14 08:24:11', 1),
	(86, 2, 2, 333.00, 'USD', 'DOP', 19314.00, 18348.30, '2024-05-14 08:25:05', 1),
	(87, 4, 6, 223232.00, 'USD', 'DOP', 12947456.00, 12300083.20, '2024-05-14 08:27:04', 1),
	(88, 3, 3, 89898.00, 'USD', 'DOP', 5214084.00, 4953379.80, '2024-05-14 08:30:15', 1),
	(89, 4, 4, 435234.00, 'USD', 'DOP', 25243572.00, 23981393.40, '2024-05-14 08:31:59', 1),
	(90, 4, 5, 2342.00, 'USD', 'DOP', 135836.00, 129044.20, '2024-05-14 08:34:43', 1),
	(91, 4, 2, 234123.00, 'DOP', 'DOP', 234123.00, 222416.85, '2024-05-14 08:35:38', 1),
	(92, 3, 3, 5000.00, 'USD', 'USD', 5000.00, 4750.00, '2024-05-14 08:45:03', 1),
	(93, 2, 1, 6000.00, 'USD', 'DOP', 348000.00, 330600.00, '2024-05-14 08:46:22', 1),
	(94, 3, 3, 7888.00, 'USD', 'DOP', 457504.00, 434628.80, '2024-05-14 08:46:57', 1),
	(95, 2, 1, 6000.00, 'USD', 'DOP', 348000.00, 330600.00, '2024-05-14 08:48:04', 1),
	(96, 5, 17, 60003.00, 'USD', 'USD', 60003.00, 57002.85, '2024-05-14 08:49:44', 1),
	(97, 24, 25, 354345.00, 'USD', 'USD', 354345.00, 336627.75, '2024-05-14 09:29:52', 1),
	(98, 4, 2, 456456.00, 'USD', 'DOP', 26474448.00, 25150725.60, '2024-05-14 11:39:49', 0),
	(99, 3, 4, 45645.00, 'USD', 'EUR', 42752.93, 40615.29, '2024-05-14 11:15:21', 1),
	(100, 3, 2, 568.00, 'DOP', 'USD', 9.66, 9.17, '2024-05-14 11:29:32', 1),
	(101, 24, 23, 4444.00, 'USD', 'USD', 4444.00, 4221.80, '2024-05-14 11:43:20', 1),
	(102, 26, 23, 5000.00, 'USD', 'DOP', 290000.00, 275500.00, '2024-05-14 13:17:08', 1),
	(103, 27, 25, 10000.00, 'USD', 'DOP', 580000.00, 551000.00, '2024-05-14 13:34:22', 1),
	(104, 2, 5, 34534.00, 'USD', 'DOP', 2002972.00, 1902823.40, '2024-05-14 14:27:51', 0),
	(105, 3, 3, 345243.00, 'USD', 'DOP', 20024094.00, 19022889.30, '2024-05-14 14:33:05', 0),
	(106, 2, 2, 4534.00, 'USD', 'USD', 4534.00, 4307.30, '2024-05-14 14:39:18', 0),
	(107, 3, 4, 456456.00, 'USD', 'DOP', 26474448.00, 25150725.60, '2024-05-14 15:10:53', 0),
	(108, 2, 3, 34534.00, 'USD', 'USD', 34534.00, 32807.30, '2024-05-14 15:29:58', 0),
	(109, 3, 4, 34234.00, 'USD', 'USD', 34234.00, 32522.30, '2024-05-14 15:31:09', 0),
	(110, 5, 4, 4234.00, 'USD', 'USD', 4234.00, 4022.30, '2024-05-14 15:33:57', 0),
	(111, 2, 2, 34534.00, 'USD', 'USD', 34534.00, 32807.30, '2024-05-14 15:41:44', 0),
	(112, 2, 4, 3453456.00, 'USD', 'USD', 3453456.00, 3280783.20, '2024-05-14 15:47:21', 0),
	(113, 2, 4, 234234.00, 'USD', 'EUR', 219392.93, 208423.29, '2024-05-14 15:49:49', 0),
	(114, 3, 3, 232342.00, 'USD', 'DOP', 13475836.00, 12802044.20, '2024-05-15 09:16:45', 0),
	(115, 25, 26, 32342.00, 'USD', 'DOP', 1875836.00, 1782044.20, '2024-05-15 09:18:16', 0),
	(116, 26, 23, 453.00, 'USD', 'DOP', 26274.00, 24960.30, '2024-05-15 09:21:11', 0),
	(117, 28, 27, 100000.00, 'USD', 'USD', 100000.00, 95000.00, '2024-05-15 12:53:47', 1),
	(118, 3, 4, 34234.00, 'USD', 'DOP', 1985572.00, 1886293.40, '2024-05-16 18:13:10', 0),
	(119, 20, 19, 3242.00, 'USD', 'USD', 3242.00, 3079.90, '2024-05-16 18:30:50', 0),
	(120, 25, 26, 3423.00, 'USD', 'USD', 3423.00, 3251.85, '2024-05-16 18:31:34', 0),
	(121, 1, 7, 1000.00, 'USD', 'DOP', 58000.00, 55100.00, '2024-05-16 20:45:08', 1);

-- Volcando estructura para tabla bd_transfiereya.tb_usuario
CREATE TABLE IF NOT EXISTS `tb_usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `usuario` varchar(15) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `rolUsuario` varchar(15) NOT NULL,
  `estado` int NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_transfiereya.tb_usuario: ~4 rows (aproximadamente)
INSERT INTO `tb_usuario` (`idUsuario`, `nombre`, `apellido`, `usuario`, `password`, `telefono`, `rolUsuario`, `estado`) VALUES
	(1, 'ivan', 'gomez', 'igomez', 'Df6NrFFv0YfBge+jU4F0ZA==$WPApCRE21dQmYSefpJeiiVTA+N5kqLC+xPIiubhw9c0=', '4534534534', 'Usuario', 0),
	(2, 'Mario', 'Cabral', 'mcabral', 'I5SjB9sFTD7g3gLehBpm1Q==$YWIdIh1t9VTE7SaZfeBQUsSDc60I8fifcBuqjJvqbgc=', '3453', 'Administrador', 0),
	(5, 'justo', 'graciano', 'jgraciano', 'TIVz/dTbNt8t1vmggDVuuw==$tLHSlr/Pq9bWYK9J1GZs5LGfEcWl8MTc2/oo68CJEdI=', '56451564', 'Usuario', 0),
	(6, 'Fernando', 'López', 'flopez', '9uifyFXL2A0GraPKS2u9ug==$YMJVs03EQMNUyQzZRIMI6U8wGEzNCOXW7I09z/RMU+8=', '8098978888', 'Administrador', 0),
	(7, 'marcos', 'soliz', 'msoliz', 'p22eMeqx1p4qquiLHr+JcA==$b1AQu666GfR+jR6RYBYYdz84KdIopwOfJEFBMc8s5rg=', '45646456', 'Administrador', 0),
	(8, 'admin', 'admin', 'admin', 'JQujVSlhPs1gyvFTmn/6JQ==$ajRio622Rd0N1S6xtQ/Q07rhlxISecF8Ntg//hjn71k=', '0000000000', 'Administrador', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
