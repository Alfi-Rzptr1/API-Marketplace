package com.rezeptor.modules.cart;

import org.springframework.stereotype.Service;

import com.rezeptor.exceptions.ResourceNotFoundException;

@Service
public class CartService {
  
	private CartRepo cartRepo;

	public CartService(CartRepo cartRepo) {
		this.cartRepo = cartRepo;
	}
	
	public Cart findById(long id) {
		return cartRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Parrent cart with id %s not found", id)));
	}
	
	public Cart save(Cart cart) {
		return cartRepo.save(cart);
	}
	
}
