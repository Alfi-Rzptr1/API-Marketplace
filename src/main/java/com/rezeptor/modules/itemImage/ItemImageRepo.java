package com.rezeptor.modules.itemImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageRepo extends JpaRepository<ItemImage, Long>{

	//Optional<ItemImage> findByItemVariantId(long itemVariantId);
  
} 
