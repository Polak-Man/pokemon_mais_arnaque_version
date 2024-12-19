package org.example;

import static spark.Spark.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        port(4567); // Démarre le serveur sur le port 4567

        // Middleware : Vérifie si un utilisateur est admin avant les requêtes sensibles
        before("/users/*", (req, res) -> {
            String username = req.headers("Username");
            if (username == null || !Database.isAdmin(username)) {
                halt(403, new Gson().toJson(new ApiResponse("error", "Forbidden: Admin access required")));
            }
        });

        // Route pour l'inscription
        post("/register", (req, res) -> {
            res.type("application/json");
            User user = new Gson().fromJson(req.body(), User.class);
            boolean success = Database.registerUser(user);
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "User registered successfully!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "User already exists or invalid data!"));
            }
        });

        // Route pour la connexion
        post("/login", (req, res) -> {
            res.type("application/json");
            User user = new Gson().fromJson(req.body(), User.class);
            boolean isValid = Database.validateUser(user);
            if (isValid) {
                return new Gson().toJson(new ApiResponse("success", "Login successful!"));
            } else {
                res.status(401);
                return new Gson().toJson(new ApiResponse("error", "Invalid username or password!"));
            }
        });

        // Route pour vérifier si un utilisateur est administrateur
        get("/is-admin/:username", (req, res) -> {
            res.type("application/json");
            String username = req.params("username");
            boolean isAdmin = Database.isAdmin(username);
            return new Gson().toJson(new ApiResponse("success", "Is admin: " + isAdmin));
        });

        // CRUD - Gestion des utilisateurs (Admins uniquement)
        get("/users", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(Database.getAllUsers());
        });

        put("/users/:username", (req, res) -> {
            res.type("application/json");
            String username = req.params("username");
            boolean isAdmin = new Gson().fromJson(req.body(), User.class).isAdmin();
            boolean success = Database.updateUser(username, isAdmin);
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "User updated successfully!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "User update failed!"));
            }
        });

        delete("/users/:username", (req, res) -> {
            res.type("application/json");
            String username = req.params("username");
            boolean success = Database.deleteUser(username);
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "User deleted successfully!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "User deletion failed!"));
            }
        });
    }
}
