package org.example.jwtdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.jwtdemo.domain.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value="/users")
@Tag(name = "User")
public interface UserAPI {

  @GetMapping(value = "/me")
  @PreAuthorize("isAuthenticated()")
  @Operation(summary = "Get the authenticated user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get the authenticated user"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  ResponseEntity<User> authenticatedUser();

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
  @Operation(summary = "Get all users")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Get all users"),
      @ApiResponse(responseCode = "401", description = "Unauthorized"),
      @ApiResponse(responseCode = "403", description = "Forbidden"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  ResponseEntity<List<User>> findAll();
}
