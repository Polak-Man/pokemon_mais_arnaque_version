package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.example.models.Cartes;
import org.example.util.Database;


public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue sur la page de connexion");

        Scanner scanner = new Scanner(System.in);

        // Demande de l'identifiant
        System.out.print("Saisir identifiant : ");
        String identifiant = scanner.nextLine();

        // Demande du mot de passe
        System.out.print("Saisir mot de passe : ");
        String motDePasse = scanner.nextLine();

        // Vérification des informations d'identification
        if (authenticateUser (identifiant, motDePasse)) {
            System.out.println("Connexion réussie !");
            // Création d'une instance de Cartes (si nécessaire)
            Cartes carte1 = new Cartes("Monstre", "Rare", "Dragon Rouge");
            // Affichage des informations de la carte
            System.out.println(carte1);
        } else {
            System.out.println("Erreur : Identifiant ou mot de passe incorrect.");
        }

        scanner.close();
    }

    private static boolean authenticateUser (String identifiant, String motDePasse) {
        String query = "SELECT * FROM usr WHERE usr_id = ? AND password = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, identifiant);
            statement.setString(2, motDePasse);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Si un utilisateur est trouvé, retourne true
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            return false;
        }
    }
}