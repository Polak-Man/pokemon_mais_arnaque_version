package org.example;

public class ApiResponse {
    private String status;
    private String message;
    private User user; // Ajout d'un attribut user pour renvoyer les informations de l'utilisateur
    private String token;

    // Constructeur sans 'user'
    public ApiResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Constructeur avec 'user'
    public ApiResponse(String status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ApiResponse(String success, String message, boolean admin) {
    }

    // Getters et Setters pour 'status', 'message' et 'user'
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}