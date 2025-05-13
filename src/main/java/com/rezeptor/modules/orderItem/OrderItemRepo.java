package com.rezeptor.modules.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long>{
  
	//@Query("SELECT orderItem FROM tbl_orderItem orderItem WHERE orderItem.orderType = ?1 AND orderItem.orderId = ?2")
	//List<OrderItem> findAllByOrderType(OrderType orderType,Order orderParrentId,Pageable pageable);
	
	//@Query("SELECT orderItem FROM tbl_orderItem orderItem WHERE orderItem.orderStatus = ?1 AND orderItem.orderId = ?2")
	//List<OrderItem> findAllByOrderStatus(OrderStatus orderStatus,Order orderParrentId,Pageable pageable);
	
	//first convert date to timestamp 
	//@Query("SELECT orderItem,createdAt FROM tbl_orderItem orderItem WHERE orderItem.orderType = ?1 AND orderItem.orderId = ?2AND createdAt BETWEEN ?3AND LOCALTIMESTAMP")
	//List<OrderItem> findAllByOrderTypeAndByStartDateBetween(OrderType orderType,Order orderParrentId,Timestamp timestamp,Pageable pageable);
	
	//@Query("SELECT orderItem,createdAt FROM tbl_orderItem orderItem WHERE orderItem.orderStatus = ?1 AND orderItem.orderId = ?2AND createdAt BETWEEN ?3AND LOCALTIMESTAMP")
	//List<OrderItem> findAllByOrderStatusAndByDate(OrderStatus orderStatus,Order orderParrentId,Timestamp timestamp,Pageable pageable);
}
