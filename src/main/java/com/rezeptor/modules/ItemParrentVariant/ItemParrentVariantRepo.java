package com.rezeptor.modules.ItemParrentVariant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemParrentVariantRepo extends JpaRepository<ItemParrentVariant, Long>{
	
	List<ItemParrentVariant> findAllByItemId(long itemId);
	
}
