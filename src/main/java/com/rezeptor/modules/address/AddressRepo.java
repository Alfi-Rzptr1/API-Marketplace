package com.rezeptor.modules.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
  
	List<Address> findAllByAccountDataId(long accountDataId);
	
}
