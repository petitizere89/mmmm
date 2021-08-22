package com.project2.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.models.Items;
import com.project2.services.ItemService;
import com.project2.services.JavaMailService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/items")
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
@CrossOrigin(value = "*")
public class ItemController {
	private ItemService iServ;
	private JavaMailService jmserv;
	
	//this may cause issues/not work
	@PostMapping("/createitem")
	public ResponseEntity<String> createItem(@RequestBody LinkedHashMap<String,String>item){
//		Category cat = new Category();
//		switch(item.get("category")) {
//		case "MEAT":
//			cat.setCategory(ICategory.MEAT);
//			break;
//		case "FRUIT":
//			cat.setCategory(ICategory.FRUIT);
//			break;
//		case "VEGETABLES":
//			cat.setCategory(ICategory.VEGETABLES);
//			break;
//		case "HERB":
//			cat.setCategory(ICategory.HERB);
//			break;
//		case "BREAD":
//			cat.setCategory(ICategory.BREAD);
//			break;
//		case "FROZEN":
//			cat.setCategory(ICategory.FROZEN);
//			break;
//		case "BAKERY":
//			cat.setCategory(ICategory.BAKERY);
//			break;
//		default : 
//			return new ResponseEntity<String>("incorrect category for item recieved", HttpStatus.BAD_REQUEST);
//		}
		Items i = new Items(item.get("itemName"),Double.parseDouble(item.get("price")),item.get("description"),item.get("category"),Integer.parseInt(item.get("quantity")));
		System.out.println(i);
        iServ.createItem(i);
        return new ResponseEntity<String>("item created",HttpStatus.ACCEPTED);
	}
	@PostMapping("/updateitem")
	public ResponseEntity<String> updateItem(@RequestBody LinkedHashMap<String,String>item){
		Items i = iServ.findById(Integer.parseInt(item.get("itemId")));
		if(i == null) {
			return new ResponseEntity<String>("item not found", HttpStatus.BAD_REQUEST);
		}else {
			if(item.get("itemName")!=null) {i.setItemName(item.get("itemName"));}
			if(item.get("price")!=null) {i.setPrice(Double.parseDouble(item.get("itemPrice")));}
			if(item.get("description")!=null) {i.setDescription(item.get("description"));}
			if(item.get("quantity")!=null) {i.setQuantity(i.getQuantity()+Integer.parseInt(item.get("quantity")));}
		}
		System.out.println(i);
        iServ.updateItem(i);
        try {
			jmserv.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new ResponseEntity<String>("item updated successfully",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getitems")
	public ResponseEntity<List<Items>> getAllItems(){
		return new ResponseEntity<List<Items>>(iServ.getAllItems() , HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getoneitem")
	public ResponseEntity<Items> getItemById(int id){
		Items i = iServ.getItemById(id);
		if(i ==null) {
			return new ResponseEntity<Items>(i,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Items>(i,HttpStatus.OK);
	}
	
}
