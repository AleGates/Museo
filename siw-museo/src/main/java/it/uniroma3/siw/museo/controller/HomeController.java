package it.uniroma3.siw.museo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/info", method = RequestMethod.GET) 
	public String showInfoPage (Model model) {
		return "info";
	}
	
	@RequestMapping(value = "/artisti", method = RequestMethod.GET) 
	public String showArtistiPage (Model model) {
		return "artisti";
	}
	
	@RequestMapping(value = "/collezioni", method = RequestMethod.GET) 
	public String showCollezioniPage (Model model) {
		return "collezioni";
	}

}
