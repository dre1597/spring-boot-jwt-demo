package org.example.jwtdemo.api.controllers;

import org.example.jwtdemo.api.AuthenticationAPI;
import org.example.jwtdemo.domain.entities.User;
import org.example.jwtdemo.dto.LoginResponseDTO;
import org.example.jwtdemo.dto.LoginUserDTO;
import org.example.jwtdemo.dto.RegisterUserDTO;
import org.example.jwtdemo.services.AuthenticationService;
import org.example.jwtdemo.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AuthenticationController implements AuthenticationAPI {
  private final JwtService jwtService;
  private final AuthenticationService authenticationService;

  public AuthenticationController(
      final JwtService jwtService,
      final AuthenticationService authenticationService
  ) {
    this.jwtService = Objects.requireNonNull(jwtService);
    this.authenticationService = Objects.requireNonNull(authenticationService);
  }

  @Override
  public ResponseEntity<User> register(final RegisterUserDTO registerUserDto) {
    User registeredUser = authenticationService.signup(registerUserDto);

    return ResponseEntity.ok(registeredUser);
  }

  @Override
  public ResponseEntity<LoginResponseDTO> authenticate(final LoginUserDTO loginUserDto) {
    var authenticatedUser = authenticationService.authenticate(loginUserDto);
    var jwtToken = jwtService.generateToken(authenticatedUser);
    var loginResponse = new LoginResponseDTO(jwtToken, jwtService.getExpirationTime());

    return ResponseEntity.ok(loginResponse);
  }
}
