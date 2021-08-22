package com.project2.controllers;

import java.util.LinkedHashMap;

import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import com.project2.models.User;

import com.project2.services.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/users")
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))

@CrossOrigin(value = "*")
public class UserContoller {
	private UserService uServ;

	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@RequestBody LinkedHashMap<String,String>user){
		
		//User u = new User(user.get("firstName"),user.get("lastName"),user.get("email"),user.get("password"),"CUSTOMER");
		User u = new User(user.get("firstName"),user.get("lastName"),user.get("email"),user.get("password"),"MANAGER");
		if(uServ.createUser(u)) {
			return new ResponseEntity<String>("User was registered",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Username or email was already taken", HttpStatus.CONFLICT);
		}
	}


	
	@PostMapping("/login")
	//@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/login")
	public ResponseEntity<User> loginUser(@RequestBody LinkedHashMap<String, String> user){
		System.out.println(user);
		User u = uServ.loginUser(user.get("username"), user.get("password"));

		if(u==null) {
			return new ResponseEntity<User>(u,HttpStatus.FORBIDDEN);
		}
		u.toString();
		return new ResponseEntity<User>(u,HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody LinkedHashMap<String, String>user){
		System.out.println(user);
		User u = uServ.findByUsername(user.get("username"));
		if(u==null) {
			return new ResponseEntity<String>("username not found",HttpStatus.I_AM_A_TEAPOT);
		}else {
			if(user.get("newUsername")!=null) { u.setUsername(user.get("newUsername"));}
			if(user.get("newFirstName")!=null) {u.setFirstName(user.get("newFirstName"));}
			if(user.get("newLastName")!=null) {u.setLastName(user.get("newLastName"));}
			if(user.get("newEmail")!=null) {u.setEmail(user.get("newEmail"));}
			if(user.get("newPassword")!=null) {u.setPassword(user.get("newPassword"));}
			
			uServ.updateUser(u);
			return new ResponseEntity<String>("user updated",HttpStatus.ACCEPTED);
		}//possible validation by doing u==username of u
		
	}
	@GetMapping("/getuser")
	public ResponseEntity<User> getUser(int id){
		User u = uServ.getUserById(id);
		if(u == null) {
			return new ResponseEntity<User>(u,HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<User>(u,HttpStatus.ACCEPTED);
		}
		
	}
	
}
