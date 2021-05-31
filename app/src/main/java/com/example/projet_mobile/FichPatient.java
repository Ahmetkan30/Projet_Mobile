package com.example.projet_mobile;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class FichPatient extends Utilisateur {

    private ArrayList<Seance> agenda;
    private final ArrayList<Activiter> activiteEnAttente;
    private final ArrayList<Activiter> activiteAssignee;
    protected static ArrayList<FichPatient> allPatient = new ArrayList<FichPatient>();

    public FichPatient(String nom, String prenom, String email, String password) {
        super(nom,prenom,email,password);
        this.agenda = new ArrayList<>();
        this.activiteEnAttente = new ArrayList<>();
        this.activiteAssignee = new ArrayList<>();
        allPatient.add(this);
    }

    public String getNom() {
        return super.getNom();
    }

    public void setNom(String nom) {
        super.setNom(nom);
    }

    public String getPrenom() {
        return super.getPrenom();
    }

    public void setPrenom(String prenom) {
        super.setPrenom(prenom);
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public ArrayList<Seance> getAgenda() {
        return agenda;
    }

    public void setAgenda(ArrayList<Seance> agenda) {
        this.agenda = agenda;
    }

    public void addSeance(Seance seance){
        this.agenda.add(seance);
    }

    public void addActivite(Activiter activite){
        this.activiteEnAttente.add(activite);
    }

    public void removeActivite(Activiter activite){
        this.activiteEnAttente.remove(activite);
        this.activiteAssignee.add(activite);
    }

    public ArrayList<Activiter> getActiviteEnAttente(){
        return this.activiteEnAttente;
    }

    public ArrayList<Activiter> getActiviteAssignee(){
        return this.activiteAssignee;
    }

    @NonNull
    public String toString(){
        return super.getPrenom() + " " + super.getNom();
    }

}