package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.N2CDentCare.model.BangGiaRangSu;

import java.util.List;


public interface BangGIaRangSuRepository extends JpaRepository<BangGiaRangSu, Integer>{
	
	@Override
	List<BangGiaRangSu> findAll();
	
}
