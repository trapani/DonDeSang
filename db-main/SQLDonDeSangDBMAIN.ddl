-- *********************************************
-- * Standard SQL generation                   
-- *--------------------------------------------
-- * DB-MAIN version: 9.1.6              
-- * Generator date: Feb 25 2013              
-- * Generation date: Tue Jun 03 14:01:18 2014 
-- * LUN file: C:\Users\Tony\Documents\Cours 2013 - 2014\Mons formation\Analyse et conceptions\Don_de_sang\DonDeSang.lun 
-- * Schema: mpd 4 DonDeSang/SQL 
-- ********************************************* 


-- Database Section
-- ________________ 

create database mpd 4 DonDeSang;


-- DBSpace Section
-- _______________


-- Tables Section
-- _____________ 

create table Adresse (
     Rue varchar(50) not null,
     Numero numeric(6) not null,
     idAdresse char(1) not null,
     idVille char(1) not null,
     idTransport char(1) not null,
     constraint ID_Adresse_ID primary key (idAdresse));

create table Analyse (
     idAnalyse char(1) not null,
     idPoche char(1) not null,
     idGrp char(1) not null,
     ID_Det numeric(10) not null,
     constraint ID_Analyse_ID primary key (idAnalyse),
     constraint SID_Analy_Poche_ID unique (idPoche));

create table Collation (
     idCollecte char(1) not null,
     constraint ID_Colla_Colle_ID primary key (idCollecte));

create table Collecte (
     DateCollecte date not null,
     TypeCollecte varchar(30) not null,
     idCollecte char(1) not null,
     idInfirmiere char(1) not null,
     idMedecin char(1) not null,
     ID_Sto numeric(10) not null,
     constraint ID_Collecte_ID primary key (idCollecte));

create table Conducteur (
     Nom varchar(30) not null,
     Prenom varchar(30) not null,
     EntreprisePrestataire varchar(50) not null,
     idConducteur char(1) not null,
     constraint ID_Conducteur_ID primary key (idConducteur));

create table DetailsAnalyse (
     ID_Det -- Sequence attribute not implemented -- not null,
     Hemoglobine numeric(3) not null,
     Hematocrite numeric(3) not null,
     Erythrocyte numeric(3) not null,
     Leucocyte numeric(3) not null,
     Lymphocyte numeric(3) not null,
     Plaquette numeric(3) not null,
     Fer numeric(3) not null,
     Sodium numeric(3) not null,
     Potassium numeric(3) not null,
     Calcium numeric(3) not null,
     constraint ID_DetailsAnalyse_ID primary key (ID_Det));

create table Donneur (
     Nom varchar(30) not null,
     Prenom varchar(30) not null,
     Datenaissance date not null,
     Sexe char(1) not null,
     email varchar(50) not null,
     tel varchar(20) not null,
     gsm varchar(12) not null,
     idDonneur char(1) not null,
     idAdresse char(1) not null,
     idGrp char(1) not null,
     constraint ID_Donneur_ID primary key (idDonneur));

create table Formulaire (
     idFormulaire char(1) not null,
     file varchar(1) not null,
     idDonneur char(1) not null,
     constraint ID_Formulaire_ID primary key (idFormulaire));

create table GroupeSanguin (
     Grp varchar(2) not null,
     Rhesus char(1) not null,
     idGrp char(1) not null,
     constraint ID_GroupeSanguin_ID primary key (idGrp));

create table Infirmiere (
     Nom varchar(30) not null,
     Prenom varchar(30) not null,
     idInfirmiere char(1) not null,
     constraint ID_Infirmiere_ID primary key (idInfirmiere));

create table Medecin (
     Nom varchar(30) not null,
     Prenom varchar(30) not null,
     idMedecin char(1) not null,
     constraint ID_Medecin_ID primary key (idMedecin));

create table Poche (
     idPoche char(1) not null,
     idCollecte char(1) not null,
     utilise char not null,
     etat char not null,
     autotransfusion char not null,
     idDonneur char(1) not null,
     constraint ID_Poche_ID primary key (idPoche),
     constraint SID_Poche_Colle_ID unique (idCollecte));

create table Stock (
     ID_Sto -- Sequence attribute not implemented -- not null,
     constraint ID_Stock_ID primary key (ID_Sto));

create table Transport (
     DateTransport date not null,
     HeureReception date not null,
     idTransport char(1) not null,
     idConducteur char(1) not null,
     ID_Sto numeric(10) not null,
     constraint ID_Transport_ID primary key (idTransport));

create table Ville (
     NomVille varchar(50) not null,
     CP numeric(5) not null,
     idVille char(1) not null,
     constraint ID_Ville_ID primary key (idVille));


-- Constraints Section
-- ___________________ 

alter table Adresse add constraint ID_Adresse_CHK
     check(exists(select * from Donneur
                  where Donneur.idAdresse = idAdresse)); 

alter table Adresse add constraint EQU_Adres_Ville_FK
     foreign key (idVille)
     references Ville;

alter table Adresse add constraint EQU_Adres_Trans_FK
     foreign key (idTransport)
     references Transport;

alter table Analyse add constraint SID_Analy_Poche_FK
     foreign key (idPoche)
     references Poche;

alter table Analyse add constraint EQU_Analy_Group_FK
     foreign key (idGrp)
     references GroupeSanguin;

