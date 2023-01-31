CREATE DATABASE  IF NOT EXISTS `comicshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `comicshop`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: comicshop
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articolo`
--

DROP TABLE IF EXISTS `articolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articolo` (
                            `id` int NOT NULL,
                            `nome` varchar(45) NOT NULL,
                            `prezzo` decimal(5,2) NOT NULL,
                            `saldo` int NOT NULL,
                            `dataDiUscita` date DEFAULT NULL,
                            `voto` float DEFAULT '0',
                            `descrizione` varchar(145) DEFAULT NULL,
                            `peso` float DEFAULT NULL,
                            `quantita` int DEFAULT NULL,
                            `mostra` tinyint NOT NULL DEFAULT '0',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contenuto`
--

DROP TABLE IF EXISTS `contenuto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contenuto` (
                             `ordine` bigint NOT NULL,
                             `articolo` int NOT NULL,
                             `iva` int DEFAULT NULL,
                             `prezzoAcquisto` float DEFAULT NULL,
                             `nomeArt` varchar(45) DEFAULT NULL,
                             `numArtAcq` int DEFAULT NULL,
                             PRIMARY KEY (`ordine`,`articolo`),
                             KEY `articolo_idx` (`articolo`),
                             CONSTRAINT `articolo_cnt` FOREIGN KEY (`articolo`) REFERENCES `articolo` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fatturazione`
--

DROP TABLE IF EXISTS `fatturazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fatturazione` (
                                `utente` varchar(30) NOT NULL,
                                `metododipagamento` int NOT NULL,
                                PRIMARY KEY (`utente`,`metododipagamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `foto`
--

DROP TABLE IF EXISTS `foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto` (
                        `foto` mediumblob NOT NULL,
                        `id` int NOT NULL,
                        `articolo` int DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `articolo_idx` (`articolo`),
                        CONSTRAINT `articolo` FOREIGN KEY (`articolo`) REFERENCES `articolo` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `fumetto`
--

DROP TABLE IF EXISTS `fumetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fumetto` (
                           `lingua` int DEFAULT NULL,
                           `autore` varchar(45) DEFAULT NULL,
                           `numPagine` int DEFAULT NULL,
                           `tipo` varchar(45) DEFAULT NULL,
                           `genere` varchar(45) DEFAULT NULL,
                           `editore` varchar(45) DEFAULT NULL,
                           `articolo` int NOT NULL,
                           PRIMARY KEY (`articolo`),
                           CONSTRAINT `articolo_fum` FOREIGN KEY (`articolo`) REFERENCES `articolo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gadget`
--

DROP TABLE IF EXISTS `gadget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gadget` (
                          `produttore` varchar(25) DEFAULT NULL,
                          `batterie` tinyint DEFAULT NULL,
                          `materiale` varchar(45) DEFAULT NULL,
                          `articolo` int NOT NULL,
                          PRIMARY KEY (`articolo`),
                          CONSTRAINT `aritocolo_gadg` FOREIGN KEY (`articolo`) REFERENCES `articolo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gioco`
--

DROP TABLE IF EXISTS `gioco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gioco` (
                         `lingua` varchar(15) NOT NULL,
                         `assemblaggio` tinyint DEFAULT NULL,
                         `etaConsigliata` int DEFAULT NULL,
                         `casaEditrice` varchar(45) DEFAULT NULL,
                         `articolo` int NOT NULL,
                         PRIMARY KEY (`articolo`),
                         CONSTRAINT `articolo_gio` FOREIGN KEY (`articolo`) REFERENCES `articolo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `indirizzo`
--

DROP TABLE IF EXISTS `indirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzo` (
                             `via` varchar(80) NOT NULL,
                             `utente` varchar(30) NOT NULL,
                             `cap` int NOT NULL,
                             `numeroCivico` int NOT NULL,
                             PRIMARY KEY (`via`,`cap`,`utente`,`numeroCivico`),
                             KEY `numero_civico_idx` (`numeroCivico`),
                             KEY `cap_idx` (`cap`),
                             KEY `via_idx` (`via`,`numeroCivico`,`cap`),
                             CONSTRAINT `via` FOREIGN KEY (`via`, `numeroCivico`, `cap`) REFERENCES `indirizzospedizione` (`via`, `numeroCivico`, `cap`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `indirizzospedizione`
--

DROP TABLE IF EXISTS `indirizzospedizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzospedizione` (
                                       `via` varchar(80) NOT NULL,
                                       `numeroCivico` int NOT NULL,
                                       `cap` int NOT NULL,
                                       `citta` varchar(45) NOT NULL,
                                       `provincia` varchar(45) NOT NULL,
                                       PRIMARY KEY (`via`,`numeroCivico`,`cap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `metododipagamento`
--

DROP TABLE IF EXISTS `metododipagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metododipagamento` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `tipo` int NOT NULL,
                                     `titolare` varchar(45) NOT NULL,
                                     `scadenza` date NOT NULL,
                                     `predefinito` tinyint(1) NOT NULL,
                                     `indirizzoFatturazione` varchar(45) NOT NULL,
                                     `numero` bigint NOT NULL,
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
                          `numeroOrdine` bigint NOT NULL AUTO_INCREMENT,
                          `costoTotale` float NOT NULL,
                          `indirizzospedizione` varchar(100) DEFAULT NULL,
                          `dataSpedizione` date DEFAULT NULL,
                          `metododipagamento` varchar(70) DEFAULT NULL,
                          `username` varchar(45) NOT NULL,
                          `dataOrdine` date NOT NULL,
                          PRIMARY KEY (`numeroOrdine`),
                          KEY `usr_ord_idx` (`username`),
                          CONSTRAINT `usr_ord` FOREIGN KEY (`username`) REFERENCES `utente` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
                              `voto` int DEFAULT NULL,
                              `testo recensione` varchar(500) DEFAULT NULL,
                              `dataPubblicazione` date DEFAULT NULL,
                              `utente` int NOT NULL,
                              `articolo` varchar(45) NOT NULL,
                              PRIMARY KEY (`utente`,`articolo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
                          `username` varchar(30) NOT NULL,
                          `nome` varchar(45) DEFAULT NULL,
                          `cognome` varchar(45) DEFAULT NULL,
                          `email` varchar(45) DEFAULT NULL,
                          `password` varchar(45) DEFAULT NULL,
                          `numTelefono` varchar(45) DEFAULT NULL,
                          `paeseResidenza` varchar(45) DEFAULT NULL,
                          `dataNascita` date DEFAULT NULL,
                          `admin` tinyint DEFAULT '0',
                          PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-31 16:32:16
