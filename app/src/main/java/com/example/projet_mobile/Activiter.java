package com.example.projet_mobile;
public class Activiter {

    private String titre;
    private String description;
    private int duree;
    private Structure structure;

    public Activiter(String titre, String description, int duree){
        this.titre = titre;
        this.description = description;
        this.duree = duree;
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

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }
}