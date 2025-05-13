package com.rezeptor.modules.orderItem;

import org.springframework.stereotype.Service;

import com.rezeptor.modules.account.AccountLogicService;
import com.rezeptor.modules.item.ItemService;
import com.rezeptor.modules.merchantData.MerchantDataService;
import com.rezeptor.modules.order.OrderService;

@Service
public class OrderItemService {
  
	private OrderItemRepo orderItemRepo;
	private OrderService orderService;
	private AccountLogicService accountService;
	private MerchantDataService merchantDataService;
	private ItemService itemService;

	public OrderItemService(OrderItemRepo orderItemRepo, OrderService orderService,
			AccountLogicService accountService, MerchantDataService merchantDataService, ItemService itemService) {
		this.orderItemRepo = orderItemRepo;
		this.orderService = orderService;
		this.accountService = accountService;
		this.merchantDataService = merchantDataService;
		this.itemService = itemService;
	}

	public OrderItem save(OrderItemRequest request) {
		OrderItem orderItem = new OrderItem(
				accountService.findByAccountId(request.buyerAccout()),
				merchantDataService.findByAccountId(request.merchantAccount()),
				request.orderType(),
				itemService.findById(request.itemId()),
				request.quantity(),
				request.paymentMethod(),
				request.orderStatus(),
				orderService.findById(request.orderParrentId()));
		
		return orderItemRepo.save(orderItem);
	}
	
}
