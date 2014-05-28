-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 28 Mai 2014 à 09:48
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `dondesang`
--
CREATE DATABASE IF NOT EXISTS `dondesang` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dondesang`;

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE IF NOT EXISTS `adresse` (
  `Rue` varchar(100) NOT NULL,
  `Numero` varchar(5) NOT NULL,
  `idAdresse` int(11) NOT NULL,
  `idVille` int(11) NOT NULL,
  `idTransport` int(11) NOT NULL,
  PRIMARY KEY (`idAdresse`),
  KEY `FKse_trouve_IND` (`idVille`),
  KEY `FKa_une_adresse_de_livraison_IND` (`idTransport`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `analyse`
--

CREATE TABLE IF NOT EXISTS `analyse` (
  `idAnalyse` int(11) NOT NULL,
  `idGrp` int(11) NOT NULL,
  `ID_Det` int(11) NOT NULL,
  PRIMARY KEY (`idAnalyse`),
  KEY `FKconfirme_grp_IND` (`idGrp`),
  KEY `FKdetaille_IND` (`ID_Det`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `collation`
--

CREATE TABLE IF NOT EXISTS `collation` (
  `idCollecte` int(11) NOT NULL,
  PRIMARY KEY (`idCollecte`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `collecte`
--

CREATE TABLE IF NOT EXISTS `collecte` (
  `idCollecte` int(11) NOT NULL,
  `DateCollecte` date NOT NULL,
  `TypeCollecte` varchar(30) NOT NULL,
  `idPoche` int(11) NOT NULL,
  `idInfirmiere` int(11) NOT NULL,
  `idMedecin` int(11) NOT NULL,
  `ID_Sto` int(11) NOT NULL,
  PRIMARY KEY (`idCollecte`),
  UNIQUE KEY `FKpermet_d_obtenir_ID` (`idPoche`),
  KEY `FKrealise_par_IND` (`idInfirmiere`),
  KEY `FKaccorde_par_IND` (`idMedecin`),
  KEY `FKest_placee_IND` (`ID_Sto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `conducteur`
--

