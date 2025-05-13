package com.rezeptor.modules.itemVariant;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.rezeptor.modules.item.ItemService;
import com.rezeptor.modules.itemImage.ItemImageService;

@Service
public class ItemVariantMapper implements Function<ItemVariantRequest,ItemVariant>{

	private final ItemService itemService;
	private final ItemImageService itemImageService;
	
	public ItemVariantMapper(ItemService itemService, ItemImageService itemImageService) {
		this.itemService = itemService;
		this.itemImageService = itemImageService;
	}

	@Override
	public ItemVariant apply(ItemVariantRequest t) {
		return new ItemVariant(
				itemService.findById(t.itemParrentId()),
				(t.imageFile() != null && !t.imageFile().isEmpty()) 
					? itemImageService.create(t.imageFile()) : null,
				t.name(),
				t.SKU(),
				t.variantCombinations(),
				Double.parseDouble(t.price()),
				t.itemDesc(),
				t.quantity());
	}

}
