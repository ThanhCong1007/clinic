package com.example.N2CDentCare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.N2CDentCare.model.Account;


@Controller
public class contactController {
	private Account user;
	
	@GetMapping("/lien-he")
	public String getContact(Model model){
		user = dashboardController.getUser();
		model.addAttribute("user", user);
		System.out.println(user);
		return "contact";
	}
}