alter table Analyse add constraint EQU_Analy_Detai_FK
     foreign key (ID_Det)
     references DetailsAnalyse;

alter table Collation add constraint ID_Colla_Colle_FK
     foreign key (idCollecte)
     references Collecte;

alter table Collecte add constraint ID_Collecte_CHK
     check(exists(select * from Poche
                  where Poche.idCollecte = idCollecte)); 

alter table Collecte add constraint ID_Collecte_CHK
     check(exists(select * from Collation
                  where Collation.idCollecte = idCollecte)); 

alter table Collecte add constraint REF_Colle_Infir_FK
     foreign key (idInfirmiere)
     references Infirmiere;

alter table Collecte add constraint REF_Colle_Medec_FK
     foreign key (idMedecin)
     references Medecin;

alter table Collecte add constraint EQU_Colle_Stock_FK
     foreign key (ID_Sto)
     references Stock;

alter table Conducteur add constraint ID_Conducteur_CHK
     check(exists(select * from Transport
                  where Transport.idConducteur = idConducteur)); 

alter table DetailsAnalyse add constraint ID_DetailsAnalyse_CHK
     check(exists(select * from Analyse
                  where Analyse.ID_Det = ID_Det)); 

alter table Donneur add constraint ID_Donneur_CHK
     check(exists(select * from Formulaire
                  where Formulaire.idDonneur = idDonneur)); 

alter table Donneur add constraint EQU_Donne_Adres_FK
     foreign key (idAdresse)
     references Adresse;

alter table Donneur add constraint EQU_Donne_Group_FK
     foreign key (idGrp)
     references GroupeSanguin;

alter table Formulaire add constraint EQU_Formu_Donne_FK
     foreign key (idDonneur)
     references Donneur;

alter table GroupeSanguin add constraint ID_GroupeSanguin_CHK
     check(exists(select * from Analyse
                  where Analyse.idGrp = idGrp)); 

alter table GroupeSanguin add constraint ID_GroupeSanguin_CHK
     check(exists(select * from Donneur
                  where Donneur.idGrp = idGrp)); 

alter table Poche add constraint ID_Poche_CHK
     check(exists(select * from Analyse
                  where Analyse.idPoche = idPoche)); 

alter table Poche add constraint REF_Poche_Donne_FK
     foreign key (idDonneur)
     references Donneur;

alter table Poche add constraint SID_Poche_Colle_FK
     foreign key (idCollecte)
     references Collecte;

alter table Stock add constraint ID_Stock_CHK
     check(exists(select * from Collecte
                  where Collecte.ID_Sto = ID_Sto)); 

alter table Transport add constraint ID_Transport_CHK
     check(exists(select * from Adresse
                  where Adresse.idTransport = idTransport)); 

alter table Transport add constraint EQU_Trans_Condu_FK
     foreign key (idConducteur)
     references Conducteur;

alter table Transport add constraint REF_Trans_Stock_FK
     foreign key (ID_Sto)
     references Stock;

alter table Ville add constraint ID_Ville_CHK
     check(exists(select * from Adresse
                  where Adresse.idVille = idVille)); 


-- Index Section
-- _____________ 

create unique index ID_Adresse_IND
     on Adresse (idAdresse);

create index EQU_Adres_Ville_IND
     on Adresse (idVille);

create index EQU_Adres_Trans_IND
     on Adresse (idTransport);

create unique index ID_Analyse_IND
     on Analyse (idAnalyse);

create unique index SID_Analy_Poche_IND
     on Analyse (idPoche);

create index EQU_Analy_Group_IND
     on Analyse (idGrp);

create index EQU_Analy_Detai_IND
     on Analyse (ID_Det);

create unique index ID_Colla_Colle_IND
     on Collation (idCollecte);

create unique index ID_Collecte_IND
     on Collecte (idCollecte);

create index REF_Colle_Infir_IND
     on Collecte (idInfirmiere);

create index REF_Colle_Medec_IND
     on Collecte (idMedecin);

create index EQU_Colle_Stock_IND
     on Collecte (ID_Sto);

create unique index ID_Conducteur_IND
     on Conducteur (idConducteur);

create unique index ID_DetailsAnalyse_IND
     on DetailsAnalyse (ID_Det);

create unique index ID_Donneur_IND
     on Donneur (idDonneur);

create index EQU_Donne_Adres_IND
     on Donneur (idAdresse);

create index EQU_Donne_Group_IND
     on Donneur (idGrp);

create unique index ID_Formulaire_IND
     on Formulaire (idFormulaire);

create index EQU_Formu_Donne_IND
     on Formulaire (idDonneur);

create unique index ID_GroupeSanguin_IND
     on GroupeSanguin (idGrp);

create unique index ID_Infirmiere_IND
     on Infirmiere (idInfirmiere);

create unique index ID_Medecin_IND
     on Medecin (idMedecin);

create unique index ID_Poche_IND
     on Poche (idPoche);

create index REF_Poche_Donne_IND
     on Poche (idDonneur);

create unique index SID_Poche_Colle_IND
     on Poche (idCollecte);

create unique index ID_Stock_IND
     on Stock (ID_Sto);

create unique index ID_Transport_IND
     on Transport (idTransport);

create index EQU_Trans_Condu_IND
     on Transport (idConducteur);

create index REF_Trans_Stock_IND
     on Transport (ID_Sto);

create unique index ID_Ville_IND
     on Ville (idVille);

