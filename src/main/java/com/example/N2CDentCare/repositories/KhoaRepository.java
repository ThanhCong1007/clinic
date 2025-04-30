package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.N2CDentCare.model.Khoa;

import java.util.List;

public interface KhoaRepository extends JpaRepository<Khoa, Integer>{
	
	@Override
	List<Khoa> findAll();
	
}
