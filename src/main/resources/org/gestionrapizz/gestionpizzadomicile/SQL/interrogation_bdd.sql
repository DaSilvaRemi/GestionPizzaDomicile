
/*------------------------------------------
----------------MENU------------------------
------------------------------------------*/
SELECT Pizza.nom, Pizza.prix, GROUP_CONCAT(Ingredients.nom) FROM Pizza
    INNER JOIN Composer on Pizza.id_pizza = Composer.id_pizza
    INNER JOIN Ingredients on Composer.id_ingredient = Ingredients.id_ingredient
    GROUP BY Pizza.nom, Pizza.prix;

/*------------------------------------------
--------------Fiche Livraison---------------
------------------------------------------*/
SELECT ULivreur.nom AS nomLivreur, Type.nom AS typeVehicule, UClient.nom AS nomClient, Commande.dateHeure_commande, Commande.retard, Pizza.nom AS nomPizza, Pizza.prix FROM Livreur
INNER JOIN Commande on Livreur.id_utilisateur = Commande.id_utilisateur
INNER JOIN Client on Commande.id_utilisateur_1 = Client.id_utilisateur
INNER JOIN Vehicule on Commande.immatriculation = Vehicule.immatriculation
INNER JOIN Type on Vehicule.id_type = Type.id_type
INNER JOIN Utilisateur ULivreur on Livreur.id_utilisateur = ULivreur.id_utilisateur
INNER JOIN Utilisateur UClient on UClient.id_utilisateur = Client.id_utilisateur
INNER JOIN Contenir on Commande.id_commande = Contenir.id_commande
INNER JOIN Pizza on Contenir.id_pizza = Pizza.id_pizza
GROUP BY ULivreur.nom, Type.nom, UClient.nom, Commande.dateHeure_commande, Commande.retard, Pizza.nom, Pizza.prix
ORDER BY dateHeure_commande DESC;

/*------------------------------------------
--------------Questions diverses---------------
------------------------------------------*/
#vehicule n'ayant jamais servi
SELECT Vehicule.immatriculation FROM Vehicule
EXCEPT (SELECT Commande.immatriculation FROM Commande);

#Nb Commande par client
SELECT Commande.id_utilisateur_1 AS idClient, COUNT(Commande.id_commande) AS nbCommandes FROM Commande
GROUP BY Commande.id_utilisateur_1;

#Moyenne des montant des commandes par client
SELECT Commande.id_utilisateur_1 AS idClient, AVG(Commande.montant) AS moyMontantCommandes FROM Commande
GROUP BY Commande.id_utilisateur_1;

#Moyenne des commandes
SELECT AVG(Commande.id_commande) AS moyMontantCommandes, AVG(Commande.montant) AS moyMontantCommandes FROM Commande
GROUP BY Commande.id_utilisateur_1;

#Extraction des clients ayant commandé plus que la moyenne
SELECT Commande.id_utilisateur_1 AS idClient, COUNT(Commande.id_commande) AS nbCommandes FROM Commande
GROUP BY Commande.id_utilisateur_1
HAVING nbCommandes > AVG(Commande.id_commande);

/*------------------------------------------
--------------Statistiques---------------
------------------------------------------*/
#Meilleur client
SELECT Utilisateur.*, Client.telephone, Client.adresse_rue, Client.adresse_codepostal, Client.adresse_ville, Client.solde, SUM(Commande.montant) AS montantTotal FROM Utilisateur
INNER JOIN Client on Utilisateur.id_utilisateur = Client.id_utilisateur
INNER JOIN Commande on Client.id_utilisateur = Commande.id_utilisateur_1
GROUP BY Client.telephone, Client.adresse_rue, Client.adresse_codepostal, Client.adresse_ville, Client.solde
ORDER BY montantTotal DESC
LIMIT 1;

#Pire livreur
SELECT Utilisateur.*, Commande.immatriculation, COUNT(Commande.retard) AS nbRetards FROM Commande
INNER JOIN Livreur on Commande.id_utilisateur = Livreur.id_utilisateur
INNER JOIN Utilisateur on Livreur.id_utilisateur = Utilisateur.id_utilisateur
GROUP BY Commande.immatriculation
ORDER BY nbRetards DESC LIMIT 1;

#Pizza favorite
SELECT Pizza.nom AS nomPizza, COUNT(Contenir.id_pizza) as nbDemandes FROM Contenir
INNER JOIN Pizza on Contenir.id_pizza = Pizza.id_pizza
GROUP BY Pizza.nom
ORDER BY nbDemandes DESC;

#Pizza la moins demandées
SELECT Pizza.nom AS nomPizza, COUNT(Contenir.id_pizza) as nbDemandes FROM Contenir
INNER JOIN Pizza on Contenir.id_pizza = Pizza.id_pizza
GROUP BY Pizza.nom
ORDER BY nbDemandes ASC;

#Ingrédient favoris
SELECT Ingredients.nom AS nomIngredient, COUNT(Composer.id_ingredient) AS nbIngredients FROM Ingredients
INNER JOIN Composer ON Ingredients.id_ingredient = Composer.id_ingredient
INNER JOIN Pizza ON Composer.id_pizza = Pizza.id_pizza
INNER JOIN Produit ON Pizza.id_pizza = Produit.id_pizza
INNER JOIN Contenir ON Produit.id_taille = Contenir.id_taille AND Produit.id_pizza = Contenir.id_pizza
INNER JOIN Commande ON Contenir.id_commande = Commande.id_commande
GROUP BY Ingredients.nom
ORDER BY nbIngredients DESC;
