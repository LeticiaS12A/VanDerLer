package com.tagivan.vandeler.dto;

import com.tagivan.vandeler.entity.User;
import com.tagivan.vandeler.entity.UserRoles;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDTO {

	private Long id;
	
	@NotBlank(message = "Nome é obrigatório.")
	private String name;
	
	@NotBlank(message = "Email é obrigatório.")
    @Email(message = "Email inválido.")
    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
        message = "E-mail inválido. Use um formato válido, ex: exemplo@email.com"
    )
	private String email;
	
	@NotBlank(message = "Senha é obrigatória.")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRoles roles;
	
	private String profileUrl;
	
	//Constructors
	public UserDTO() {
	}

	public UserDTO(Long id, @NotBlank(message = "Nome é obrigatório.") String name,
			@NotBlank(message = "Email é obrigatório.") @Email(message = "Email inválido.") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "E-mail inválido. Use um formato válido, ex: exemplo@email.com") String email,
			@NotBlank(message = "Senha é obrigatória.") String password, UserRoles roles, String profileUrl) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.profileUrl = profileUrl;
	}
	public UserDTO(User u) {
		id = u.getId();
		name = u.getName();
		email = u.getEmail();
		password = u.getPassword();
		roles = u.getRoles();
		profileUrl = u.getProfileUrl();
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

	public UserRoles getRoles() {
		return roles;
	}
	public void setRoles(UserRoles roles) {
		this.roles = roles;
	}

	public String getProfileUrl() {
		return profileUrl;
	}
	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
}
