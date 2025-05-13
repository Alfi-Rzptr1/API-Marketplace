package com.rezeptor.modules.notification;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long>{
  
	Optional<Notification> findByAccountDataId(long accountDataId);
	
}
