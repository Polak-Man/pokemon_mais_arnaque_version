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
            try {
                boolean isAdmin = new Gson().fromJson(req.body(), User.class).isAdmin();
                boolean success = Database.updateUser(username, isAdmin);

                if (success) {
                    return new Gson().toJson(new ApiResponse("success", "User updated successfully!"));
                } else {
                    res.status(400);
                    return new Gson().toJson(new ApiResponse("error", "Failed to update user. User not found or database issue."));
                }
            } catch (Exception e) {
                res.status(500);
                return new Gson().toJson(new ApiResponse("error", "An unexpected error occurred: " + e.getMessage()));
            }
        });

        // Route pour mettre à jour un mot de passe
        put("/users/:username/password", (req, res) -> {
            res.type("application/json");
            String username = req.params("username");
            String currentUsername = req.headers("Username");

            // Vérifie si l'utilisateur est admin ou met à jour son propre mot de passe
            boolean isAdmin = Database.isAdmin(currentUsername);
            if (!isAdmin && !currentUsername.equals(username)) {
                res.status(403);
                return new Gson().toJson(new ApiResponse("error", "Forbidden: Only the user or an admin can update the password"));
            }

            User requestUser = new Gson().fromJson(req.body(), User.class);

            // Met à jour le mot de passe dans la base de données
            boolean success = Database.updatePassword(username, requestUser.getNewPassword());
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "Password updated successfully!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "Password update failed!"));
            }
        });


        delete("/users/:username", (req, res) -> {
            res.type("application/json");
            String username = req.params("username");
            try {
                boolean success = Database.deleteUser(username);

                if (success) {
                    return new Gson().toJson(new ApiResponse("success", "User deleted successfully!"));
                } else {
                    res.status(400);
                    return new Gson().toJson(new ApiResponse("error", "Failed to delete user. User not found or database issue."));
                }
            } catch (Exception e) {
                res.status(500);
                return new Gson().toJson(new ApiResponse("error", "An unexpected error occurred: " + e.getMessage()));
            }
        });

        // Route pour ajouter une carte Pokémon
        post("/pokemon", (req, res) -> {
            res.type("application/json");
            PokemonCard card = new Gson().fromJson(req.body(), PokemonCard.class);
            boolean success = Database.addPokemonCard(card);
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "Pokemon card added successfully!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "Failed to add Pokemon card!"));
            }
        });

        // Route pour mettre à jour une carte Pokémon
        put("/pokemon/:cardId", (req, res) -> {
            res.type("application/json");
            int cardId = Integer.parseInt(req.params("cardId"));
            PokemonCard card = new Gson().fromJson(req.body(), PokemonCard.class);
            boolean success = Database.updatePokemonCard(cardId, card);
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "Pokemon card updated successfully!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "Failed to update Pokemon card!"));
            }
        });

        // Route pour supprimer une carte Pokémon
        delete("/pokemon/:cardId", (req, res) -> {
            res.type("application/json");
            int cardId = Integer.parseInt(req.params("cardId"));
            boolean success = Database.deletePokemonCard(cardId);
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "Pokemon card deleted successfully!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "Failed to delete Pokemon card!"));
            }
        });

        // Route pour obtenir toutes les cartes Pokémon
        get("/pokemon", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(Database.getAllPokemonCards());
        });


        // Route pour obtenir une carte Pokémon par son ID
        get("/pokemon/:cardId", (req, res) -> {
            res.type("application/json");
            int cardId = Integer.parseInt(req.params("cardId"));
            PokemonCard card = Database.getPokemonCardById(cardId);
            if (card != null) {
                return new Gson().toJson(card);
            } else {
                res.status(404);
                return new Gson().toJson(new ApiResponse("error", "Pokemon card not found!"));
            }
        });

    }
}
