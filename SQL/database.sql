CREATE TABLE Utilisateur (
  email VARCHAR(100) PRIMARY KEY NOT NULL,
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  adresse VARCHAR(255) NOT NULL
);
CREATE TABLE Produit (
  idProduit INT GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT by 1) PRIMARY KEY NOT NULL,
  nomProduit VARCHAR(50) NOT NULL,
  prixRevient FLOAT NOT NULL,
  stock INT NOT NULL,
  email VARCHAR(50) NOT NULL,
  FOREIGN KEY (email) REFERENCES Utilisateur(email),
  CONSTRAINT prixPositif CHECK (prixRevient >= 0),
  CONSTRAINT stockPositif CHECK (stock >= 0)
);
CREATE TABLE TypeVente (
  idTypeV INT GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT by 1) PRIMARY KEY NOT NULL,
  typeV VARCHAR(11) DEFAULT 'montante',
  repetitivite INT DEFAULT 1,
  revocable INT DEFAULT 0,
  dateV TIMESTAMP DEFAULT NULL
);
CREATE TABLE Categorie (
  nomCat VARCHAR(50) PRIMARY KEY NOT NULL,
  description VARCHAR(255) DEFAULT ' '
);
CREATE TABLE CategorieProduit (
  idProduit INT NOT NULL,
  nomCat VARCHAR(50) NOT NULL,
  FOREIGN KEY (idProduit) REFERENCES Produit(idProduit),
  FOREIGN KEY (nomCat) REFERENCES Categorie(nomCat),
  PRIMARY KEY(idProduit, nomCat)
);
CREATE TABLE Salle (
  idSalle INT GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT by 1) PRIMARY KEY NOT NULL,
  idTypeV INT NOT NULL,
  nomCat VARCHAR(30) NOT NULL,
  FOREIGN KEY (idTypeV) REFERENCES TypeVente(idTypeV),
  FOREIGN KEY (nomCat) REFERENCES Categorie(nomCat)
);
CREATE TABLE Vente (
  idVente INT GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT by 1) PRIMARY KEY NOT NULL,
  idSalle INT NOT NULL,
  idProduit INT NOT NULL,
  prixDep FLOAT NOT NULL,
  FOREIGN KEY (idSalle) REFERENCES Salle(idSalle),
  FOREIGN KEY (idProduit) REFERENCES Produit(idProduit),
  CONSTRAINT prixDepPositif CHECK (prixDep >= 0)
);
CREATE TABLE Caracteristique (
  nomCar VARCHAR(50) PRIMARY KEY NOT NULL,
  valeurCar VARCHAR(255) NOT NULL,
  idProduit INT NOT NULL,
  FOREIGN KEY (idProduit) REFERENCES Produit(idProduit)
);
CREATE TABLE Enchere (
  idEnchere INT GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT by 1) PRIMARY KEY NOT NULL,
  prixAchat FLOAT NOT NULL,
  dateE TIMESTAMP NOT NULL,
  qttProduit INT NOT NULL,
  validite INT DEFAULT 0,
  CONSTRAINT prixAchatPositif CHECK (prixAchat >= 0),
  CONSTRAINT qttPositif CHECK (qttProduit > 0)
);
CREATE TABLE Encherir (
  idEnchere INT NOT NULL,
  email VARCHAR(100) NOT NULL,
  idVente INT NOT NULL,
  FOREIGN KEY (idVente) REFERENCES Vente(idVente),
  FOREIGN KEY (email) REFERENCES Utilisateur(email),
  FOREIGN KEY (idEnchere) REFERENCES Enchere(idEnchere),
  PRIMARY KEY(idEnchere, email, idVente)
);
