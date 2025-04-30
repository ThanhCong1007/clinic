package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.N2CDentCare.model.BenhAn;

import java.util.List;

@Repository
public interface BenhAnRepository extends JpaRepository<BenhAn, Integer>{
	
	@Override
	List<BenhAn> findAll();
	
	List<BenhAn> findBySdt(String sdt);
	
	List<BenhAn> findByMaBa(int maBa);
}
