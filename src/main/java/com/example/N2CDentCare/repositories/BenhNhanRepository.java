package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.N2CDentCare.model.BenhNhan;

import java.util.List;

public interface BenhNhanRepository extends JpaRepository<BenhNhan, Integer>{	
	@Override
	List<BenhNhan> findAll();
	
	List<BenhNhan> findBySdt(String sdt);
	
	boolean existsBySdt(String sdt);
	
	List<BenhNhan> findByMaBn(int maBn);
}
