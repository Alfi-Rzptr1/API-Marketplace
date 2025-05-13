package com.rezeptor.modules.accountData;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AccountDataRepo extends JpaRepository<AccountData, Long>{
	
	Optional<AccountData> findById(long id);
	
	@Modifying
	@Query("UPDATE AccountData a SET a.firstName = :firstname WHERE a.id = :id")
	int updateFirstName(@Param("firstname") String firstName,@Param("id") long id);
	
	@Modifying
	@Query("UPDATE AccountData a SET a.lastName = :lastname WHERE a.id = :id")
	int updateLastName(@Param("lastname") String lastName,@Param("id") long id);
	
}
