package org.example;

import static spark.Spark.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        port(4567); // Démarre le serveur sur le port 4567

        // Initialisation de la base de données
        Database.init();

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
    }
}
