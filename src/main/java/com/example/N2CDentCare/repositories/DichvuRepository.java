package com.example.N2CDentCare.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.N2CDentCare.model.Dichvu;


public interface DichvuRepository extends JpaRepository<Dichvu,String> {
	@Override
	List<Dichvu> findAll();
}
