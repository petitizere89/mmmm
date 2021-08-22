package com.project2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.models.Cart;
import com.project2.models.Items;
import com.project2.models.User;
import com.project2.repository.CartRepo;
import com.project2.repository.ItemRepo;
import com.project2.repository.UserRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class CartService {
	private ItemRepo iDao;
	private UserRepo uDao;
	private CartRepo cDao;
	
	public void addItem(User u, Items i) {
		Cart c = cDao.findByCartId(u.getCart().getCartId());
		List<Items> items = c.getItems();
		items.add(i);
		c.setItems(items);
		cDao.save(c);
	}
}
