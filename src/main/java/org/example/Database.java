package org.example;
import java.util.HashMap;

public class Database {
    private static HashMap<String, String> users = new HashMap<>();

    public static void init() {
        // Exemple d'utilisateur déjà inscrit
        users.put("test_user", "password123");
    }

    public static boolean registerUser(User user) {
        if (users.containsKey(user.getUsername())) {
            return false; // Utilisateur existe déjà
        }
        users.put(user.getUsername(), user.getPassword());
        return true;
    }

    public static boolean validateUser(User user) {
        return users.containsKey(user.getUsername()) &&
                users.get(user.getUsername()).equals(user.getPassword());
    }
}
