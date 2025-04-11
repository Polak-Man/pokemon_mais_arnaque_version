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
        String query = "SELECT password, is_admin FROM usr WHERE usr_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                boolean isAdmin = rs.getBoolean("is_admin");
                String storedPassword = rs.getString("password");
                return {storedPassword.equals(user.getPassword()), isAdmin};
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la validation : " + e.getMessage());
            return false;
        }
    }

    public static User getUserByUsername(String username) {
        String query = "SELECT usr_id, password, is_admin FROM usr WHERE usr_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("usr_id"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("is_admin"));
                return user;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
        }
        return null; // Si l'utilisateur n'est pas trouvé, on retourne null
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
            if (rowsUpdated > 0) {
                return true;
            } else {
                System.err.println("User not found: " + username);
                return false; // L'utilisateur n'a pas été trouvé
            }
        } catch (SQLException e) {
            System.err.println("Error during user update: " + e.getMessage());
            return false; // Problème de base de données
        }
    }

    public static boolean updatePassword(String username, String newPassword) {
        String query = "UPDATE usr SET password = ? WHERE usr_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteUser(String username) {
        String checkQuery = "SELECT 1 FROM usr WHERE usr_id = ?";
        String deleteQuery = "DELETE FROM usr WHERE usr_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {

            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                System.err.println("User not found: " + username);
                return false; // L'utilisateur n'existe pas
            }

            deleteStmt.setString(1, username);
            int rowsDeleted = deleteStmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error during user deletion: " + e.getMessage());
            return false; // Problème de base de données
        }
    }

    public static List<PokemonCard> getAllPokemonCards() {
        List<PokemonCard> cards = new ArrayList<>();
        String query = "SELECT * FROM pokemon_cards";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                PokemonCard card = new PokemonCard();
                card.setCardId(rs.getInt("card_id"));
                card.setCardName(rs.getString("card_name"));
                card.setEdition(rs.getString("edition"));
                card.setSetName(rs.getString("set_name"));
                card.setCardType(rs.getString("card_type"));
                card.setHp(rs.getInt("hp"));
                card.setAttack(rs.getInt("attack"));
                card.setRarity(rs.getString("rarity"));
                card.setDescription(rs.getString("description"));
                cards.add(card);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des cartes : " + e.getMessage());
        }
        return cards;
    }

    public static PokemonCard getPokemonCardById(int cardId) {
        String query = "SELECT * FROM pokemon_cards WHERE card_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cardId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                PokemonCard card = new PokemonCard();
                card.setCardId(rs.getInt("card_id"));
                card.setCardName(rs.getString("card_name"));
                card.setEdition(rs.getString("edition"));
                card.setSetName(rs.getString("set_name"));
                card.setCardType(rs.getString("card_type"));
                card.setHp(rs.getInt("hp"));
                card.setAttack(rs.getInt("attack"));
                card.setRarity(rs.getString("rarity"));
                card.setDescription(rs.getString("description"));
                return card;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la carte : " + e.getMessage());
        }
        return null;
    }

    public static boolean addPokemonCard(PokemonCard card) {
        String query = "INSERT INTO pokemon_cards (card_name, edition, set_name, card_type, hp, attack, rarity, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, card.getCardName());
            stmt.setString(2, card.getEdition());
            stmt.setString(3, card.getSetName());
            stmt.setString(4, card.getCardType());
            stmt.setInt(5, card.getHp());
            stmt.setInt(6, card.getAttack());
            stmt.setString(7, card.getRarity());
            stmt.setString(8, card.getDescription());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la carte : " + e.getMessage());
            return false;
        }
    }

    public static boolean updatePokemonCard(int cardId, PokemonCard card) {
        String query = "UPDATE pokemon_cards SET card_name=?, edition=?, set_name=?, card_type=?, hp=?, attack=?, rarity=?, description=? WHERE card_id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, card.getCardName());
            stmt.setString(2, card.getEdition());
            stmt.setString(3, card.getSetName());
            stmt.setString(4, card.getCardType());
            stmt.setInt(5, card.getHp());
            stmt.setInt(6, card.getAttack());
            stmt.setString(7, card.getRarity());
            stmt.setString(8, card.getDescription());
            stmt.setInt(9, cardId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de la carte : " + e.getMessage());
            return false;
        }
    }

    public static boolean deletePokemonCard(int cardId) {
        String query = "DELETE FROM pokemon_cards WHERE card_id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cardId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la carte : " + e.getMessage());
            return false;
        }
    }

    public static boolean addPokemonToUserCollection(String username, int cardId) {
        String query = "INSERT INTO user_pokemon_cards (user_id, card_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setInt(2, cardId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la carte à la collection de l'utilisateur : " + e.getMessage());
            return false;
        }
    }

    public static boolean removePokemonFromUserCollection(String username, int cardId) {
        String query = "DELETE FROM user_pokemon_cards WHERE user_id = ? AND card_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setInt(2, cardId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors du retrait de la carte de la collection de l'utilisateur : " + e.getMessage());
            return false;
        }
    }

    public static List<PokemonCard> getUserPokemonCards(String username) {
        List<PokemonCard> cards = new ArrayList<>();
        String query = "SELECT pc.* FROM pokemon_cards pc JOIN user_pokemon_cards upc ON pc.card_id = upc.card_id WHERE upc.user_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PokemonCard card = new PokemonCard();
                    card.setCardId(rs.getInt("card_id"));
                    card.setCardName(rs.getString("card_name"));
                    card.setEdition(rs.getString("edition"));
                    card.setSetName(rs.getString("set_name"));
                    card.setCardType(rs.getString("card_type"));
                    card.setHp(rs.getInt("hp"));
                    card.setAttack(rs.getInt("attack"));
                    card.setRarity(rs.getString("rarity"));
                    card.setDescription(rs.getString("description"));
                    cards.add(card);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des cartes de l'utilisateur : " + e.getMessage());
        }
        return cards;
    }

}
