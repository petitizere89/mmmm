package com.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.models.Cart;


@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	public Cart findByCartId(int id);
}
