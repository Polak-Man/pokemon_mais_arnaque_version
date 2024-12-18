package org.example.models;

public class AuthResult {
    private boolean authenticated;
    private boolean isAdmin;

    public AuthResult(boolean authenticated, boolean isAdmin) {
        this.authenticated = authenticated;
        this.isAdmin = isAdmin;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}