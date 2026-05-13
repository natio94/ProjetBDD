-- ================================================================
-- SCÉNARIO : Annulation d'un atelier par un artiste avec remboursement de tous les participants inscrits
-- Contexte : L'atelier #3 (Photographie de rue, Pierre Laurent) est annulé. Tous les membres ayant une réservation "Paid"  ou "Pending" sont basculés en "Refunded".
-- Si la mise à jour est incomplète → rollback total.
-- ================================================================

DELIMITER $$

CREATE PROCEDURE AnnulationAtelier(
    IN p_idWorkshop  INT,
    IN p_id_artiste  INT
)

BEGIN
    DECLARE v_nb_remboursements INT DEFAULT 0;
    DECLARE v_titre_atelier     VARCHAR(100);
    DECLARE v_artiste_atelier   INT;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;


        -- Verrouillage des réservations concernées
        SELECT COUNT(*) INTO v_nb_remboursements
        FROM   Booking
        WHERE  idWorkshop     = p_idWorkshop
          AND  paymentStatus  IN ('Paid', 'Pending')
        FOR UPDATE;

        -- Remboursement de tous les participants actifs
        UPDATE Booking
        SET    paymentStatus = 'Refunded'
        WHERE  idWorkshop    = p_idWorkshop
          AND  paymentStatus IN ('Paid', 'Pending');

        -- Vérification que toutes les lignes ont bien été mises à jour
        IF ROW_COUNT() != v_nb_remboursements THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Erreur lors du remboursement : mise à jour incomplète.';
        END IF;

        -- Désactivation de l'atelier (maxParticipant à 0 = fermé)
        UPDATE Workshop
        SET    maxParticipant = 0
        WHERE  idWorkshop = p_idWorkshop;

        -- Rapport d'annulation
        SELECT
            v_titre_atelier         AS atelier_annule,
            v_nb_remboursements     AS membres_rembourses,
            'Annulation réussie'    AS statut;

    COMMIT;
END$$

DELIMITER ;

-- Appel du scénario
CALL AnnulationAtelier(3, 3);  -- Atelier #3 annulé par Pierre (artiste #3)


-- Atomicité :
-- START TRANSACTION
-- COMMIT
-- ROLLBACK via EXIT HANDLER