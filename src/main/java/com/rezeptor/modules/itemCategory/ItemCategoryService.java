package com.rezeptor.modules.itemCategory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.modules.item.Item;

@Service
public class ItemCategoryService {
  
	private ItemCategoryRepo itemCategoryRepo;

	public ItemCategoryService(ItemCategoryRepo itemCategoryRepo) {
		this.itemCategoryRepo = itemCategoryRepo;
	}
	
	public ItemCategory findById(long id) {
		return itemCategoryRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Category with id %s not found", id)));
	}
	
	public ItemCategory save(String name) {
		return itemCategoryRepo.save(new ItemCategory(name));
	}
	
	public ItemCategory save(String name,List<Item> items) {
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setName(name);
		itemCategory.setItems(items);
		return itemCategoryRepo.save(itemCategory);
	}
	
}
