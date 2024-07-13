package org.example.jwtdemo.services;

import org.example.jwtdemo.domain.entities.User;
import org.example.jwtdemo.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAll() {
    return (List<User>) userRepository.findAll();
  }
}
