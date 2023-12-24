package ru.carsalon.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.carsalon.configuration.ApplicationConfig;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final ApplicationConfig config;

    public String getJwtFromHeader(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(config.jwt().headerName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(config.jwt().secret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(config.jwt().secret()).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.out.println("Generate JWT token is failed: " + e.getMessage());
        }

        return false;
    }

    public String generateTokenFromEmail(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, config.jwt().secret())
                .compact();
    }
}
