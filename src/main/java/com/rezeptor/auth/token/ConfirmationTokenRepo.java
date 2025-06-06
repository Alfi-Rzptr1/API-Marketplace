package com.rezeptor.auth.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long>{

	Optional<ConfirmationToken> findByToken(String token);
	
	@Modifying
	@Query("UPDATE ConfirmationToken c SET c.confirmedAt = :confirmedAt WHERE c.token = :token")
	int updateConfirmedAt(@Param("token") String token,@Param("confirmedAt") LocalDateTime confirmedAt);
}
