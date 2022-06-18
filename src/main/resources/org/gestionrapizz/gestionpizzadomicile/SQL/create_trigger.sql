CREATE OR REPLACE PROCEDURE verif_solde_client_on_commande(IN id_client INT, IN montant_commande DOUBLE, INOUT id_statut INT)
BEGIN
    DECLARE tot_montant_commandes_by_client DOUBLE;
    SET tot_montant_commandes_by_client = (
        SELECT IFNULL((
                SELECT ROUND(SUM(Commande.montant), 2)
                FROM Commande
                INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur
                INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation
                INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur
                INNER JOIN Statut ON Commande.id_statut = Statut.id_statut
                WHERE Commande.id_utilisateur_1 = id_client AND (Statut.nom = 'En attente' OR Statut.nom = 'Livraison en cours')
                GROUP BY Commande.id_utilisateur_1
            ),
                0.0
            ) AS tot_montant_commandes_by_client
    );

    IF (SELECT Client.solde < tot_montant_commandes_by_client FROM Client WHERE Client.id_utilisateur = id_client) = 1 THEN
        SET id_statut = (SELECT Statut.id_statut FROM Statut WHERE Statut.nom = 'Refusé');
    END IF;
END;

CREATE OR REPLACE PROCEDURE verif_fidelite_client_on_commande(IN id_new_commande INT, IN id_client INT, INOUT montant_commande DOUBLE)
BEGIN
    DECLARE nb_pizza_commander INTEGER;

    SET nb_pizza_commander = (SELECT COUNT(Contenir.id_pizza) AS nb_pizza_commander FROM Contenir
        INNER JOIN Commande on Contenir.id_commande = Commande.id_commande
        INNER JOIN Client on Commande.id_utilisateur_1 = Client.id_utilisateur
        WHERE Commande.id_utilisateur_1 = id_client
        GROUP BY Commande.id_utilisateur_1
    );

    IF (SELECT nb_pizza_commander % 10) = 0 THEN
        SET montant_commande = (
            SELECT IFNULL((
                SELECT ROUND(montant_commande - SUM((
                        SELECT
                            CASE Taille.nom
                                WHEN 'Naine' THEN ROUND(prix * 0.67, 2)
                                WHEN 'Ogresse' THEN ROUND(prix * 1.33, 2)
                                ELSE Pizza.prix
                            END
                        FROM Produit
                        INNER JOIN Pizza ON Pizza.id_pizza = Produit.id_pizza
                        INNER JOIN Taille ON Taille.id_taille = Produit.id_taille
                        INNER JOIN Contenir ON Produit.id_taille = Contenir.id_taille AND Produit.id_pizza = Contenir.id_pizza
                        INNER JOIN Commande ON Contenir.id_commande = Commande.id_commande
                        INNER JOIN Statut on Commande.id_statut = Statut.id_statut
                        WHERE Commande.id_commande = id_new_commande
                        ORDER BY Pizza.prix
                        LIMIT 1
                )), 2)
            ), montant_commande));
    END IF;
END;

CREATE OR REPLACE PROCEDURE verif_retard_commande(IN date_heure_commande DATETIME, IN date_heure_livraison DATETIME, INOUT montant DOUBLE, INOUT retard BOOLEAN)
BEGIN
    IF (SELECT TIMESTAMPDIFF(MINUTE, date_heure_commande, date_heure_livraison) >= 30 FROM Commande WHERE date_heure_livraison IS NOT NULL LIMIT 1) = 1 THEN
        SET retard = TRUE;
        SET montant = 0.0;
    END IF;
END;

