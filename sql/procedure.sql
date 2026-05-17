-- Procédures

-- Procédure 1 : Créer un atelier avec un artiste associé
DELIMITER $$
CREATE PROCEDURE CreerWorkshop(
    IN p_idWorkshop INT,
    IN p_title VARCHAR(100),
    IN p_date DATETIME,
    IN p_duration INT,
    IN p_maxParticipant INT,
    IN p_price DECIMAL(10,2),
    IN p_location VARCHAR(100),
    IN p_description TEXT,
    IN p_level VARCHAR(50),
    IN p_id_artiste INT
)
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM Artiste WHERE id_artiste = p_id_artiste
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Artiste inexistant';
    END IF;
    INSERT INTO Workshop
    VALUES (
        p_idWorkshop, p_title, p_date, p_duration,
        p_maxParticipant, p_price, p_location,
        p_description, p_level, p_id_artiste
    );
END$$
DELIMITER ;


-- Procédure 2 : Inscrire un membre à un atelier
DELIMITER $$
CREATE PROCEDURE InscrireMembreWorkshop(
    IN p_idMember INT,
    IN p_idWorkshop INT
)
BEGIN
    DECLARE v_max INT;
    DECLARE v_count INT;
    START TRANSACTION;
    SELECT maxParticipant INTO v_max
    FROM Workshop
    WHERE idWorkshop = p_idWorkshop
    FOR UPDATE;
    SELECT COUNT(*) INTO v_count
    FROM Booking
    WHERE idWorkshop = p_idWorkshop
      AND paymentStatus != 'Cancelled';
    INSERT INTO Booking (Booking_Date, paymentStatus, idWorkshop, idMember)
    VALUES (NOW(), 'Paid', p_idWorkshop, p_idMember);
    COMMIT;
END$$
DELIMITER ;



-- Procédure 3 : Ajoute une oeuvre + lien artiste
DELIMITER $$
CREATE PROCEDURE AjouterArtwork(
    IN p_id_artworks INT,
    IN p_title VARCHAR(100),
    IN p_year YEAR,
    IN p_type VARCHAR(50),
    IN p_medium VARCHAR(50),
    IN p_dimensions VARCHAR(50),
    IN p_description TEXT,
    IN p_price DECIMAL(10,2),
    IN p_status VARCHAR(50),
    IN p_id_artiste INT
)
BEGIN
    START TRANSACTION;
    INSERT INTO Artwork
    VALUES (
        p_id_artworks, p_title, p_year, p_type,p_medium, p_dimensions, p_description, p_price, p_status, p_id_artiste
    );
    INSERT INTO Artiste_Artwork
    VALUES (p_id_artiste, p_id_artworks);
    COMMIT;
END$$
DELIMITER ;