package org.example.jwtdemo.api.controllers;

import org.example.jwtdemo.api.AdminAPI;
import org.example.jwtdemo.domain.entities.User;
import org.example.jwtdemo.dto.RegisterUserDTO;
import org.example.jwtdemo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AdminController implements AdminAPI {
  private final UserService userService;

  public AdminController(final UserService userService) {
    this.userService = Objects.requireNonNull(userService);
  }

  @Override
  public ResponseEntity<User> createAdministrator(RegisterUserDTO registerUserDto) {
    var createdAdmin = userService.createAdministrator(registerUserDto);

    return ResponseEntity.ok(createdAdmin);
  }
}
