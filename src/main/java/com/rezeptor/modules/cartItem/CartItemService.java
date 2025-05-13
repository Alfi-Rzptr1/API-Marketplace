package com.rezeptor.modules.cartItem;

import org.springframework.stereotype.Service;

import com.rezeptor.exceptions.ResourceNotFoundException;
import com.rezeptor.modules.cart.CartService;
import com.rezeptor.modules.item.ItemService;
import com.rezeptor.modules.itemVariant.ItemVariantService;
import com.rezeptor.modules.merchantData.MerchantDataService;

@Service
public class CartItemService {

	private CartItemRepo cartItemRepo;
	private CartService cartService;
	private MerchantDataService merchantDataService;
	private ItemService itemService;
	private ItemVariantService itemVariantService;
	
	public CartItemService(CartItemRepo cartItemRepo, CartService cartService, MerchantDataService merchantDataService,
			ItemService itemService, ItemVariantService itemVariantService) {
		this.cartItemRepo = cartItemRepo;
		this.cartService = cartService;
		this.merchantDataService = merchantDataService;
		this.itemService = itemService;
		this.itemVariantService = itemVariantService;
	}
	
	public CartItem findById(long id) {
		return cartItemRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Cart with id %s not found", id)));
	}
	
	public CartItem save(CartItemRequest request) {		
		CartItem cartItem = new CartItem(
				merchantDataService.findByAccountId(request.accountMerchantId()),						 
				itemService.findById(request.itemParrentId()),						 
				itemVariantService.findById(request.itemVariantid()),
				cartService.findById(request.cartParrentId()),
				request.quantity());
		
		return cartItemRepo.save(cartItem);
	}
	
	
}