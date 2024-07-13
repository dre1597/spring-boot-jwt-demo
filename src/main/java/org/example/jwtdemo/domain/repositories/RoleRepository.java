package org.example.jwtdemo.domain.repositories;

import org.example.jwtdemo.domain.entities.Role;
import org.example.jwtdemo.domain.entities.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
  Optional<Role> findByName(RoleEnum name);
}
