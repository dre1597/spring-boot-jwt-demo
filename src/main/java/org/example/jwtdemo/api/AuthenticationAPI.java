package org.example.jwtdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.jwtdemo.domain.entities.User;
import org.example.jwtdemo.dto.LoginResponseDTO;
import org.example.jwtdemo.dto.LoginUserDTO;
import org.example.jwtdemo.dto.RegisterUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/auth")
@Tag(name = "Authentication")
public interface AuthenticationAPI {
  @PostMapping("/signup")
  @Operation(summary = "Register a new user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Register a new user"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  ResponseEntity<User> register(@RequestBody final RegisterUserDTO registerUserDto);

  @PostMapping("/login")
  @Operation(summary = "Authenticate a user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Authenticate a user"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  ResponseEntity<LoginResponseDTO> authenticate(@RequestBody final LoginUserDTO loginUserDto);
}
