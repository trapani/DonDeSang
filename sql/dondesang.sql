-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mar 03 Juin 2014 à 09:41
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
  `idAdresse` int(11) NOT NULL AUTO_INCREMENT,
  `Rue` varchar(100) NOT NULL,
  `Numero` varchar(5) NOT NULL,
  `idVille` int(11) NOT NULL,
  `idTransport` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAdresse`),
  KEY `FKse_trouve_IND` (`idVille`),
  KEY `FKa_une_adresse_de_livraison_IND` (`idTransport`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `adresse`
--

INSERT INTO `adresse` (`idAdresse`, `Rue`, `Numero`, `idVille`, `idTransport`) VALUES
(6, 'rue de Namur', '93', 1, NULL),
(7, 'rue des paquerettes', '56', 2, NULL),
(8, 'avenue leopold 3', '52', 2, NULL),
(9, 'rue saint-nicolas', '85', 3, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `analyse`
--

CREATE TABLE IF NOT EXISTS `analyse` (
  `idAnalyse` int(11) NOT NULL AUTO_INCREMENT,
  `idGrp` int(11) NOT NULL,
  `ID_Det` int(11) NOT NULL,
  PRIMARY KEY (`idAnalyse`),
  KEY `FKconfirme_grp_IND` (`idGrp`),
  KEY `FKdetaille_IND` (`ID_Det`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `collation`
--

CREATE TABLE IF NOT EXISTS `collation` (
  `idCollecte` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idCollecte`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `collation`
--

