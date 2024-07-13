package org.example.jwtdemo.bootstrap;

import org.example.jwtdemo.domain.entities.RoleEnum;
import org.example.jwtdemo.domain.entities.User;
import org.example.jwtdemo.domain.repositories.RoleRepository;
import org.example.jwtdemo.domain.repositories.UserRepository;
import org.example.jwtdemo.dto.RegisterUserDTO;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  public AdminSeeder(
      final RoleRepository roleRepository,
      final UserRepository userRepository,
      final PasswordEncoder passwordEncoder
  ) {
    this.roleRepository = Objects.requireNonNull(roleRepository);
    this.userRepository = Objects.requireNonNull(userRepository);
    this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
  }

  @Override
  public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
    this.createSuperAdministrator();
  }

  private void createSuperAdministrator() {
    var userDto = new RegisterUserDTO("super.admin@email.com", "123456");
    var role = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
    var user = userRepository.findByEmail(userDto.email());

    if (role.isEmpty() || user.isPresent()) {
      return;
    }

    var newUser = new User()
        .setEmail(userDto.email())
        .setPassword(passwordEncoder.encode(userDto.password()))
        .setRole(role.get());

    userRepository.save(newUser);
  }
}
