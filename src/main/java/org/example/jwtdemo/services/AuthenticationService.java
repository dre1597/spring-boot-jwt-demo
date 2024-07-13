package org.example.jwtdemo.services;

import org.example.jwtdemo.domain.entities.User;
import org.example.jwtdemo.domain.repositories.UserRepository;
import org.example.jwtdemo.dto.LoginUserDTO;
import org.example.jwtdemo.dto.RegisterUserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(
    UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    AuthenticationManager authenticationManager
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  public User signup(RegisterUserDTO input) {
    var user = new User()
        .setEmail(input.email())
        .setPassword(passwordEncoder.encode(input.password()));

    return userRepository.save(user);
  }

  public User authenticate(LoginUserDTO input) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            input.email(),
            input.password()
        )
    );

    return userRepository
        .findByEmail(input.email())
        .orElseThrow();
  }
}
