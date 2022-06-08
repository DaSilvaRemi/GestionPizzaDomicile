CREATE TABLE Pizza
(
    id_pizza INT AUTO_INCREMENT,
    nom      VARCHAR(50) NOT NULL,
    prix     DOUBLE      NOT NULL DEFAULT 0.0,
    CONSTRAINT pk_id_pizza PRIMARY KEY (id_pizza),
    CONSTRAINT unique_pizza_nom UNIQUE (nom)
);

CREATE TABLE Ingredients
(
    id_ingredient INT AUTO_INCREMENT,
    nom           VARCHAR(50) NOT NULL,
    CONSTRAINT pk_ingredient_id PRIMARY KEY (id_ingredient),
    CONSTRAINT unique_ingredient_nom UNIQUE (nom)
);

CREATE TABLE Type
(
    id_type INT AUTO_INCREMENT,
    nom     VARCHAR(50) NOT NULL,
    CONSTRAINT pk_ingredient_id PRIMARY KEY (id_type),
    CONSTRAINT unique_type_nom UNIQUE (nom)
);

CREATE TABLE Vehicule
(
    immatriculation CHAR(10),
    id_type         INT NOT NULL,
    CONSTRAINT pk_vehicule_immatriculation PRIMARY KEY (immatriculation),
    CONSTRAINT fk_vehicule_idType_type FOREIGN KEY (id_type) REFERENCES Type (id_type)
);

CREATE TABLE Utilisateur
(
    id_utilisateur INT AUTO_INCREMENT,
    nom            VARCHAR(50) NOT NULL,
    prenom         VARCHAR(50) NOT NULL,
    email          VARCHAR(50) NOT NULL,
    motdepasse     TEXT        NOT NULL,
    CONSTRAINT pk_utilisateur_id PRIMARY KEY (id_utilisateur),
    CONSTRAINT unique_utilisateur_email UNIQUE (email)
);

CREATE TABLE Taille
(
    id_taille INT AUTO_INCREMENT,
    nom       VARCHAR(50) NOT NULL,
    CONSTRAINT pk_taille_id PRIMARY KEY (id_taille),
    CONSTRAINT unique_taille_nom UNIQUE (nom)
);

CREATE TABLE Produit
(
    id_taille INT,
    id_pizza  INT,
    CONSTRAINT pk_produit_idPizza_idTaille PRIMARY KEY (id_taille, id_pizza),
    CONSTRAINT fk_produit_idTaille_taille FOREIGN KEY (id_taille) REFERENCES Taille (id_taille),
    CONSTRAINT fk_produit_idPizza_pizza FOREIGN KEY (id_pizza) REFERENCES Pizza (id_pizza)
);

CREATE TABLE Client
(
    id_utilisateur     INT,
    telephone          VARCHAR(10) NOT NULL,
    adresse_rue        VARCHAR(60) NOT NULL,
    adresse_ville      VARCHAR(60) NOT NULL,
    adresse_codepostal VARCHAR(5)  NOT NULL,
    solde              DOUBLE      NOT NULL DEFAULT 0.00,
    CONSTRAINT pk_client_idUtilisateur PRIMARY KEY (id_utilisateur),
    CONSTRAINT unique_client_telephone UNIQUE (telephone),
    CONSTRAINT fk_client_idUtilisateur_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur (id_utilisateur)
);

CREATE TABLE Livreur
(
    id_utilisateur INT,
    CONSTRAINT pk_livreur_idUtilisateur PRIMARY KEY (id_utilisateur),
    CONSTRAINT fk_livreur_idUtilisateur_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur (id_utilisateur)
);

CREATE TABLE Administrateur
(
    id_utilisateur INT,
    CONSTRAINT pk_administrateur_idUtilisateur PRIMARY KEY (id_utilisateur),
    CONSTRAINT fk_administrateur_idUtilisateur_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur (id_utilisateur)
);

CREATE TABLE Statut
(
    id_statut INT AUTO_INCREMENT,
    nom       VARCHAR(50) NOT NULL,
    CONSTRAINT pk_statut_idStatut PRIMARY KEY (id_statut),
    CONSTRAINT unique_statut_nom UNIQUE (nom)
);

CREATE TABLE Commande
(
    id_commande         INT AUTO_INCREMENT,
    dateHeure_commande  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateHeure_livraison DATETIME,
    montant             DOUBLE   NOT NULL DEFAULT 0.0,
    retard              BOOLEAN  NOT NULL DEFAULT FALSE,
    id_utilisateur      INT      NOT NULL,
    immatriculation     CHAR(10) NOT NULL,
    id_utilisateur_1    INT      NOT NULL,
    id_statut           INT      NOT NULL,
    CONSTRAINT pk_commande_id PRIMARY KEY (id_commande),
    CONSTRAINT fk_commande_idStatut_statut FOREIGN KEY (id_statut) REFERENCES Statut (id_statut),
    CONSTRAINT fk_commande_idUtilisateur_livreur FOREIGN KEY (id_utilisateur) REFERENCES Livreur (id_utilisateur),
    CONSTRAINT fk_commande_immatriculation_vehicule FOREIGN KEY (immatriculation) REFERENCES Vehicule (immatriculation),
    CONSTRAINT fk_commande_idUtilisateur_client FOREIGN KEY (id_utilisateur_1) REFERENCES Client (id_utilisateur)
);

CREATE TABLE Contenir
(
    id_commande INT,
    id_taille   INT,
    id_pizza    INT,
    CONSTRAINT pk_contenir_idCommande_idPizza_idTaille PRIMARY KEY (id_commande, id_taille, id_pizza),
    CONSTRAINT fk_contenir_idCommande_commande FOREIGN KEY (id_commande) REFERENCES Commande (id_commande),
    CONSTRAINT fk_contenir_idCommande_idPizza_produit FOREIGN KEY (id_taille, id_pizza) REFERENCES Produit (id_taille, id_pizza)
);

CREATE TABLE Composer
(
    id_pizza      INT,
    id_ingredient INT,
    CONSTRAINT pk_composer_idPizza_idIngredient PRIMARY KEY (id_pizza, id_ingredient),
    CONSTRAINT fk_composer_idPizza_pizza FOREIGN KEY (id_pizza) REFERENCES Pizza (id_pizza),
    CONSTRAINT fk_composer_idIngredients_ingredient FOREIGN KEY (id_ingredient) REFERENCES Ingredients (id_ingredient)
);
