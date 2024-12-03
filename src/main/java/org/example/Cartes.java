package org.example;

public class Cartes {
    // Attributs
    private String type;
    private String rarete;
    private String nom;

    // Constructeur
    public Cartes(String type, String rarete, String nom) {
        this.type = type;
        this.rarete = rarete;
        this.nom = nom;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getRarete() {
        return rarete;
    }

    public String getNom() {
        return nom;
    }

    // Setters
    public void setType(String type) {
        this.type = type;
    }

    public void setRarete(String rarete) {
        this.rarete = rarete;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Carte{" +
                "type='" + type + '\'' +
                ", rarete='" + rarete + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}