package org.example;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        port(4567); // Démarre le serveur sur le port 4567

        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "http://localhost:5173"); // Frontend URL
            res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        });

        // Gestion des OPTIONS pour les pré-vols CORS
        options("/*", (request, response) -> {
            response.status(200);
            return "OK";
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
            Database.UserValidationResult isValid = Database.validateUser(user);
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

            boolean isAdmin = Database.isAdmin(currentUsername);
            if (!isAdmin && !currentUsername.equals(username)) {
                res.status(403);
                return new Gson().toJson(new ApiResponse("error", "Forbidden: Only the user or an admin can update the password"));
            }

            User requestUser = new Gson().fromJson(req.body(), User.class);
            boolean success = Database.updatePassword(username, requestUser.getNewPassword());

            if (success) {
                return new Gson().toJson(new ApiResponse("success", "Password updated successfully!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "Password update failed!"));
            }
        });

        // Route pour supprimer un utilisateur
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

        // Pokémon routes
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

        get("/pokemon", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(Database.getAllPokemonCards());
        });

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

        post("/user/:username/pokemon/:cardId", (req, res) -> {
            res.type("application/json");
            String username = req.params("username");
            int cardId = Integer.parseInt(req.params("cardId"));

            boolean success = Database.addPokemonToUserCollection(username, cardId);
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "Pokemon card added to user collection!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "Failed to add Pokemon card to user collection!"));
            }
        });

        delete("/user/:username/pokemon/:cardId", (req, res) -> {
            res.type("application/json");
            String username = req.params("username");
            int cardId = Integer.parseInt(req.params("cardId"));

            boolean success = Database.removePokemonFromUserCollection(username, cardId);
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "Pokemon card removed from user collection!"));
            } else {
                res.status(400);
                return new Gson().toJson(new ApiResponse("error", "Failed to remove Pokemon card from user collection!"));
            }
        });

        get("/user/:username/pokemon", (req, res) -> {
            res.type("application/json");
            String username = req.params("username");

            List<PokemonCard> userCards = Database.getUserPokemonCards(username);
            return new Gson().toJson(userCards);
        });
    }
}
