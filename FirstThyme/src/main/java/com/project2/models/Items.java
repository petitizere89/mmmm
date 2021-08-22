package com.project2.models;

import java.util.List;
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
import javax.persistence.OneToMany;
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
@ToString
@Entity
@Table(name="items")
public class Items {
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int itemId;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="price")
	private double price;
	
	@Column(name="description")
	private String description;
	
	@JoinColumn(name="category")
	private String category;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="cart_id", nullable=true)
	private Cart cart;

	public Items(String itemName, double price, String description, String category, int quantity) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.description = description;
		this.category = category;
		this.quantity = quantity;
	}
}
