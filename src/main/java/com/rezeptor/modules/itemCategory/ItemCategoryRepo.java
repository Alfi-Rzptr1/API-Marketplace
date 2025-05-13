package com.rezeptor.modules.itemCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepo extends JpaRepository<ItemCategory, Long>{
  
}
