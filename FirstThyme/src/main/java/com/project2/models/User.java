package com.project2.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="first_name",nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="balance",nullable=false)
	private double balance;
	
	@Column(name="username", nullable=false, unique=true)
	private String username;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="cart_id",nullable=false)
	private Cart cart;
	
	@Column(name ="user_role")
	private String userRole;

	//CREATE USER CONSTRUCTOR/signup
	public User(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = "" + firstName + lastName;
		this.email = email;
		this.balance = 1000.10;
		this.password = password;
	}
	public User(String firstName, String lastName, String email, String password, String userRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = "" + firstName + lastName;
		this.email = email;
		this.balance = 1000.10;
		this.password = password;
		this.userRole = userRole;
	}
}
