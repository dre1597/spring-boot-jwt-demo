package org.example.jwtdemo.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "roles")
@Entity
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private Integer id;

  @Column(unique = true, nullable = true)
  @Enumerated(EnumType.STRING)
  private RoleEnum name;

  @Column(nullable = false)
  private String description;

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  public Integer getId() {
    return id;
  }

  public Role setId(final Integer id) {
    this.id = id;
    return this;
  }

  public RoleEnum getName() {
    return name;
  }

  public Role setName(final RoleEnum name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Role setDescription(final String description) {
    this.description = description;
    return this;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Role setCreatedAt(final Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public Role setUpdatedAt(final Date updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }
}
