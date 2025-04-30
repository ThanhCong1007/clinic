package com.example.N2CDentCare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.N2CDentCare.model.Doctor;
import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, String>{
	
	@Override
	List<Doctor> findAll();
	
	List<Doctor> findBySdt(String sdt);
	
}
