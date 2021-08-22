package com.project2.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project2.models.Items;
import com.project2.repository.ItemRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ItemService {
	private ItemRepo iDao;
	
	public void createItem(Items i) {
		try{
			iDao.save(i);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//	public void addRollMeat(Items i) {
//	 Category role = new Category(1,ICategory.MEAT);
//	 i.setCategory(role);
//		//return u;
//	}
//	public void addRollFruit(Items i) {
//		 Category role = new Category(2,ICategory.FRUIT);
//		 i.setCategory(role);
//			//return u;
//	}
//	public void addRollVegetables(Items i) {
//		 Category role = new Category(3,ICategory.VEGETABLES);
//		 i.setCategory(role);
//	}
//	public void addRollHerb(Items i) {
//		 Category role = new Category(4,ICategory.HERB);
//		 i.setCategory(role);
//	}
//	public void addRollBread(Items i) {
//		 Category role = new Category(5,ICategory.BREAD);
//		 i.setCategory(role);
//	}
//	public void addRollFrozen(Items i) {
//		 Category role = new Category(6,ICategory.FROZEN);
//		 i.setCategory(role);
//	}
//	public void addRollBakery(Items i) {
//		 Category role = new Category(7,ICategory.BAKERY);
//		 i.setCategory(role);
//	}
	public Items getItemById(int id) {
		Items i = iDao.findById(id);
		if(i==null) {
			return null;
		}else {
			return i;
		}
	}
	public Items findById(int id) {
		Items i = iDao.findById(id);
		if(i==null) {
			return null;
		}else {
			return i;
		}
	}
	
	public void updateItem(Items i) {
		iDao.save(i);
	}
	public List<Items> getAllItems(){
		return iDao.findAll();
	}
}
