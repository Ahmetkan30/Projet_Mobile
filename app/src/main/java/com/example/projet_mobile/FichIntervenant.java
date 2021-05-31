package com.example.projet_mobile;
import java.util.ArrayList;

public class FichIntervenant extends Utilisateur {

    protected static ArrayList<FichIntervenant> allIntervenant = new ArrayList<>();

    public FichIntervenant(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
        allIntervenant.add(this);
    }

}