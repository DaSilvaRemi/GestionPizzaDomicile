
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
SELECT ULivreur.nom AS nomLivreur, Type.nom AS typeVehicule, UClient.nom AS nomClient, Commande.dateHeure_commande, Commande.retard, Pizza.nom AS nomPizza, Pizza.prix
FROM Livreur
INNER JOIN Commande ON Livreur.id_utilisateur = Commande.id_utilisateur
INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur
INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation
INNER JOIN Type ON Vehicule.id_type = Type.id_type
INNER JOIN Utilisateur ULivreur ON Livreur.id_utilisateur = ULivreur.id_utilisateur
INNER JOIN Utilisateur UClient ON UClient.id_utilisateur = Client.id_utilisateur
INNER JOIN Contenir ON Commande.id_commande = Contenir.id_commande
INNER JOIN Pizza on Contenir.id_pizza = Pizza.id_pizza
WHERE Commande.id_utilisateur_1 = 2 AND Commande.id_commande = 1
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

#Moyenne des commandes
SELECT ROUND(AVG(Commande.montant), 2) AS moyMontantCommandes FROM Commande;

#Extraction des clients ayant commandé plus que la moyenne
SELECT Utilisateur.*, Client.telephone, Client.adresse_rue, Client.adresse_ville,
       Client.adresse_codepostal, Client.solde, ROUND(SUM(Commande.montant), 2) AS montantTotCommande
FROM Client
INNER JOIN Utilisateur ON Client.id_utilisateur = Utilisateur.id_utilisateur
INNER JOIN Commande ON Commande.id_utilisateur_1 = Client.id_utilisateur
GROUP BY Client.telephone, Client.adresse_rue, Client.adresse_ville, Client.adresse_codepostal, Client.solde
HAVING montantTotCommande > (SELECT ROUND(AVG(Commande.montant), 2) AS moyMontantCommandes FROM Commande);


/*------------------------------------------
--------------Statistiques---------------
------------------------------------------*/
#Meilleur client
SELECT Utilisateur.*, Client.telephone, Client.adresse_rue, Client.adresse_codepostal, Client.adresse_ville,
       Client.solde, SUM(Commande.montant) AS montantTotal
FROM Utilisateur
INNER JOIN Client on Utilisateur.id_utilisateur = Client.id_utilisateur
INNER JOIN Commande on Client.id_utilisateur = Commande.id_utilisateur_1
GROUP BY Client.telephone, Client.adresse_rue, Client.adresse_codepostal, Client.adresse_ville, Client.solde
ORDER BY montantTotal DESC
LIMIT 1;

#Pire livreur
SELECT Utilisateur.*, Commande.immatriculation, COUNT(Commande.retard) AS nbRetards FROM Commande
INNER JOIN Livreur on Commande.id_utilisateur = Livreur.id_utilisateur
INNER JOIN Utilisateur on Livreur.id_utilisateur = Utilisateur.id_utilisateur
WHERE Commande.retard = 1
GROUP BY Commande.immatriculation
ORDER BY nbRetards DESC LIMIT 1;


#Pizza favorite
SELECT Pizza.nom AS nomPizza, COUNT(Contenir.id_pizza) as nbDemandes FROM Contenir
INNER JOIN Pizza on Contenir.id_pizza = Pizza.id_pizza
GROUP BY Pizza.nom
ORDER BY nbDemandes DESC LIMIT 1;

#Pizza la moins demandées
SELECT Pizza.nom AS nomPizza, COUNT(Contenir.id_pizza) as nbDemandes FROM Contenir
INNER JOIN Pizza on Contenir.id_pizza = Pizza.id_pizza
GROUP BY Pizza.nom
ORDER BY nbDemandes LIMIT 1;

#Ingrédient favoris
SELECT Ingredients.nom AS nomIngredient, COUNT(Composer.id_ingredient) AS nbIngredients FROM Ingredients
INNER JOIN Composer ON Ingredients.id_ingredient = Composer.id_ingredient
INNER JOIN Pizza ON Composer.id_pizza = Pizza.id_pizza
INNER JOIN Produit ON Pizza.id_pizza = Produit.id_pizza
INNER JOIN Contenir ON Produit.id_taille = Contenir.id_taille AND Produit.id_pizza = Contenir.id_pizza
INNER JOIN Commande ON Contenir.id_commande = Commande.id_commande
GROUP BY Ingredients.nom
ORDER BY nbIngredients DESC;
LIMIT 1;

