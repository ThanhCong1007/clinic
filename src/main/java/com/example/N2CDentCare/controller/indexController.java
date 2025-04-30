package com.example.N2CDentCare.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.N2CDentCare.model.Account;
import com.example.N2CDentCare.model.Dichvu;
import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.model.GioLamViec;
import com.example.N2CDentCare.repositories.DichvuRepository;
import com.example.N2CDentCare.repositories.DoctorRepository;
import com.example.N2CDentCare.repositories.GioLamViecRepository;

@Controller
public class indexController {
	
	private Account user;
	
	@Autowired
	DichvuRepository dichvuRepository;	
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	GioLamViecRepository gioLamViecRepository;
	
	@GetMapping("/trang-chu")
	public String getDichvu(Model model){
		List<Dichvu> list = dichvuRepository.findAll();
		
		model.addAttribute("dichvus", list);
		
		List<Doctor> dList = doctorRepository.findAll();
		List<Doctor> kq = new ArrayList<>();
		for (int i = 0; i < dList.size(); i++) {
			Doctor dSample = dList.get(i);
			if (dSample.getHocVan().startsWith("THẠC SĨ") || dSample.getHocVan().startsWith("TIẾN SĨ"))
				kq.add(dSample);
		}
		
		model.addAttribute("doctors", kq);
		
		List<GioLamViec> glvList = gioLamViecRepository.findAll();
		model.addAttribute("glvlist", glvList);
		user = dashboardController.getUser();
		model.addAttribute("user", user);
		System.out.println(user);
		return "index";
	}
}
