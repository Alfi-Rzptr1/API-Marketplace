package com.rezeptor.modules.merchantData;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantDataRepo extends JpaRepository<MerchantData, Long>{
	
  Optional<MerchantData> findByAccountId(long id);
}
