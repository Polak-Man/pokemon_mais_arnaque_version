package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ouais ça part ");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Saisir identifiant : ");
        String identifiant = scanner.nextLine();

        System.out.print("Saisir mot de passe : ");
        String motDePasse = scanner.nextLine();

        // Traitement des données utilisateur (ici, simple affichage)
        System.out.println("Identifiant : " + identifiant);
        System.out.println("Mot de passe : " + motDePasse);

        // Création d'une instance de Cartes
        Cartes carte1 = new Cartes("Monstre", "Rare", "Dragon Rouge");

        // Affichage des informations de la carte
        System.out.println(carte1);

        scanner.close();
    }
}