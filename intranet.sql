-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-05-2015 a las 07:02:56
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `intranet`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE IF NOT EXISTS `administrador` (
  `NOMBRE` varchar(25) NOT NULL,
  `CLAVE` varchar(25) NOT NULL,
  `EMAIL` varchar(25) NOT NULL,
  `TEL` varchar(25) NOT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  PRIMARY KEY (`CLAVE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`NOMBRE`, `CLAVE`, `EMAIL`, `TEL`, `PASSWORD`) VALUES
('2', '1', '3', '4', '5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE IF NOT EXISTS `alumno` (
  `NOMBRE` varchar(25) NOT NULL,
  `MATRICULA` varchar(25) NOT NULL,
  `TEL` varchar(25) NOT NULL,
  `DIRECCION` varchar(25) NOT NULL,
  `CARRERA` enum('ITI','ITEM','ITMA','ISTI','LAG','LMKT') NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  PRIMARY KEY (`MATRICULA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`NOMBRE`, `MATRICULA`, `TEL`, `DIRECCION`, `CARRERA`, `EMAIL`, `PASSWORD`) VALUES
('1', '2', '3', '4', 'ITI', '5', '6');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificaciones`
--

CREATE TABLE IF NOT EXISTS `calificaciones` (
  `ID_GPO` varchar(25) NOT NULL,
  `CAL1` varchar(25) NOT NULL,
  `CAL2` varchar(25) NOT NULL,
  `CAL3` varchar(25) NOT NULL,
  `FINAL` varchar(25) NOT NULL,
  `EXTRA` varchar(25) NOT NULL,
  `INASISTENCIAS` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo`
--

CREATE TABLE IF NOT EXISTS `grupo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MATRICULA_ALUM` varchar(25) NOT NULL,
  `CLAVE_MATERIA` varchar(25) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

CREATE TABLE IF NOT EXISTS `materia` (
  `NOMBRE` varchar(25) NOT NULL,
  `CLAVE` varchar(25) NOT NULL,
  `CLAVE_PROFESOR` varchar(25) NOT NULL,
  `AULA` varchar(25) NOT NULL,
  `HORA` varchar(25) NOT NULL,
  `CARRERA` enum('ITI','ITEM','ITMA','ISTI','LAG','LMKT') NOT NULL,
  PRIMARY KEY (`CLAVE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `materia`
--

INSERT INTO `materia` (`NOMBRE`, `CLAVE`, `CLAVE_PROFESOR`, `AULA`, `HORA`, `CARRERA`) VALUES
('1', '2', '3', '4', '5', 'ITI');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE IF NOT EXISTS `profesor` (
  `NOMBRE` varchar(25) NOT NULL,
  `RFC` varchar(25) NOT NULL,
  `TEL` varchar(25) NOT NULL,
  `DIRECCION` varchar(25) NOT NULL,
  `CLAVE` varchar(25) NOT NULL,
  `EMAIL` varchar(25) NOT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  PRIMARY KEY (`CLAVE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`NOMBRE`, `RFC`, `TEL`, `DIRECCION`, `CLAVE`, `EMAIL`, `PASSWORD`) VALUES
('2', '3', '4', '5', '1', '6', '7');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
