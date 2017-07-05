package com.eric.monitoringserverjava.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 *
 */
@Document
public class User {
	@Id
	private String id;
	private String name;
	private String password;
	private List<Role> roles;

	public enum Role {ADMIN, USER, GUEST}

	public User () {
	}

	public User (String id, String name, String password, List<Role> roles) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	public List<Role> getRoles () {
		return roles;
	}

	public void setRoles (List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString () {
		return "User{" +
		  "id='" + id + '\'' +
		  ", name='" + name + '\'' +
		  ", password='" + password + '\'' +
		  ", roles=" + roles +
		  '}';
	}
}
