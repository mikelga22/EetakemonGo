DROP DATABASE IF EXISTS Proyecto;
CREATE DATABASE Proyecto;
USE Proyecto;

CREATE TABLE User (

  id INTEGER NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255),
  contrasena VARCHAR(255),
  email VARCHAR(255),
  admin INTEGER,
  PRIMARY KEY (id)   
)ENGINE = InnoDB;


CREATE TABLE Eetakemon (

  id INTEGER NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255),
  tipo VARCHAR(255),
  nivel INTEGER NOT NULL,
  foto VARCHAR(255),
  PRIMARY KEY (id)   
)ENGINE = InnoDB;

CREATE TABLE Relation (

  id INTEGER NOT NULL AUTO_INCREMENT,
  idUser INTEGER NOT NULL,
  idEetakemon INTEGER NOT NULL,
  level INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (idUser) REFERENCES   User(id),
  FOREIGN KEY (idEetakemon) REFERENCES   Eetakemon(id)   
)ENGINE = InnoDB;


Insert INTO User (nombre, contrasena, email, admin) VALUES (mikel, mikel, mikel@gmail.com, 1);
Insert INTO User (nombre, contrasena, email, admin) VALUES (aleix, aleix, aleix@gmail.com, 1);
Insert INTO User (nombre, contrasena, email, admin) VALUES (guillem, guillem, guillem@gmail.com, 1);

Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES (Bernorlax, Normal, 15, http://localhost:8081/images/bernorlax.png);
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES (Davyphno, Normal, 15, http://localhost:8081/images/davyphno.png);
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES (Francerpie, Inferior, 1, http://localhost:8081/images/francerpie.png);
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES (Jesuskou, Inferior, 1, http://localhost:8081/images/jesuscou.png);
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES (Jordinine, Normal, 15, http://localhost:8081/images/jordinine.png);
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES (Lluiscarp, Legendario, 30, http://localhost:8081/images/lluiscarp.png);
Insert INTO Eetakemon (nombre, tipo, nivel, foto) VALUES (Mewdecerio, Legendario, 30, http://localhost:8081/images/mewdecerio.png);

Insert INTO Relation (idUser, idEetakemon, level) VALUES (1, 1, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (2, 1, 30);
Insert INTO Relation (idUser, idEetakemon, level) VALUES (2, 1, 30);
