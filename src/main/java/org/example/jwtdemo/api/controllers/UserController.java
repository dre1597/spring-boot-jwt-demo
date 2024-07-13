package org.example.jwtdemo.api.controllers;

import org.example.jwtdemo.api.UserAPI;
import org.example.jwtdemo.domain.entities.User;
import org.example.jwtdemo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController implements UserAPI {
  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = Objects.requireNonNull(userService);
  }

  @Override
  public ResponseEntity<User> authenticatedUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    var currentUser = (User) authentication.getPrincipal();

    return ResponseEntity.ok(currentUser);
  }

  @Override
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }
}
