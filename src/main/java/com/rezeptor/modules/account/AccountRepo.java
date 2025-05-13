package com.rezeptor.modules.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{
	
	Optional<Account> findByEmail(String email);
	
	Optional<Account> findByUsername(String username);
	
	@Modifying
	@Query("UPDATE Account a SET a.enabled = true WHERE a.email = :email")
	int enableAccount(@Param("email") String email);
	
	@Modifying
	@Query("UPDATE Account a SET a.username = :username WHERE a.id = :id")
	int updateUsername(@Param("username") String username,@Param("id") long id);
	
	@Modifying
	@Query("UPDATE Account a SET a.email = :email WHERE a.id = :id")
	int updateEmail(@Param("email") String email,@Param("id") long id);
	
	@Modifying
	@Query("UPDATE Account a SET a.deleted = true WHERE a.id = :id")
	int deleteAccount(@Param("id") long id);
}