CREATE TABLE IF NOT EXISTS `conducteur` (
  `idConducteur` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `EntreprisePrestataire` varchar(50) NOT NULL,
  PRIMARY KEY (`idConducteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `detailsanalyse`
--

CREATE TABLE IF NOT EXISTS `detailsanalyse` (
  `ID_Det` int(11) NOT NULL,
  `Hemoglobine` int(11) NOT NULL,
  `Hematocrite` int(11) NOT NULL,
  `Erythrocyte` int(11) NOT NULL,
  `Leucocyte` int(11) NOT NULL,
  `Lymphocyte` int(11) NOT NULL,
  `Plaquette` int(11) NOT NULL,
  `Fer` int(11) NOT NULL,
  `Sodium` int(11) NOT NULL,
  `Potassium` int(11) NOT NULL,
  `Calcium` int(11) NOT NULL,
  PRIMARY KEY (`ID_Det`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `donneur`
--

CREATE TABLE IF NOT EXISTS `donneur` (
  `idDonneur` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Age` int(11) NOT NULL,
  `Sexe` char(1) NOT NULL,
  `idAdresse` int(11) NOT NULL,
  `idGrp` int(11) NOT NULL,
  PRIMARY KEY (`idDonneur`),
  KEY `FKhabite_IND` (`idAdresse`),
  KEY `FKest_du_groupe_IND` (`idGrp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `formulaire`
--

CREATE TABLE IF NOT EXISTS `formulaire` (
  `idFormulaire` int(11) NOT NULL,
  `idDonneur` int(11) NOT NULL,
  `tatouage` varchar(250) NOT NULL,
  `piercing` varchar(250) NOT NULL,
  `operation` varchar(250) NOT NULL,
  `MaladieRecente` varchar(250) NOT NULL,
  PRIMARY KEY (`idFormulaire`),
  KEY `FKa_complete_IND` (`idDonneur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `groupesanguin`
--

CREATE TABLE IF NOT EXISTS `groupesanguin` (
  `idGrp` int(11) NOT NULL,
  `Grp` varchar(2) NOT NULL,
  `Rhesus` char(1) NOT NULL,
  PRIMARY KEY (`idGrp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `infirmiere`
--

CREATE TABLE IF NOT EXISTS `infirmiere` (
  `idInfirmiere` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`idInfirmiere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE IF NOT EXISTS `medecin` (
  `idMedecin` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`idMedecin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `poche`
--

CREATE TABLE IF NOT EXISTS `poche` (
  `idPoche` int(11) NOT NULL,
  `idAnalyse` int(11) NOT NULL,
  `idDonneur` int(11) NOT NULL,
  PRIMARY KEY (`idPoche`),
  UNIQUE KEY `FKnecessite_ID` (`idAnalyse`),
  KEY `FKpreleve_sur_IND` (`idDonneur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE IF NOT EXISTS `stock` (
  `ID_Sto` int(11) NOT NULL,
  PRIMARY KEY (`ID_Sto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `transport`
--

CREATE TABLE IF NOT EXISTS `transport` (
  `idTransport` int(11) NOT NULL,
  `DateTransport` date NOT NULL,
  `HeureReception` date NOT NULL,
  `idConducteur` int(11) NOT NULL,
  `ID_Sto` int(11) NOT NULL,
  PRIMARY KEY (`idTransport`),
  KEY `FKeffectue_par_IND` (`idConducteur`),
  KEY `FKreapprovisionne_via_IND` (`ID_Sto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE IF NOT EXISTS `ville` (
  `idVille` int(11) NOT NULL,
  `NomVille` varchar(100) NOT NULL,
  `CP` varchar(10) NOT NULL,
  PRIMARY KEY (`idVille`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `FKa_une_adresse_de_livraison_FK` FOREIGN KEY (`idTransport`) REFERENCES `transport` (`idTransport`),
  ADD CONSTRAINT `FKse_trouve_FK` FOREIGN KEY (`idVille`) REFERENCES `ville` (`idVille`);

--
-- Contraintes pour la table `analyse`
--
ALTER TABLE `analyse`
  ADD CONSTRAINT `FKconfirme_grp_FK` FOREIGN KEY (`idGrp`) REFERENCES `groupesanguin` (`idGrp`),
  ADD CONSTRAINT `FKdetaille_FK` FOREIGN KEY (`ID_Det`) REFERENCES `detailsanalyse` (`ID_Det`);

--
-- Contraintes pour la table `collation`
--
ALTER TABLE `collation`
  ADD CONSTRAINT `FKRecoit_en_compensation_FK` FOREIGN KEY (`idCollecte`) REFERENCES `collecte` (`idCollecte`);

--
-- Contraintes pour la table `collecte`
--
ALTER TABLE `collecte`
  ADD CONSTRAINT `FKaccorde_par_FK` FOREIGN KEY (`idMedecin`) REFERENCES `medecin` (`idMedecin`),
  ADD CONSTRAINT `FKest_placee_FK` FOREIGN KEY (`ID_Sto`) REFERENCES `stock` (`ID_Sto`),
  ADD CONSTRAINT `FKpermet_d_obtenir_FK` FOREIGN KEY (`idPoche`) REFERENCES `poche` (`idPoche`),
  ADD CONSTRAINT `FKrealise_par_FK` FOREIGN KEY (`idInfirmiere`) REFERENCES `infirmiere` (`idInfirmiere`);

--
-- Contraintes pour la table `donneur`
--
ALTER TABLE `donneur`
  ADD CONSTRAINT `FKest_du_groupe_FK` FOREIGN KEY (`idGrp`) REFERENCES `groupesanguin` (`idGrp`),
  ADD CONSTRAINT `FKhabite_FK` FOREIGN KEY (`idAdresse`) REFERENCES `adresse` (`idAdresse`);

--
-- Contraintes pour la table `formulaire`
--
ALTER TABLE `formulaire`
  ADD CONSTRAINT `FKa_complete_FK` FOREIGN KEY (`idDonneur`) REFERENCES `donneur` (`idDonneur`);

--
-- Contraintes pour la table `poche`
--
ALTER TABLE `poche`
  ADD CONSTRAINT `FKnecessite_FK` FOREIGN KEY (`idAnalyse`) REFERENCES `analyse` (`idAnalyse`),
  ADD CONSTRAINT `FKpreleve_sur_FK` FOREIGN KEY (`idDonneur`) REFERENCES `donneur` (`idDonneur`);

--
-- Contraintes pour la table `transport`
--
ALTER TABLE `transport`
  ADD CONSTRAINT `FKeffectue_par_FK` FOREIGN KEY (`idConducteur`) REFERENCES `conducteur` (`idConducteur`),
  ADD CONSTRAINT `FKreapprovisionne_via_FK` FOREIGN KEY (`ID_Sto`) REFERENCES `stock` (`ID_Sto`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