CREATE OR REPLACE PROCEDURE verif_fidelite_contenir(IN id_commande_contenir INT)
BEGIN
    DECLARE id_client, id_statut_commande, nb_pizza_commander, nb_pizza_a_rembourser INT;
    DECLARE montant_commande DOUBLE;

    SET id_client = (SELECT Commande.id_utilisateur_1 FROM Commande WHERE Commande.id_commande = id_commande_contenir);
    SET id_statut_commande = (SELECT Commande.id_statut FROM Commande WHERE Commande.id_commande = id_commande_contenir);
    SET montant_commande = (
    SELECT IFNULL((
        SELECT SUM(
            CASE Taille.nom
                WHEN 'Naine' THEN ROUND(prix * 0.67, 2)
                WHEN 'Ogresse' THEN ROUND(prix * 1.33, 2)
                ELSE Pizza.prix
            END)
        FROM Produit
        INNER JOIN Pizza ON Pizza.id_pizza = Produit.id_pizza
        INNER JOIN Taille ON Taille.id_taille = Produit.id_taille
        INNER JOIN Contenir ON Produit.id_taille = Contenir.id_taille AND Produit.id_pizza = Contenir.id_pizza
        INNER JOIN Commande ON Contenir.id_commande = Commande.id_commande
        WHERE Commande.id_commande = id_commande_contenir
        ORDER BY Pizza.prix
        ), 0.0));

    CALL verif_solde_client_on_commande(id_client, montant_commande, id_statut_commande);

    SET nb_pizza_commander = (SELECT COUNT(Contenir.id_pizza) AS nb_pizza_commander FROM Contenir
        INNER JOIN Commande on Contenir.id_commande = Commande.id_commande
        INNER JOIN Client on Commande.id_utilisateur_1 = Client.id_utilisateur
        WHERE Commande.id_commande = id_commande_contenir
        GROUP BY Commande.id_commande
    );

    SET nb_pizza_a_rembourser = (SELECT ROUND(nb_pizza_commander / 10, 0));
    IF (SELECT (SELECT Statut.nom != 'Refusé' FROM Statut WHERE Statut.id_statut = id_statut_commande) AND nb_pizza_a_rembourser >= 1) = 1 THEN

            SET montant_commande = (
                SELECT IFNULL((
                    SELECT ROUND(montant_commande - SUM((
                        SELECT CASE Taille.nom
                            WHEN 'Naine' THEN ROUND(prix * 0.67, 2)
                            WHEN 'Ogresse' THEN ROUND(prix * 1.33, 2)
                            ELSE Pizza.prix
                        END
                        FROM Produit
                        INNER JOIN Pizza ON Pizza.id_pizza = Produit.id_pizza
                        INNER JOIN Taille ON Taille.id_taille = Produit.id_taille
                        INNER JOIN Contenir ON Produit.id_taille = Contenir.id_taille AND Produit.id_pizza = Contenir.id_pizza
                        INNER JOIN Commande ON Contenir.id_commande = Commande.id_commande
                        INNER JOIN Statut on Commande.id_statut = Statut.id_statut
                        WHERE Commande.id_commande = id_commande_contenir
                        ORDER BY Pizza.prix
                        LIMIT nb_pizza_a_rembourser
                    )), 2)
                ), montant_commande));
    END IF;

    UPDATE Commande SET
        Commande.id_statut = id_statut_commande,
        Commande.montant = montant_commande
    WHERE id_commande = id_commande_contenir;
END;


CREATE OR REPLACE TRIGGER verifdatas_commande_insert_trigger
    BEFORE INSERT
    ON Commande
    FOR EACH ROW
BEGIN
    CALL verif_solde_client_on_commande(NEW.id_utilisateur_1, NEW.montant, NEW.id_statut);
    IF (SELECT Statut.nom FROM Statut WHERE Statut.id_statut = NEW.id_statut) != 'Refusé' THEN
         CALL verif_retard_commande(NEW.dateHeure_commande, NEW.dateHeure_livraison, NEW.montant, NEW.retard);
    END IF;
END;

CREATE OR REPLACE TRIGGER verifdatas_commande_update_trigger
    BEFORE UPDATE
    ON Commande
    FOR EACH ROW
BEGIN
    IF (SELECT Statut.nom FROM Statut WHERE Statut.id_statut = NEW.id_statut) != 'Refusé' THEN
        CALL verif_solde_client_on_commande(NEW.id_utilisateur_1, NEW.montant, NEW.id_statut);
        IF (SELECT Statut.nom FROM Statut WHERE Statut.id_statut = NEW.id_statut) != 'Refusé' THEN
            CALL verif_retard_commande(NEW.dateHeure_commande, NEW.dateHeure_livraison, NEW.montant, NEW.retard);
        END IF;
    END IF;
END;

CREATE OR REPLACE TRIGGER verifdatas_insert_contenir_trigger
    AFTER INSERT
    ON Contenir
    FOR EACH ROW
BEGIN
    CALL verif_fidelite_contenir(NEW.id_commande);
END;

CREATE OR REPLACE TRIGGER verifdatas_delete_contenir_trigger
    AFTER DELETE
    ON Contenir
    FOR EACH ROW
BEGIN
    CALL verif_fidelite_contenir(OLD.id_commande);
END;


