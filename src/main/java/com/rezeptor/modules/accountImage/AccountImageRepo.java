package com.rezeptor.modules.accountImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountImageRepo extends JpaRepository<AccountImage, Long>{
  
}
