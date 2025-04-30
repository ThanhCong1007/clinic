package com.example.N2CDentCare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.N2CDentCare.model.Account;
import com.example.N2CDentCare.model.ChuyenKhoa;
import com.example.N2CDentCare.model.Doctor;
import com.example.N2CDentCare.model.Khoa;
import com.example.N2CDentCare.repositories.ChuyenKhoaRepository;
import com.example.N2CDentCare.repositories.DoctorRepository;
import com.example.N2CDentCare.repositories.KhoaRepository;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class aboutController {
	private Account user;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	KhoaRepository khoaRepository;
	
	@Autowired
	ChuyenKhoaRepository chuyenKhoaRepository;
	
	@GetMapping("/gioi-thieu/thong-tin-phong-kham")
	public String getAbout(Model model){
		user = dashboardController.getUser();
		model.addAttribute("user", user);
		System.out.println(user);
		return "/gioi-thieu/about";
	}
	
	@GetMapping("/gioi-thieu/bac-si")
	public String getDoctors(Model model){
		List<Doctor> dList = doctorRepository.findAll();		
		
		List<Khoa> kList = khoaRepository.findAll();
		
		List<ChuyenKhoa> ckList = chuyenKhoaRepository.findAll();
		
		
		for (int i = 0; i < dList.size(); i++) {
			Doctor dSample = dList.get(i);
			String moTa = "";
			for (int j = 0; j < kList.size(); j++) {
				Khoa kSample = kList.get(j);
				if (kSample.getMaBs().equals(dSample.getMaBs())) {
					for (int p = 0; p < ckList.size(); p++) {
						ChuyenKhoa ckSample = ckList.get(p);
						if (kSample.getMaChuyenKhoa().equals(ckSample.getMaKhoa())) {
							moTa = moTa + ckSample.getTenKhoa() + ", ";
						}
					}
				}
				
			}		
			if (moTa != "") {
				moTa = moTa.substring(0, moTa.length() - 2);
				moTa = moTa + ".";
			}
			dSample.setMoTa(moTa);
			dList.set(i, dSample);
		}
		
		model.addAttribute("doctors", dList);
		
		user = dashboardController.getUser();
		model.addAttribute("user", user);
		System.out.println(user);
		
		return "/gioi-thieu/team";
	}
}
