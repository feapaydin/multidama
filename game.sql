-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 17 Ara 2016, 16:54:35
-- Sunucu sürümü: 5.7.9
-- PHP Sürümü: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `multidama`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `game_grids`
--

DROP TABLE IF EXISTS `game_grids`;
CREATE TABLE IF NOT EXISTS `game_grids` (
  `g_id` int(11) NOT NULL AUTO_INCREMENT,
  `g_durum` int(11) NOT NULL,
  `g_owner` int(11) NOT NULL,
  `g_posX` int(11) NOT NULL,
  `g_posY` int(11) NOT NULL,
  `g_roomId` int(11) NOT NULL,
  `g_inGameId` int(11) NOT NULL,
  PRIMARY KEY (`g_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2377 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `game_players`
--

DROP TABLE IF EXISTS `game_players`;
CREATE TABLE IF NOT EXISTS `game_players` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_ad` varchar(512) COLLATE utf8_turkish_ci NOT NULL,
  `p_sifre` varchar(512) COLLATE utf8_turkish_ci NOT NULL,
  `p_lastOnline` varchar(50) COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `game_rooms`
--

DROP TABLE IF EXISTS `game_rooms`;
CREATE TABLE IF NOT EXISTS `game_rooms` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_ad` varchar(512) COLLATE utf8_turkish_ci NOT NULL,
  `r_p1` int(11) NOT NULL DEFAULT '-1',
  `r_p2` int(11) NOT NULL DEFAULT '-1',
  `r_pass` varchar(512) COLLATE utf8_turkish_ci NOT NULL,
  `r_turn` int(11) NOT NULL,
  `r_winner` int(11) NOT NULL DEFAULT '-1',
  `r_p1_tas` int(11) NOT NULL DEFAULT '16',
  `r_p2_tas` int(11) NOT NULL DEFAULT '16',
  `r_lastMove` int(11) NOT NULL DEFAULT '-1',
  `r_lastMoveFrom` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`r_id`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
