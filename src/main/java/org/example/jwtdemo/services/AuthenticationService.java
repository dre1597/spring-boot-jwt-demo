package org.example.jwtdemo.services;

import org.example.jwtdemo.domain.entities.RoleEnum;
import org.example.jwtdemo.domain.entities.User;
import org.example.jwtdemo.domain.repositories.RoleRepository;
import org.example.jwtdemo.domain.repositories.UserRepository;
import org.example.jwtdemo.dto.LoginUserDTO;
import org.example.jwtdemo.dto.RegisterUserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(
    final UserRepository userRepository,
    final RoleRepository roleRepository,
    final PasswordEncoder passwordEncoder,
    final AuthenticationManager authenticationManager
  ) {
    this.userRepository = Objects.requireNonNull(userRepository);
    this.roleRepository = Objects.requireNonNull(roleRepository);
    this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
    this.authenticationManager = Objects.requireNonNull(authenticationManager);
  }

  public User signup(RegisterUserDTO input) {
    var role = roleRepository.findByName(RoleEnum.USER);

    if(role.isEmpty()) {
      return null;
    }

    var user = new User()
        .setEmail(input.email())
        .setPassword(passwordEncoder.encode(input.password()))
        .setRole(role.get());

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
