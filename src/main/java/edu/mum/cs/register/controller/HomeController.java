package edu.mum.cs.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping(value= {"/","/home","/eregistrar/home"})
	public String homePage() {
		return "home";
	}
	

}
