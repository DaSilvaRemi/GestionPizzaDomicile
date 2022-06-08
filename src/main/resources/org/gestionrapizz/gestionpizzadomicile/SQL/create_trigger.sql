CREATE OR REPLACE PROCEDURE VerifSoldeClientOnCommande(IN id_client INT, IN montantCommande DOUBLE, OUT idStatut INT)
BEGIN
    DECLARE totMontantCommandesClient DOUBLE;
    SET totMontantCommandesClient = (
        SELECT SUM(Commande.montant) AS totMontantCommandesClient
        FROM Commande
        INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur
        INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation
        INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur
        INNER JOIN Statut ON Commande.id_statut = Statut.id_statut
        WHERE Commande.id_utilisateur_1 = id_client AND Statut.nom = 'En attente' OR Statut.nom = 'En cours de préparation' OR Statut.nom = 'Livraison en cours'
        GROUP BY Commande.id_utilisateur_1
    );

    IF (SELECT Client.solde FROM Client WHERE Client.id_utilisateur = id_client) < montantCommande + totMontantCommandesClient THEN
        SET idStatut = (SELECT Statut.id_statut FROM Statut WHERE Statut.nom = 'Refusé');
    END IF;
END;

CREATE OR REPLACE PROCEDURE VerifFideliteClientOnCommande(IN id_client INT, OUT montantCommande DOUBLE)
BEGIN
    DECLARE nbPizzaCommander INTEGER;
    SET nbPizzaCommander = (SELECT COUNT(Contenir.id_pizza) AS nbPizzaCommander FROM Contenir
        INNER JOIN Commande on Contenir.id_commande = Commande.id_commande
        INNER JOIN Client on Commande.id_utilisateur_1 = Client.id_utilisateur
        GROUP BY Commande.id_utilisateur_1
    );

    IF nbPizzaCommander / 10 >= 1 THEN

        SET montantCommande = montantCommande + (SELECT SUM(
            CASE Taille.nom
                WHEN 'Naine' THEN ROUND(prix * 0.67, 2)
                WHEN 'Ogresse' THEN ROUND(prix * 1.33, 2)
                ELSE Pizza.prix
            END) AS prix_produit
        FROM Produit
        INNER JOIN Pizza ON Pizza.id_pizza = Produit.id_pizza
        INNER JOIN Taille ON Taille.id_taille = Produit.id_taille
        INNER JOIN Contenir ON Produit.id_taille = Contenir.id_taille AND Produit.id_pizza = Contenir.id_pizza
        INNER JOIN Commande ON Contenir.id_commande = Commande.id_commande
        WHERE Commande.id_commande = 1
        ORDER BY Pizza.prix DESC
        LIMIT nbPizzaCommander);
    END IF;
END;

CREATE OR REPLACE PROCEDURE VerifRetardCommande(IN dateHeureCommande DATETIME, IN dateHeureLivraison DATETIME,
                                                OUT montant DOUBLE, OUT retard BOOLEAN)
BEGIN
    IF dateHeureLivraison IS NOT NULL AND TIMEDIFF(TIME(dateHeureLivraison), TIME(dateHeureCommande)) >= '00:30:00' THEN
        SET retard = TRUE;
        SET montant = 0.0;
    END IF;
END;

CREATE OR REPLACE TRIGGER verifdatas_commande_insert_trigger
    BEFORE INSERT
    ON Commande
    FOR EACH ROW
BEGIN
    #CALL VerifSoldeClientOnCommande(NEW.id_utilisateur_1, NEW.montant, NEW.id_statut);

    IF (SELECT Statut.nom FROM Statut WHERE Statut.id_statut = NEW.id_statut) != 'Refusé' THEN
        CALL VerifFideliteClientOnCommande(NEW.id_utilisateur_1, NEW.montant);
    END IF;
END;

CREATE OR REPLACE TRIGGER verifdatas_commande_update_trigger
    BEFORE UPDATE
    ON Commande
    FOR EACH ROW
BEGIN
    IF (SELECT Statut.nom FROM Statut WHERE Statut.id_statut = NEW.id_statut) != 'Refusé' THEN
        CALL VerifSoldeClientOnCommande(NEW.id_utilisateur_1, NEW.montant, NEW.id_statut);
        IF (SELECT Statut.nom FROM Statut WHERE Statut.id_statut = NEW.id_statut) != 'Refusé' THEN
            CALL VerifFideliteClientOnCommande(NEW.id_utilisateur_1, NEW.montant);
            CALL VerifRetardCommande(NEW.dateHeure_commande, NEW.dateHeure_livraison, NEW.montant, NEW.retard);
        END IF;
    END IF;
END;
