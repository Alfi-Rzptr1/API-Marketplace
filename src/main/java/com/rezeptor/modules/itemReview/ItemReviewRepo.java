package com.rezeptor.modules.itemReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemReviewRepo extends JpaRepository<ItemReview, Long>{
  
}
