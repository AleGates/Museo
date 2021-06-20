package it.uniroma3.siw.museo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import it.uniroma3.siw.museo.model.Admin;
import it.uniroma3.siw.museo.service.AdminService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private AdminService adminService;
	

	

	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "loginForm";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "home.html";
	}
	
	
	//Non capisco se serve
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
    	
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Admin admin = adminService.getCredentials(userDetails.getUsername());
    	if (admin.getRole().equals(Admin.ADMIN_ROLE)) {
            return "admin/home";
        }
        return "home";
    }
}