package org.example.jwtdemo.services;

import org.example.jwtdemo.domain.entities.Role;
import org.example.jwtdemo.domain.entities.RoleEnum;
import org.example.jwtdemo.domain.entities.User;
import org.example.jwtdemo.domain.repositories.RoleRepository;
import org.example.jwtdemo.domain.repositories.UserRepository;
import org.example.jwtdemo.dto.RegisterUserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(
      final UserRepository userRepository,
      final RoleRepository roleRepository,
      final PasswordEncoder passwordEncoder
  ) {
    this.userRepository = Objects.requireNonNull(userRepository);
    this.roleRepository = Objects.requireNonNull(roleRepository);
    this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
  }

  public List<User> findAll() {
    return (List<User>) userRepository.findAll();
  }

  public User createAdministrator(final RegisterUserDTO input) {
    Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

    if (optionalRole.isEmpty()) {
      return null;
    }

    var user = new User()
        .setEmail(input.email())
        .setPassword(passwordEncoder.encode(input.password()))
        .setRole(optionalRole.get());

    return userRepository.save(user);
  }
}
