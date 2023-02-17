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
public class MedicamentModel {
    private int id;
    private String desc;
    private int prix;
    private int qte;

    public MedicamentModel() {
    }

    public MedicamentModel(int id, String desc, int prix, int qte) {
        this.id = id;
        this.desc = desc;
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
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the print
     */
    public int getPrix() {
        return prix;
    }

    /**
     * @param print the print to set
     */
    public void setPrint(int prix) {
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
    
}
