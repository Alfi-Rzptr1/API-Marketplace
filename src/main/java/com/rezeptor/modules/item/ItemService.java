package com.rezeptor.modules.item;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.modules.itemCategory.ItemCategoryService;
import com.rezeptor.modules.itemImage.ItemImage;
import com.rezeptor.modules.itemImage.ItemImageService;
import com.rezeptor.modules.itemVariant.ItemVariantMapper;
import com.rezeptor.modules.itemVariant.ItemVariantRequest;
import com.rezeptor.modules.merchantData.MerchantDataService;

import jakarta.transaction.Transactional;

@Service
public class ItemService {

	private ItemRepo itemRepo;
	private MerchantDataService merchantDataService;
	private ItemCategoryService itemCategoryService;
	private ItemImageService itemImageService;
	private ItemVariantMapper itemVariantMapper;
	
	//private int totalQuantityItem = 0;

	public ItemService(ItemRepo itemRepo, MerchantDataService merchantDataService,
			ItemCategoryService itemCategoryService,ItemImageService itemImageService,
			@Lazy ItemVariantMapper itemVariantMapper) {
		this.itemRepo = itemRepo;
		this.merchantDataService = merchantDataService;
		this.itemCategoryService = itemCategoryService;
		this.itemImageService = itemImageService;
		this.itemVariantMapper = itemVariantMapper;
	}

	public Item findById(long itemId) {
		return itemRepo.findById(itemId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Item with id %s not found", itemId)));
	}
	
	@Transactional
	public Item save(ItemRequest request) {
		
		Item item = new Item(
				request.name(),
				request.itemVariantRequests().stream()
						.mapToInt(itemVariantReq -> itemVariantReq.quantity()).sum(),
				0,
				request.isCOD(),
				merchantDataService.findByAccountId(request.accountId()),
				itemCategoryService.findById(request.itemCategoryId()),
				request.itemVariantRequests().stream().map(itemVariantMapper::apply)
						.collect(Collectors.collectingAndThen(
								Collectors.toList(), Collections::unmodifiableList)),
				getItemImages(request.itemVariantRequests().stream().toList()));
		
		return itemRepo.save(item);
	}
	
	private List<ItemImage> getItemImages(List<ItemVariantRequest> itemVariants){
		List<ItemImage> itemImages = new ArrayList<>();
		itemVariants.forEach((itemVariantReq) -> 
				itemImages.add(itemImageService.create(itemVariantReq.imageFile())));
		return itemImages;
	}
	
}
