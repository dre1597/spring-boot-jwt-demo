package org.example.jwtdemo.bootstrap;

import org.example.jwtdemo.domain.entities.Role;
import org.example.jwtdemo.domain.entities.RoleEnum;
import org.example.jwtdemo.domain.repositories.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
  private final RoleRepository roleRepository;

  public RoleSeeder(final RoleRepository roleRepository) {
    this.roleRepository = Objects.requireNonNull(roleRepository);
  }

  @Override
  public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
    this.loadRoles();
  }

  private void loadRoles() {
    var roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN };
    var roleDescriptionMap = Map.of(
        RoleEnum.USER, "Default user role",
        RoleEnum.ADMIN, "Administrator role",
        RoleEnum.SUPER_ADMIN, "Super Administrator role"
    );

    Arrays.stream(roleNames).forEach(roleName -> {
      var role = roleRepository.findByName(roleName);

      role.ifPresentOrElse(System.out::println, () -> {
        var roleToCreate = new Role();

        roleToCreate.setName(roleName)
            .setDescription(roleDescriptionMap.get(roleName));

        roleRepository.save(roleToCreate);
      });
    });
  }
}
