package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pkmarn";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    static {
        // Charger le driver JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC non trouvé !");
            e.printStackTrace();
        }
    }

    public static boolean registerUser(User user) {
        String query = "INSERT INTO usr (usr_id, password, is_admin) VALUES (?, ?, 0)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'inscription : " + e.getMessage());
            return false;
        }
    }

    public static boolean validateUser(User user) {
        String query = "SELECT password FROM usr WHERE usr_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(user.getPassword());
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la validation : " + e.getMessage());
            return false;
        }
    }

    public static boolean isAdmin(String username) {
        String query = "SELECT is_admin FROM usr WHERE usr_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("is_admin");
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'administrateur : " + e.getMessage());
            return false;
        }
    }

    public static List<User> getAllUsers() {
        String query = "SELECT usr_id, is_admin FROM usr";
        List<User> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("usr_id"));
                user.setAdmin(rs.getBoolean("is_admin"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }
        return users;
    }

    public static boolean updateUser(String username, boolean isAdmin) {
        String query = "UPDATE usr SET is_admin = ? WHERE usr_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, isAdmin);
            stmt.setString(2, username);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteUser(String username) {
        String query = "DELETE FROM usr WHERE usr_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            return false;
        }
    }
}
