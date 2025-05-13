package com.rezeptor.modules.itemVariant;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.modules.item.ItemService;
import com.rezeptor.modules.itemImage.ItemImageService;

@Service
public class ItemVariantService {
  
	private ItemVariantRepo itemVariantRepo;
	private ItemService itemService;
	private ItemImageService itemImageService;

	public ItemVariantService(ItemVariantRepo itemVariantRepo, ItemService itemService,
		ItemImageService itemImageService) {
		this.itemVariantRepo = itemVariantRepo;
		this.itemService = itemService;
		this.itemImageService = itemImageService;
	}

	public List<ItemVariant> findByItemId(long itemId){
		if(itemVariantRepo.findByItemId(itemId).isEmpty()) {
			throw new ResourceNotFoundException(
					String.format("Parrent Item with id %s doesn't have any item variants", itemId));
		}
		return itemVariantRepo.findByItemId(itemId);
	}
	
	public ItemVariant findById(long id) {
		return itemVariantRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Item variant with id %s not found", id)));
	}

	public ItemVariant create(ItemVariantRequest request) {
		MultipartFile file = request.imageFile();
		
		ItemVariant itemVariant = new ItemVariant(
				itemService.findById(request.itemParrentId()),
				file.isEmpty() ? null : itemImageService.create(file),
				request.name(),
				request.SKU(),
				request.variantCombinations(),
				Double.parseDouble(request.price()),
				request.itemDesc(),
				request.quantity());
		
		return itemVariant;
	}
	
	public ItemVariant save(ItemVariantRequest request) {
		ItemVariant itemVariant = create(request);
		return itemVariantRepo.save(itemVariant);
	}
	
}
