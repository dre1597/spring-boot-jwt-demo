package org.example.jwtdemo.dto;

public record LoginResponseDTO(
    String  token,
    long expiresIn
) {
}
