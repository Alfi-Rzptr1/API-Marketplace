package com.rezeptor.modules.itemVariant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVariantRepo extends JpaRepository<ItemVariant, Long>{
  
	List<ItemVariant> findByItemId(long itemId);
}
