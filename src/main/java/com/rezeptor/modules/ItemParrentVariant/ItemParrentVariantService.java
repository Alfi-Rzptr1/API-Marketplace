package com.rezeptor.modules.ItemParrentVariant;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.modules.item.ItemService;
import com.rezeptor.modules.itemVariant.ItemVariantMapper;

@Service
public class ItemParrentVariantService {

	private ItemParrentVariantRepo itemParrentVariantRepo;
	private ItemService itemService;
	private ItemVariantMapper itemVariantMapper;
	
	public ItemParrentVariantService(ItemParrentVariantRepo itemParrentVariantRepo, ItemService itemService,
			ItemVariantMapper itemVariantMapper) {
		this.itemParrentVariantRepo = itemParrentVariantRepo;
		this.itemService = itemService;
		this.itemVariantMapper = itemVariantMapper;
	}

	public ItemParrentVariant findById(long id) {
		return itemParrentVariantRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Parrent item variant with id %s not found", id)));
	}
	
	public ItemParrentVariant create(ItemParrentVariantRequest request) {	
		return new ItemParrentVariant(
				itemService.findById(request.itemId()),
				request.itemVariantRequests().stream()
											 .map(itemVariantMapper::apply)
											 .collect(Collectors.collectingAndThen(
													 Collectors.toList(), Collections::unmodifiableList)),
				request.name());
	}
	
	public ItemParrentVariant save(ItemParrentVariantRequest request) {
		ItemParrentVariant itemParrentVariant = create(request);
		return itemParrentVariantRepo.save(itemParrentVariant);
	}
	
}
