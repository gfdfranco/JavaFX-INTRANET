-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-05-2015 a las 02:54:13
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
  `CLAVE` varchar(25) NOT NULL,
  `NOMBRE` varchar(25) NOT NULL,
  `EMAIL` varchar(25) NOT NULL,
  `TEL` varchar(25) NOT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  PRIMARY KEY (`CLAVE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE IF NOT EXISTS `alumno` (
  `MATRICULA` varchar(25) NOT NULL,
  `NOMBRE` varchar(25) NOT NULL,
  `TEL` varchar(25) NOT NULL,
  `DIRECCION` varchar(25) NOT NULL,
  `AO_INGRESO` varchar(25) NOT NULL,
  `CARRERA` enum('ITI','ITEM','ITMA','ISTI','LAG','LMKT') NOT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  PRIMARY KEY (`MATRICULA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `CLAVE` varchar(25) NOT NULL,
  `NOMBRE` varchar(25) NOT NULL,
  `CLAVE_PROFESOR` varchar(25) NOT NULL,
  `AULA` varchar(25) NOT NULL,
  `HORA` varchar(25) NOT NULL,
  `CARRERA` enum('ITI','ITEM','ITMA','ISTI','LAG','LMKT') NOT NULL,
  PRIMARY KEY (`CLAVE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE IF NOT EXISTS `profesor` (
  `CLAVE` varchar(25) NOT NULL,
  `NOMBRE` varchar(25) NOT NULL,
  `RFC` varchar(25) NOT NULL,
  `TEL` varchar(25) NOT NULL,
  `DIRECCION` varchar(25) NOT NULL,
  `EMAIL` varchar(25) NOT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  PRIMARY KEY (`CLAVE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
