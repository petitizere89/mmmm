package com.project2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project2.models.Cart;
import com.project2.models.User;
import com.project2.repository.UserRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserService {
	private UserRepo uDao;
	//view all items
	//create shopping cart
	//create customer account
	//update user info
	//how git api
	//purchase items
	//manager creates inventory system
	//manager can adjust inventory
	
	public boolean createUser(User u) {
		//create shopping cart
		//add roll
		try {
			createShoppingCart(u);
			//addRollC(u); SEE COMMENT ON LINE 58 FOR REASONING
			uDao.save(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean updateUser(User u) {
		try {
			uDao.save(u);
			return true;
			
		}catch (Exception e) {
			return false;
		}
	}
	public void createShoppingCart(User u) {
		Cart c = new Cart(u.getId());
		u.setCart(c);
		//return u;
	}

	/*
	 * NOT NEEDED ANY MORE....IS DONE IN USER CONTROLLER 
	 * public void addRollC(User u) { 
	 * 	UserRoles role = new
	 * 	UserRoles(URoles.CUSTOMER); 
	 * u.setUserRole(role); //return u;
	 *  } 
	 * public voidaddRollM(User u) {
	 *  UserRoles role = new UserRoles(URoles.MANAGER);
	 *  u.setUserRole(role); 
	 *  //return u; 
	 *  }
	 */
	public User loginUser(String username, String password) {
		User u = uDao.findByUsername(username);
		if(u==null) {
			return null;
		}else {
			if(!u.getPassword().equals(password)) {
				return null;
			}else {
				return u;
			}
		}
	}
	public User displayUser(String username) {
		User u = uDao.findByUsername(username);
		if(u == null) {
			return null;
		}else {
			return u;
		}
	}
	public User getUserById(int id) {
		return uDao.getById(id);
	}
	public User findByUsername(String username) {
		return uDao.findByUsername(username);
	}
}
