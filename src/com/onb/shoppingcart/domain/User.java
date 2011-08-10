package com.onb.shoppingcart.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User extends AbstractModel{

	@ManyToMany
	@JoinTable(name = "User_Role",
			joinColumns = { @JoinColumn(name = "user_id")},
			inverseJoinColumns = { @JoinColumn(name = "role_id")})
	private Role role;
	
	private String username;
	
	private String password;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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
	
	
}
