package com.project2.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart")
public class Cart {
	
	@JoinColumn(name="user_id")
	private int userId;
	
	@Id
	@Column(name="cart_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartId;
	
	//could be problematic might need to be changed later
	@OneToMany(mappedBy="itemId",cascade=CascadeType.ALL)
	private List<Items> items = new ArrayList<>();

	public Cart(int user_id) {
		super();
		this.userId = user_id;
		this.items = new ArrayList<>();
	}
	
	
	
}
