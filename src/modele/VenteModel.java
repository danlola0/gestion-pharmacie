/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author User
 */
public class VenteModel {
    private int id;
    private int id_client;
    private int id_fournisseur;
    private int prix;
    private int qte;
    private String nom_client;
    private String nom_fournisseur;
    private String medicament;

    public VenteModel() {
    }

    public VenteModel(int id, int id_client, int id_fournisseur, int prix, int qte, String nom_client, String nom_fournisseur, String medicament) {
        this.id = id;
        this.id_client = id_client;
        this.id_fournisseur = id_fournisseur;
        this.prix = prix;
        this.qte = qte;
        this.nom_client = nom_client;
        this.nom_fournisseur = nom_fournisseur;
        this.medicament = medicament;
    }
    public VenteModel(int id,  String nom_client, String nom_fournisseur, String medicament, int prix, int qte) {
        this.id = id;
          this.nom_client = nom_client;
        this.nom_fournisseur = nom_fournisseur;
        this.medicament = medicament;
        this.prix = prix;
        this.qte = qte;
      
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_client
     */
    public int getId_client() {
        return id_client;
    }

    /**
     * @param id_client the id_client to set
     */
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    /**
     * @return the id_fournisseur
     */
    public int getId_fournisseur() {
        return id_fournisseur;
    }

    /**
     * @param id_fournisseur the id_fournisseur to set
     */
    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    /**
     * @return the prix
     */
    public int getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * @return the qte
     */
    public int getQte() {
        return qte;
    }

    /**
     * @param qte the qte to set
     */
    public void setQte(int qte) {
        this.qte = qte;
    }

    /**
     * @return the nom_client
     */
    public String getNom_client() {
        return nom_client;
    }

    /**
     * @param nom_client the nom_client to set
     */
    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    /**
     * @return the nom_fournisseur
     */
    public String getNom_fournisseur() {
        return nom_fournisseur;
    }

    /**
     * @param nom_fournisseur the nom_fournisseur to set
     */
    public void setNom_fournisseur(String nom_fournisseur) {
        this.nom_fournisseur = nom_fournisseur;
    }

    /**
     * @return the medicament
     */
    public String getMedicament() {
        return medicament;
    }

    /**
     * @param medicament the medicament to set
     */
    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }
    
    
    
}
