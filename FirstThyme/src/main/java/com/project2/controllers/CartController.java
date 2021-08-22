package com.project2.controllers;

import java.util.LinkedHashMap;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.models.Items;
import com.project2.models.User;
import com.project2.services.CartService;
import com.project2.services.ItemService;
import com.project2.services.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/cart")
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
@CrossOrigin(value = "*")
public class CartController {
	private CartService cServ;
	private UserService uServ;
	private ItemService iServ;
	
	@PostMapping(value="/additem")
	public ResponseEntity<String> addItem(@RequestBody LinkedHashMap<String,String>req){
		User u = uServ.getUserById(Integer.parseInt(req.get("userId")));
		Items i = iServ.getItemById(Integer.parseInt(req.get("itemId")));
		if(u==null || i==null) {
			return new ResponseEntity<String>("Failed to Grab either user or item",HttpStatus.CONFLICT);
		}
		i.setCart(u.getCart());
		iServ.updateItem(i);
		cServ.addItem(u, i);
		return new ResponseEntity<String> ("item added to cart",HttpStatus.OK);
	}
	
	/*
	 * @Params required
	 *  req{
	 * 		userId,
	 * 		itemId,
	 * 		quantity
	 * }
	 */
	@PostMapping(value="/transaction")
	public ResponseEntity<String> doTransaction(@RequestBody LinkedHashMap<String, String>req){
		User u = uServ.getUserById(Integer.parseInt(req.get("userId")));
		Items i = iServ.getItemById(Integer.parseInt(req.get("itemId")));
		int quantity = Integer.parseInt(req.get("quantity"));
		if(u==null || i==null || quantity == 0) {
			return new ResponseEntity<String>("Failed to Grab either user or item",HttpStatus.CONFLICT);
		}
		/*things we want
		 * check item quantity if i.quantity > quantity allow
		*  grab item price
		*  remove item quantity * price from user money
		*  update user 
		*  add to transaction table if time allows
		*  remove quantity from item
		*  update item
		*/
		if(i.getQuantity() > quantity) {
			try {
				if(u.getBalance() < i.getQuantity()*i.getPrice()) {
					return new ResponseEntity<String>("U broke get a job",HttpStatus.CONFLICT);
				}else {
					System.out.println("User: " + u.getUsername() + " balance: "  +  u.getBalance());
					u.setBalance(u.getBalance() - (i.getQuantity()*i.getPrice()));
					System.out.println("After set = User: " + u.getUsername() + " balance: "  +  u.getBalance());
					uServ.updateUser(u);
					System.out.println("User update Success");
					System.out.println("itemID: " + i.getItemId() + " quantity: " + i.getQuantity());
					i.setQuantity(i.getQuantity() - quantity);	
					iServ.updateItem(i);
					System.out.println("ID: " + i.getItemId() + " ItemName: " + i.getItemName() + " item update Success");
					return new ResponseEntity<String>("HERES THE SUCCESS RESPONSE ARE U HAPPY U SHOULD B",HttpStatus.ACCEPTED);
				}	
			}catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Update user failed or not enough money",HttpStatus.CONFLICT);
			}
		}else {
			return new ResponseEntity<String>("Items quantity does not suffice be less greedy",HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
