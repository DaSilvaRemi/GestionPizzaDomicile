package org.gestionrapizz.gestionpizzadomicile.models.tabs;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;

import java.sql.Timestamp;

public class MesCommandes {
    private int id;
    private Timestamp dateHeureCommande;
    private Timestamp dateHeureLivraison;
    private String nomStatut;
    private String nomLivreur;


    public MesCommandes(Commande commande){
        this.id = commande.getId();
        this.dateHeureCommande = commande.getDateHeure();
        this.dateHeureLivraison = commande.getDateHeureLivraison();
        this.nomStatut = commande.getStatut().getNom();
        this.nomLivreur = commande.getLivreur().getNom();
    }
}
