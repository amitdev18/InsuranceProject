package protecons.insurance.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    private final long expiration;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration:3600000}") long expiration) {

        this.secretKey =
                Keys.hmacShaKeyFor(
                        secret.getBytes(
                                StandardCharsets.UTF_8
                        )
                );

        this.expiration = expiration;
    }

    public String generateToken(
            String userId,
            String email,
            String role) {

        Date issuedAt = new Date();

        Date expirationDate =
                new Date(
                        issuedAt.getTime() + expiration
                );

        return Jwts.builder()

                .subject(userId)

                .claim(
                        "email",
                        email
                )

                .claim(
                        "role",
                        role
                )

                .issuedAt(issuedAt)

                .expiration(expirationDate)

                .signWith(secretKey)

                .compact();
    }

    public Claims extractAllClaims(
            String token) {

        return Jwts.parser()

                .verifyWith(secretKey)

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }

    public String extractUserId(
            String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    public String extractEmail(
            String token) {

        return extractAllClaims(token)
                .get(
                        "email",
                        String.class
                );
    }

    public String extractRole(
            String token) {

        return extractAllClaims(token)
                .get(
                        "role",
                        String.class
                );
    }

    public boolean isTokenValid(
            String token) {

        try {

            Claims claims =
                    extractAllClaims(token);

            Date expirationDate =
                    claims.getExpiration();

            return expirationDate.after(
                    new Date()
            );

        } catch (Exception e) {

            return false;
        }
    }

    public long getExpirationInSeconds() {

        return expiration / 1000;
    }
}
