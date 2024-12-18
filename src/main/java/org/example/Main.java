package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.example.models.AuthResult; // Assurez-vous que cette classe est dans le bon package
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
        AuthResult authResult = authenticateUser (identifiant, motDePasse);
        if (authResult.isAuthenticated()) {
            if (authResult.isAdmin()) {
                System.out.println("Connecté en tant qu'Admin !");
            } else {
                System.out.println("Connecté en utilisateur lambda cheh !");
            }
            // Création d'une instance de Cartes (si nécessaire)
            // Cartes carte1 = new Cartes("Monstre", "Rare", "Dragon Rouge");
            // Affichage des informations de la carte
            // System.out.println(carte1);
        } else {
            System.out.println("Erreur : Identifiant ou mot de passe incorrect.");
        }

        scanner.close();
    }

    private static AuthResult authenticateUser (String identifiant, String motDePasse) {
        String query = "SELECT is_admin FROM usr WHERE usr_id = ? AND password = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, identifiant);
            statement.setString(2, motDePasse);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // Si un utilisateur est trouvé
                boolean isAdmin = resultSet.getBoolean("is_admin");
                return new AuthResult(true, isAdmin); // Authentification réussie
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
        return new AuthResult(false, false); // Authentification échouée
    }
}