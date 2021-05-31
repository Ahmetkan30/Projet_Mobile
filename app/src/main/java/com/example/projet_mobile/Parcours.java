package com.example.projet_mobile;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Parcours {

    private FichPatient patient;
    private String titre;
    private String description;
    private String categorie;
    private ArrayList<Activiter> listeActivite;

    public Parcours(FichPatient patient, String titre, String description, String categorie, ArrayList<Activiter> listeActivite) {
        this.patient = patient;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.listeActivite = listeActivite;
        for(int i = 0; i<this.listeActivite.size(); i++){
            this.patient.addActivite(this.listeActivite.get(i));
        }
    }

    public FichPatient getPatient(){
        return patient;
    }

    public void setPatient(FichPatient patient){
        this.patient = patient;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public ArrayList<Activiter> getListeActivite() {
        return listeActivite;
    }

    public void setListeActivite(ArrayList<Activiter> listeActivite) {
        this.listeActivite = listeActivite;
    }

    @NonNull
    public String toString(){
        return patient+", "+titre+", "+description+", "+categorie+", "+listeActivite;
    }
}
