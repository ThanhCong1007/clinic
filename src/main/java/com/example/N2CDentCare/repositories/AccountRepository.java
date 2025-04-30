package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.N2CDentCare.model.Account;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String>{
	
	@Override
	List<Account> findAll();
	
	List<Account> findByUsername(String username);
	
}
