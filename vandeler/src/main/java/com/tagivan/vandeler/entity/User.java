package com.tagivan.vandeler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;
	
	private String name;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(unique = true, nullable = false)
	private String password;
	
	private UserRoles roles;
	
	@Column(columnDefinition = "TEXT")
	private String profileUrl;
	
	//Constructors
	public User() {
	}

	public User(Long id, String name, String email, String password, String profileUrl, UserRoles roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.profileUrl = profileUrl;
		this.roles = roles;
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	
	public UserRoles getRoles() {
		return roles;
	}
	public void setRoles(UserRoles roles) {
		this.roles = roles;
	}
	
}
