package com.example.N2CDentCare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.N2CDentCare.model.DatLich;

public interface DatLichRepository extends JpaRepository<DatLich, Integer>  {
	@Override
	List<DatLich> findAll();
}
