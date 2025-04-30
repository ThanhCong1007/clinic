package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.N2CDentCare.model.ChuyenKhoa;
import java.util.List;


public interface ChuyenKhoaRepository extends JpaRepository<ChuyenKhoa, String>{
	
	@Override
	List<ChuyenKhoa> findAll();
	
}
