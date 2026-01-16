CREATE DATABASE Bank;
USE Bank;

CREATE TABLE clients (
    id_client INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    cin VARCHAR(20) UNIQUE,
    telephone VARCHAR(20)
);

CREATE TABLE Compte_Bancaire (
    RIB VARCHAR(30) PRIMARY KEY,
    solde DOUBLE DEFAULT 0.0,
    IBAN VARCHAR(34) UNIQUE,
    code_PIN VARCHAR(4),
    Date_Ouverture DATE,
    password VARCHAR(255),
    id_client INT,
    FOREIGN KEY (id_client) REFERENCES clients(id_client) ON DELETE CASCADE
);

CREATE TABLE admins (
    id_admin INT AUTO_INCREMENT PRIMARY KEY,
    cin VARCHAR(20) UNIQUE,
    password VARCHAR(255)
);