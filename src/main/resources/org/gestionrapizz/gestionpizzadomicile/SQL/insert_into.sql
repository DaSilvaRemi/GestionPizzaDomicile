INSERT INTO Ingredients (nom)
VALUES ('Artichaut'),
       ('Champignons'),
       ('Crème fraîche'),
       ('Fromage de chèvre'),
       ('Fromage râpé'),
       ('Jambon'),
       ('Merguez'),
       ('Miel'),
       ('Mozzarella'),
       ('Oeuf'),
       ('Oignons rouges'),
       ('Olives'),
       ('Origan'),
       ('Poivrons'),
       ('Salade'),
       ('Sauce tomate');

INSERT INTO Pizza (nom, prix)
VALUES ('Végétarienne', 8.30),
       ('Chèvre et miel', 14.40),
       ('Speciale pizzaiolo', 11.40),
       ('Vesuvio', 13.00),
       ('Regina', 12.40),
       ('Diavola', 14.40),
       ('Capri', 10.90);

INSERT INTO Composer (id_pizza, id_ingredient)
VALUES ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Chèvre et miel'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Poivrons')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Chèvre et miel'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Crème fraîche')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Chèvre et miel'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Mozzarella')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Chèvre et miel'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Fromage de chèvre')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Chèvre et miel'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Jambon')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Chèvre et miel'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Salade')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Chèvre et miel'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Miel')),

       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Speciale pizzaiolo'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Sauce tomate')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Speciale pizzaiolo'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Mozzarella')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Speciale pizzaiolo'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Jambon')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Speciale pizzaiolo'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Oeuf')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Speciale pizzaiolo'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Crème fraîche')),

       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Vesuvio'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Sauce tomate')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Vesuvio'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Mozzarella')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Vesuvio'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Merguez')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Vesuvio'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Poivrons')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Vesuvio'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Oeuf')),

       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Regina'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Sauce tomate')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Regina'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Mozzarella')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Regina'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Jambon')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Regina'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Champignons')),

       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Diavola'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Sauce tomate')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Diavola'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Fromage râpé')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Diavola'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Poivrons')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Diavola'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Oignons rouges')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Diavola'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Mozzarella')),

       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Capri'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Sauce tomate')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Capri'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Mozzarella')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Capri'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Fromage de chèvre')),

       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Végétarienne'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Poivrons')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Végétarienne'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Artichaut')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Végétarienne'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Olives')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Végétarienne'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Sauce tomate')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Végétarienne'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Origan')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Végétarienne'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Champignons')),
       ((SELECT Pizza.id_pizza FROM Pizza WHERE nom = 'Végétarienne'),
        (SELECT Ingredients.id_ingredient FROM Ingredients WHERE nom = 'Salade'));

INSERT INTO Taille (nom)
VALUES ('Naine'),
       ('Humaine'),
       ('Ogresse');

INSERT INTO Type (nom)
VALUES ('Voiture'),
       ('Scooter');

INSERT INTO Vehicule (immatriculation, id_type)
VALUES ('BA102GH', (SELECT Type.id_type FROM Type WHERE nom = 'Voiture')),
       ('EF879BB', (SELECT Type.id_type FROM Type WHERE nom = 'Scooter')),
       ('345RHC77', (SELECT Type.id_type FROM Type WHERE nom = 'Scooter'));


INSERT INTO Utilisateur (nom, prenom, email, motdepasse)
VALUES ('TRAN', 'Guillaume', 'guillaumetran@rapizz.fr', PASSWORD('rapidoTran')),
       ('DURAND', 'Martin', 'mdurand@gmail.com', PASSWORD('martinDurand')),
       ('My Admin', 'admin', 'support@rapizz.fr', PASSWORD('admin')),
       ('Rapido', 'Vialet', 'rvialet@rapizz.fr', PASSWORD('rapido')),
       ('Prof', 'Dotto', 'Prof.Dotto@gmail.com', PASSWORD('dotto')),
       ('Timide', 'Mammolo', 'Timide.Mammolo@gmail.com', PASSWORD('mammolo')),
       ('Atchoum', 'Eolo', 'Atchoum.Eolo@gmail.com', PASSWORD('eolo')),
       ('Joyeux', 'Gongolo', 'Joyeux.Gongolo@gmail.com', PASSWORD('gongolo')),
       ('Simplet', 'Cucciolo', 'Simplet.Cucciolo@gmail.com', PASSWORD('cucciolo')),
       ('Dormeur', 'Pisolo', 'Dormeur.Pisolo@gmail.com', PASSWORD('pisolo')),
       ('Grincheux', 'Brontolo', 'Grincheux.Brontolo@gmail.com', PASSWORD('brontolo')),
       ('PEGULA', 'Jessica', 'jessicapegula@hotmail.com', PASSWORD('jessica'));

INSERT INTO Client (id_utilisateur, telephone, adresse_rue, adresse_ville, adresse_codepostal, solde)
VALUES ((SELECT Utilisateur.id_utilisateur FROM Utilisateur WHERE email = 'mdurand@gmail.com'), '0689737629',
        '28 rue des grands prés', 'Montcuq', '46800', 90.0),
       ((SELECT Utilisateur.id_utilisateur FROM Utilisateur WHERE email = 'jessicapegula@hotmail.com'), '0189785642',
        '3 boulevard De Gaulle', 'Paris', '75009', 12.1);

INSERT INTO Livreur (id_utilisateur)
VALUES ((SELECT Utilisateur.id_utilisateur FROM Utilisateur WHERE email = 'rvialet@rapizz.fr')),
       ((SELECT Utilisateur.id_utilisateur FROM Utilisateur WHERE email = 'guillaumetran@rapizz.fr'));

INSERT INTO Administrateur (id_utilisateur)
VALUES ((SELECT Utilisateur.id_utilisateur FROM Utilisateur WHERE email = 'support@rapizz.fr'));

INSERT INTO Statut (nom)
VALUES ('Livraison en cours'),
       ('Livré'),
       ('En attente'),
       ('Refusé');

INSERT INTO Produit (id_taille, id_pizza)
VALUES ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Naine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Végétarienne')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Humaine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Végétarienne')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Ogresse'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Végétarienne')),

       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Naine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Chèvre et miel')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Humaine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Chèvre et miel')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Ogresse'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Chèvre et miel')),

       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Naine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Speciale pizzaiolo')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Humaine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Speciale pizzaiolo')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Ogresse'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Speciale pizzaiolo')),

       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Naine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Vesuvio')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Humaine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Vesuvio')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Ogresse'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Vesuvio')),

       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Naine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Regina')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Humaine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Regina')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Ogresse'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Regina')),

       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Naine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Diavola')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Humaine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Diavola')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Ogresse'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Diavola')),

       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Naine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Capri')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Humaine'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Capri')),
       ((SELECT Taille.id_taille FROM Taille WHERE Taille.nom = 'Ogresse'),
        (SELECT Pizza.id_pizza FROM Pizza WHERE Pizza.nom = 'Capri'));