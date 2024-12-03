package org.example.util;

import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/pkmarn"; // Modifiez selon vos besoins
    private static final String USER = "root"; // Modifiez par votre utilisateur
    private static final String PASSWORD = "root"; // Modifiez par votre mot de passe

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC non trouvé : " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Méthode pour récupérer tous les utilisateurs
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT pseudo, pwd, is_admin FROM users";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String pseudo = rs.getString("pseudo");
                String pwd = rs.getString("pwd");
                boolean isAdmin = rs.getBoolean("is_admin");

                users.add(new User(pseudo, pwd, isAdmin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }

        return users;
    }

    // Méthode pour ajouter un utilisateur
    public static void addUser(User user) {
        String query = "INSERT INTO users (pseudo, pwd, is_admin) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getPseudo());
            stmt.setString(2, user.getPwd());
            stmt.setBoolean(3, user.isAdmin());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'ajout d'un utilisateur : " + e.getMessage());
        }
    }

    // Méthode pour supprimer un utilisateur par pseudo
    public static void deleteUser(String pseudo) {
        String query = "DELETE FROM users WHERE pseudo = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, pseudo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression d'un utilisateur : " + e.getMessage());
        }
    }
}
