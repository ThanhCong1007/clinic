package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Id")
	private String id;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Account [Username=" + username + ", Password=" + password + ", Id=" + id + "]";
	}

	public Account(String username, String password, String id) {
		super();
		this.username = username;
		this.password = password;
		this.id = id;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
