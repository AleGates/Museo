package it.uniroma3.siw.museo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.OperaService;
import net.bytebuddy.asm.Advice.This;

@Controller
public class MainController {
	
	@Autowired
	private OperaService operaService;
	
	@RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
	public String home(Model model) {
	     List<Opera> opere = this.operaService.tutti();
	     Collections.shuffle(opere);
	     model.addAttribute("opere", opere.subList(0, 3));
		 return "home";
	}

}