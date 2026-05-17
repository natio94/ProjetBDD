CREATE DATABASE if not exists Art_Connect;
USE Art_Connect ;

CREATE TABLE if not exists Artiste(
   id_artiste INT AUTO_INCREMENT,
   bio TEXT,
   birth DATE,
   Email VARCHAR(50),
   phone VARCHAR(20),
   city VARCHAR(50),
   website VARCHAR(100),
   socialMedia VARCHAR(100),
   isActive BOOLEAN,
   name VARCHAR(50),
   PRIMARY KEY(id_artiste)
);

CREATE TABLE if not exists Artwork(
   id_artworks INT AUTO_INCREMENT,
   Title VARCHAR(100),
   CreationYear INT,
   Type VARCHAR(50),
   Medium VARCHAR(50),
   Dimensions VARCHAR(50),
   Descriptions TEXT,
   Price DECIMAL(10,2),
   Status VARCHAR(50),
   id_artiste INT NOT NULL,
   PRIMARY KEY(id_artworks),
   FOREIGN KEY(id_artiste) REFERENCES Artiste(id_artiste)
);

CREATE TABLE if not exists CommunityMember(
   idMember INT AUTO_INCREMENT,
   name VARCHAR(50),
   email VARCHAR(50),
   phone VARCHAR(20),
   city VARCHAR(50),
   membershipType VARCHAR(50),
   birthdate DATE,
   PRIMARY KEY(idMember)
);

CREATE TABLE if not exists Review(
   idReview INT AUTO_INCREMENT,
   comment TEXT,
   Rating INT,
   ReviewDate DATETIME,
   idMember INT NOT NULL,
   id_artworks INT NOT NULL,
   PRIMARY KEY(idReview),
   FOREIGN KEY(idMember) REFERENCES CommunityMember(idMember),
   FOREIGN KEY(id_artworks) REFERENCES Artwork(id_artworks)
);

CREATE TABLE if not exists ArtworkTag(
   idTag INT AUTO_INCREMENT,
   name VARCHAR(50),
   PRIMARY KEY(idTag)
);

CREATE TABLE if not exists Gallery(
   idGallery INT AUTO_INCREMENT,
   name VARCHAR(50),
   address VARCHAR(100),
   ownerName VARCHAR(50),
   openingHours VARCHAR(100),
   contactPhone VARCHAR(20),
   rating DECIMAL(3,2),
   website VARCHAR(100),
   PRIMARY KEY(idGallery)
);

CREATE TABLE if not exists Exhibition(
   idExibhition INT AUTO_INCREMENT,
   title VARCHAR(100),
   curatorName VARCHAR(50),
   Theme VARCHAR(50),
   startDate DATETIME,
   endDate DATETIME,
   idGallery INT NOT NULL,
   PRIMARY KEY(idExibhition),
   FOREIGN KEY(idGallery) REFERENCES Gallery(idGallery)
);

CREATE TABLE if not exists Discipline(
   idDiscipline INT AUTO_INCREMENT,
   name VARCHAR(50),
   PRIMARY KEY(idDiscipline)
);

CREATE TABLE if not exists Workshop(
   idWorkshop INT AUTO_INCREMENT,
   title VARCHAR(100),
   date DATETIME,
   duration INT,
   maxParticipant INT,
   price DECIMAL(10,2),
   Location VARCHAR(100),
   Description TEXT,
   Level VARCHAR(50),
   id_artiste INT NOT NULL,
   PRIMARY KEY(idWorkshop),
   FOREIGN KEY(id_artiste) REFERENCES Artiste(id_artiste)
);

CREATE TABLE if not exists Booking(
   id_booking INT AUTO_INCREMENT,
   Booking_Date DATETIME,
   paymentStatus VARCHAR(50),
   idWorkshop INT NOT NULL,
   idMember INT NOT NULL,
   PRIMARY KEY(id_booking),
   FOREIGN KEY(idWorkshop) REFERENCES Workshop(idWorkshop),
   FOREIGN KEY(idMember) REFERENCES CommunityMember(idMember)
);

CREATE TABLE if not exists Artwork_ArtworkTag(
   id_artworks INT,
   idTag INT,
   PRIMARY KEY(id_artworks, idTag),
   FOREIGN KEY(id_artworks) REFERENCES Artwork(id_artworks),
   FOREIGN KEY(idTag) REFERENCES ArtworkTag(idTag)
);

CREATE TABLE if not exists Artiste_Discipline(
   id_artiste INT,
   idDiscipline INT,
   PRIMARY KEY(id_artiste, idDiscipline),
   FOREIGN KEY(id_artiste) REFERENCES Artiste(id_artiste),
   FOREIGN KEY(idDiscipline) REFERENCES Discipline(idDiscipline)
);

CREATE TABLE if not exists Exhibition_Artwork(
   id_artworks INT,
   idExibhition INT,
   PRIMARY KEY(id_artworks, idExibhition),
   FOREIGN KEY(id_artworks) REFERENCES Artwork(id_artworks),
   FOREIGN KEY(idExibhition) REFERENCES Exhibition(idExibhition)
);

CREATE TABLE if not exists Artiste_Artwork(
   id_artiste INT,
   id_artworks INT,
   PRIMARY KEY(id_artiste, id_artworks),
   FOREIGN KEY(id_artiste) REFERENCES Artiste(id_artiste),
   FOREIGN KEY(id_artworks) REFERENCES Artwork(id_artworks)
);