INSERT INTO `collation` (`idCollecte`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `collecte`
--

CREATE TABLE IF NOT EXISTS `collecte` (
  `idCollecte` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `collecte`
--

INSERT INTO `collecte` (`idCollecte`, `DateCollecte`, `TypeCollecte`, `idPoche`, `idInfirmiere`, `idMedecin`, `ID_Sto`) VALUES
(1, '2014-06-01', 'Sang', 6, 1, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `conducteur`
--

CREATE TABLE IF NOT EXISTS `conducteur` (
  `idConducteur` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `EntreprisePrestataire` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idConducteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `detailsanalyse`
--

CREATE TABLE IF NOT EXISTS `detailsanalyse` (
  `ID_Det` int(11) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `donneur`
--

CREATE TABLE IF NOT EXISTS `donneur` (
  `idDonneur` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `NumNational` varchar(11) NOT NULL,
  `DateNaissance` date NOT NULL,
  `Sexe` char(1) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  `gsm` varchar(30) DEFAULT NULL,
  `idAdresse` int(11) NOT NULL,
  `idGrp` int(11) NOT NULL,
  PRIMARY KEY (`idDonneur`),
  KEY `FKhabite_IND` (`idAdresse`),
  KEY `FKest_du_groupe_IND` (`idGrp`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `donneur`
--

INSERT INTO `donneur` (`idDonneur`, `Nom`, `Prenom`, `NumNational`, `DateNaissance`, `Sexe`, `email`, `tel`, `gsm`, `idAdresse`, `idGrp`) VALUES
(1, 'trapani', 'tony', '90070929912', '1990-07-09', 'H', NULL, NULL, NULL, 6, 1),
(2, 'Robert', 'charles', '82020812356', '1982-02-08', 'H', NULL, NULL, NULL, 7, 6),
(3, 'Robert', 'Nicolas', '88050845612', '1988-05-08', 'H', NULL, NULL, NULL, 8, 3),
(4, 'Letor', 'Manuel', '89111536985', '1989-11-15', 'H', NULL, NULL, NULL, 9, 7);

-- --------------------------------------------------------

--
-- Structure de la table `formulaire`
--

CREATE TABLE IF NOT EXISTS `formulaire` (
  `idFormulaire` int(11) NOT NULL AUTO_INCREMENT,
  `idDonneur` int(11) NOT NULL,
  `File` tinyblob NOT NULL,
  PRIMARY KEY (`idFormulaire`),
  KEY `FKa_complete_IND` (`idDonneur`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `formulaire`
--

INSERT INTO `formulaire` (`idFormulaire`, `idDonneur`, `File`) VALUES
(1, 4, '');

-- --------------------------------------------------------

--
-- Structure de la table `groupesanguin`
--

CREATE TABLE IF NOT EXISTS `groupesanguin` (
  `idGrp` int(11) NOT NULL AUTO_INCREMENT,
  `Grp` varchar(2) NOT NULL,
  `Rhesus` char(1) NOT NULL,
  PRIMARY KEY (`idGrp`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `groupesanguin`
--

INSERT INTO `groupesanguin` (`idGrp`, `Grp`, `Rhesus`) VALUES
(1, 'A', '+'),
(2, 'A', '-'),
(3, 'B', '+'),
(4, 'B', '-'),
(5, 'AB', '+'),
(6, 'AB', '-'),
(7, 'O', '+'),
(8, 'O', '-');

-- --------------------------------------------------------

--
-- Structure de la table `infirmiere`
--

CREATE TABLE IF NOT EXISTS `infirmiere` (
  `idInfirmiere` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`idInfirmiere`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `infirmiere`
--

INSERT INTO `infirmiere` (`idInfirmiere`, `Nom`, `Prenom`) VALUES
(1, 'Delarue', 'Martine'),
(2, 'Baudouin', 'Nicole'),
(3, 'Duchateau', 'Pamela');

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE IF NOT EXISTS `medecin` (
  `idMedecin` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`idMedecin`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `medecin`
--

INSERT INTO `medecin` (`idMedecin`, `Nom`, `Prenom`) VALUES
(1, 'Dupont', 'Franck'),
(2, 'Laplace', 'Benoît'),
(3, 'Durand', 'Giovanni');

-- --------------------------------------------------------

--
-- Structure de la table `poche`
--

CREATE TABLE IF NOT EXISTS `poche` (
  `idPoche` int(11) NOT NULL AUTO_INCREMENT,
  `autotransfusion` tinyint(1) NOT NULL,
  `utilise` tinyint(1) NOT NULL,
  `etat` tinyint(1) DEFAULT NULL,
  `idAnalyse` int(11) DEFAULT NULL,
  `idDonneur` int(11) NOT NULL,
  PRIMARY KEY (`idPoche`),
  UNIQUE KEY `FKnecessite_ID` (`idAnalyse`),
  KEY `FKpreleve_sur_IND` (`idDonneur`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `poche`
--

INSERT INTO `poche` (`idPoche`, `autotransfusion`, `utilise`, `etat`, `idAnalyse`, `idDonneur`) VALUES
(6, 0, 0, 0, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE IF NOT EXISTS `stock` (
  `ID_Sto` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID_Sto`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `stock`
--

INSERT INTO `stock` (`ID_Sto`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `transport`
--

CREATE TABLE IF NOT EXISTS `transport` (
  `idTransport` int(11) NOT NULL AUTO_INCREMENT,
  `DateTransport` date NOT NULL,
  `HeureReception` date NOT NULL,
  `idConducteur` int(11) NOT NULL,
  `ID_Sto` int(11) NOT NULL,
  PRIMARY KEY (`idTransport`),
  KEY `FKeffectue_par_IND` (`idConducteur`),
  KEY `FKreapprovisionne_via_IND` (`ID_Sto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE IF NOT EXISTS `ville` (
  `idVille` int(11) NOT NULL AUTO_INCREMENT,
  `NomVille` varchar(100) NOT NULL,
  `CP` varchar(10) NOT NULL,
  PRIMARY KEY (`idVille`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `ville`
--

INSERT INTO `ville` (`idVille`, `NomVille`, `CP`) VALUES
(1, 'Ressaix', '7134'),
(2, 'Peronnes', '7134'),
(3, 'Houdeng-Goegnies', '7110');

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
