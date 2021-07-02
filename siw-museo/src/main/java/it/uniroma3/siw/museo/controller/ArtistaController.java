package it.uniroma3.siw.museo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.controller.validator.ArtistaValidator;
import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.service.ArtistaService;

//da verificare se serve getArtistaPerNomeECognome
@Controller
public class ArtistaController {
	
	@Autowired
	private ArtistaService artistaService;
	
    @Autowired
    private ArtistaValidator artistaValidator;
    
    @RequestMapping(value="/admin/addArtista", method = RequestMethod.GET)
    public String addArtista(Model model) {
    	model.addAttribute("artista", new Artista());
        return "admin/artistaForm";
    }
    
    @RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
    public String getArtistaPerId(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("artista", this.artistaService.artistaPerId(id));
    	return "artista.html"; 
    }


    @RequestMapping(value = "/artisti", method = RequestMethod.GET)
    public String getArtisti(Model model) {
    		model.addAttribute("artisti", this.artistaService.tutti());
    		return "artisti.html";//ancora non esiste
    }
    
    @RequestMapping(value = "/admin/artista", method = RequestMethod.POST)
    public String newArtista(@ModelAttribute("artista") Artista artista, 
    									Model model, BindingResult bindingResult) {
    	this.artistaValidator.validate(artista, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.artistaService.inserisci(artista);
            model.addAttribute("artisti", this.artistaService.tutti());
            return "artisti.html";
        }
        return "admin/artistaForm";
    }
    
    @RequestMapping(value= "/admin/deleteArtisti", method= RequestMethod.GET)
    public String deleteCollezioneGet(Model model) {
    	model.addAttribute("artisti", this.artistaService.tutti());
    	return "admin/deleteArtista.html";
    }
    
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.POST)
	public String deleteCollezionePost(@PathVariable("id") Long id, Model model) {
		this.artistaService.deleteArtistaById(id);
		model.addAttribute("artisti", this.artistaService.tutti());
		return "artisti.html";
	}
}