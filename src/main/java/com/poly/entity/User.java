package com.poly.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
@NamedQueries({
		@NamedQuery(name = "User.findByIdOrEmail", query = "SELECT u FROM User u WHERE u.id = :idOrEmail OR u.email = :idOrEmail") })
public class User {
	@Id
	private String id;
	private String password;
	private String fullname;
	private String email;
	private Boolean admin = false;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Favorite> favorites;

	public User() {
	}

	public User(String id, String password, String fullname, String email, Boolean admin, List<Favorite> favorites) {
		this.id = id;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.admin = admin;
		this.favorites = favorites;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
	
}
