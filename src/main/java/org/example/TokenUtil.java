package org.example;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenUtil {
    private static final String SECRET_KEY = "mysecretkey123456"; // Assurez-vous que cette clé est définie
    private static final long EXPIRATION_TIME = 86400000; // 1 jour en millisecondes

    public static String generateToken(String username, boolean isAdmin) {
        System.out.println("Clé secrète utilisée : " + SECRET_KEY); // Log pour débogage
        return Jwts.builder()
                .setSubject(username)
                .claim("isAdmin", isAdmin)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean isAdmin(String token) {
        return (boolean) Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("isAdmin");
    }
}