package com.example.projet_mobile;
import androidx.annotation.NonNull;

import java.util.Calendar;

public class Seance {

    private Activiter activite;
    private Calendar debut;
    private Calendar fin;

    public Seance(Activiter activite, Calendar debut){
        this.activite = activite;
        this.debut = debut;
        int hours = this.activite.getDuree() / 60;
        int minutes = this.activite.getDuree() % 60;
        this.fin = (Calendar) debut.clone();
        this.fin.add(Calendar.HOUR, hours);
        this.fin.add(Calendar.MINUTE, minutes);
    }

    public Activiter getActivite() {
        return activite;
    }

    public void setActivite(Activiter activite) {
        this.activite = activite;
    }

    public Calendar getDebut() {
        return debut;
    }

    public void setDebut(Calendar debut) {
        this.debut = debut;
        int hours = this.activite.getDuree() / 60;
        int minutes = this.activite.getDuree() % 60;
        this.fin = (Calendar) debut.clone();
        this.fin.add(Calendar.HOUR, hours);
        this.fin.add(Calendar.MINUTE, minutes);
    }

    public Calendar getFin() {
        return fin;
    }

    public void setFin(Calendar fin) {
        this.fin = fin;
    }

    @NonNull
    public String toString(){
        return activite+", "+ debut +", "+ fin;
    }
}