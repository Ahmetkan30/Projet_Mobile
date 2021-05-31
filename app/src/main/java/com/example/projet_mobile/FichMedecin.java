package com.example.projet_mobile;

import java.util.ArrayList;

public class FichMedecin extends Utilisateur {

    protected static ArrayList<FichMedecin> allMedecin = new ArrayList<>();

    public FichMedecin(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
        allMedecin.add(this);
    }
}