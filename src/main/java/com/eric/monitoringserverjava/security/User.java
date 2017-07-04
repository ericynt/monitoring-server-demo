package com.eric.monitoringserverjava.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document
public class User {
	@Id
	private String id;
	private String name;
	private String password;

	public User () {
	}

	public User (String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString () {
		return "User{" +
		  "id='" + id + '\'' +
		  ", name='" + name + '\'' +
		  ", password='" + password + '\'' +
		  '}';
	}
}
