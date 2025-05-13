package com.rezeptor.modules.itemReview;

import org.springframework.stereotype.Service;

import com.rezeptor.modules.account.AccountLogicService;
import com.rezeptor.modules.item.ItemService;
import com.rezeptor.modules.itemVariant.ItemVariantService;

@Service
public class ItemReviewService {
  
	private ItemReviewRepo itemReviewRepo;
	private AccountLogicService accountService;
	private ItemService itemService;
	private ItemVariantService itemVariantService;
	
	public ItemReviewService(ItemReviewRepo itemReviewRepo, AccountLogicService accountService,
			ItemService itemService, ItemVariantService itemVariantService) {
		this.itemReviewRepo = itemReviewRepo;
		this.accountService = accountService;
		this.itemService = itemService;
		this.itemVariantService = itemVariantService;
	}

	public ItemReview save(ItemReviewRequest request) {
		ItemReview itemReview = new ItemReview(
				Double.parseDouble(request.star()),
				request.message(), 
				accountService.findByAccountId(request.accountId()), 
				itemService.findById(request.itemParrentId()), 
				itemVariantService.findById(request.itemVariantId()));
		
		return itemReviewRepo.save(itemReview);
	}
	
}
