package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import static spark.Spark.halt;
import static spark.Spark.before;

public class AuthMiddleware {

    private static final String SECRET_KEY = "mysecretkey123456"; // Changez cela pour une clé plus sécurisée

    public static void authenticate() {
        before("/admin/*", (req, res) -> {
            String token = req.headers("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                halt(401, "Unauthorized: No token provided");
            }

            token = token.substring(7); // Enlève "Bearer " du token
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();
                // Vous pouvez récupérer le nom d'utilisateur et le rôle ici si nécessaire
                String username = claims.getSubject();
                boolean isAdmin = claims.get("isAdmin", Boolean.class);
                // Vous pouvez stocker ces informations dans le contexte de la requête si nécessaire
                req.attribute("username", username);
                req.attribute("isAdmin", isAdmin);
            } catch (Exception e) {
                halt(401, "Unauthorized: Invalid token");
            }
        });
    }
}