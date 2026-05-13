-- Fonctions

-- Fonction 1 : Nombre de participants à un atelier
DELIMITER $$
CREATE FUNCTION NbParticipants(p_idWorkshop INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total
    FROM Booking
    WHERE idWorkshop = p_idWorkshop
      AND paymentStatus != 'Cancelled';
    RETURN total;
END$$
DELIMITER ;

-- Fonction 2 : Prix moyen des oeurvres d'un artiste
DELIMITER $$
CREATE FUNCTION PrixMoyenArtiste(p_id_artiste INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE avg_price DECIMAL(10,2);
    SELECT AVG(Price) INTO avg_price
    FROM Artwork
    WHERE id_artiste = p_id_artiste;
    RETURN IFNULL(avg_price, 0);
END$$
DELIMITER ;

-- Fonction 3 : Disponibilité d'un atelier
DELIMITER $$
CREATE FUNCTION PlacesRestantes(p_idWorkshop INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE v_max INT;
    DECLARE v_count INT;
    SELECT maxParticipant INTO v_max
    FROM Workshop
    WHERE idWorkshop = p_idWorkshop;
    SELECT COUNT(*) INTO v_count
    FROM Booking
    WHERE idWorkshop = p_idWorkshop
      AND paymentStatus != 'Cancelled';
    RETURN v_max - v_count;
END$$
DELIMITER ;