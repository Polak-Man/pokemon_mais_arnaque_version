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
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization, Username");
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
        // Route pour la connexion
        post("/login", (req, res) -> {
            res.type("application/json");
            User user = new Gson().fromJson(req.body(), User.class);

            // Vérification des données reçues
            System.out.println("Tentative de connexion pour l'utilisateur : " + user.getUsername());

            // Appel à la méthode de validation modifiée qui renvoie un objet UserValidationResult
            Database.UserValidationResult validationResult = Database.validateUser(user);

            // Vérification de la validité du mot de passe
            if (validationResult.isPasswordValid()) {
                // Log si le mot de passe est valide
                System.out.println("Mot de passe valide pour l'utilisateur : " + user.getUsername());

                // Récupérer l'utilisateur avec son ID
                user = Database.getUserByUsername(user.getUsername()); // Récupérer l'utilisateur depuis la base de données, incluant l'ID

                // Renvoyer une réponse JSON avec l'information sur l'utilisateur, y compris son ID et son rôle (isAdmin)
                ApiResponse response = new ApiResponse("success", "Login successful!", user);
                response.getUser().setAdmin(validationResult.isAdmin());  // Ajout du statut admin à la réponse

                // Inclure l'ID de l'utilisateur dans la réponse
                response.getUser().setId(user.getId());  // Assurez-vous que la méthode `getId()` existe sur l'objet User

                return new Gson().toJson(response);  // Sérialisation correcte de l'objet
            } else {
                // Log en cas d'échec
                System.out.println("Échec de la validation du mot de passe pour l'utilisateur : " + user.getUsername());
                res.status(401); // Code de statut HTTP 401 pour une erreur d'authentification
                ApiResponse response = new ApiResponse("error", "Invalid username or password!");
                return new Gson().toJson(response);  // Sérialisation correcte de l'objet
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

        // Route pour changer le nom d'utilisateur
        put("/users/:username/change-name", (req, res) -> {
            res.type("application/json");
            String currentUsername = req.headers("Username"); // Nom d'utilisateur de l'utilisateur authentifié
            String usernameToUpdate = req.params("username"); // Nom d'utilisateur à modifier
            String newUsername = new Gson().fromJson(req.body(), User.class).getUsername(); // Nouveau nom

            // Vérifier que l'utilisateur est soit l'utilisateur lui-même, soit un administrateur
            boolean isAdmin = Database.isAdmin(currentUsername);
            if (!currentUsername.equals(usernameToUpdate) && !isAdmin) {
                res.status(403); // Accès interdit
                return new Gson().toJson(new ApiResponse("error", "Forbidden: Only the user or an admin can change the username"));
            }

            // Vérification si le nouveau nom d'utilisateur est déjà pris
            boolean usernameExists = Database.usernameExists(newUsername);
            if (usernameExists) {
                res.status(400); // Mauvaise requête
                return new Gson().toJson(new ApiResponse("error", "Username already exists"));
            }

            // Mise à jour du nom d'utilisateur dans la base de données
            boolean success = Database.updateUsername(usernameToUpdate, newUsername);
            if (success) {
                return new Gson().toJson(new ApiResponse("success", "Username updated successfully!"));
            } else {
                res.status(400); // Mauvaise requête
                return new Gson().toJson(new ApiResponse("error", "Failed to update username"));
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

            // Ajouter la carte au joueur (en tenant compte du username)
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

            // Supprimer la carte de la collection du joueur
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

            // Récupérer toutes les cartes du joueur
            List<PokemonCard> userCards = Database.getUserPokemonCards(username);
            return new Gson().toJson(userCards);
        });

    }
}
