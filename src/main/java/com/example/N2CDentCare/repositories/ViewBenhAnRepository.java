package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.N2CDentCare.model.BenhAn;
import com.example.N2CDentCare.model.ViewBenhAn;

import java.util.List;

public interface ViewBenhAnRepository extends JpaRepository<ViewBenhAn, String>{
	
	@Override
	List<ViewBenhAn> findAll();
	
	List<ViewBenhAn> findBySdt(String sdt);
}
