package ru.carsalon.configuration;

public record JwtConfig(
    String secret,
    Long expiration,
    String headerName
) {
}
