package com.mmd.hr.entity.authentication;

import com.mmd.hr.validation.MinusOne;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

	@Id
	@NotBlank(message = "username is required")
	private String username;

	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=])[a-zA-Z0-9!@#$%^&*()-_+=]{8,}$",
			message = "Must be at least 8 characters, including at least one upper-case, one number and one special character")
	private String password;

	@Column(name = "enabled")
	private int active;

	@Column(name = "date_created")
	private LocalDate dateCreated;

	public User() {}

	public User(boolean active){
		if(active){
			this.active = 1;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_roles",
			joinColumns = {@JoinColumn(name = "username")},
			inverseJoinColumns = { @JoinColumn(name = "role") }
	)
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
