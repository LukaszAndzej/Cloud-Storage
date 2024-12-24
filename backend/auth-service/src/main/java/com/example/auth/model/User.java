package com.example.auth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

	@Column(unique = true, nullable = false)
  private String username;

	@Column(nullable = false)
  private String password;

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getUsername() {
      return username;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }
}
