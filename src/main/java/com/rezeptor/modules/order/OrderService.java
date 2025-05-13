package com.rezeptor.modules.order;

import org.springframework.stereotype.Service;

import com.rezeptor.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
 
	private OrderRepo orderRepo;
	
	public OrderService(OrderRepo orderRepo) {
		this.orderRepo = orderRepo;
	}

	public Order save(Order order) {
		return orderRepo.save(order);
	}
	
	public Order findById(long id){
		return orderRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(
						String.format("Order parrent with id %s not found", id)));
	}
	
}
