package com.mmd.hr.dto.user;

import com.mmd.hr.entity.authentication.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDTO {
	@Valid
	private User user;

	@Size(min = 1, message = "Must select at least one")
	private List<String> rolesNames;

	public UserDTO(User user, List<String> rolesNames) {
		this.user = user;
		this.rolesNames = rolesNames;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getRolesNames() {
		return rolesNames;
	}

	public void setRolesNames(List<String> rolesNames) {
		this.rolesNames = rolesNames;
	}
}
