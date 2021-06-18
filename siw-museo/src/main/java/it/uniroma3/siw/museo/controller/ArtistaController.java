package it.uniroma3.siw.museo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @RequestMapping(value="/addArtista", method = RequestMethod.GET)
    public String addArtista(Model model) {
    	logger.debug("addArtista");
    	model.addAttribute("artista", new Artista());
        return "artistaForm.html"; //ancora non esiste
    }
//seleziona l'artista per id
    @RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
    public String getArtistaPerId(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("artista", this.artistaService.artistaPerId(id));
    	return "artista.html"; 
    }
  
//seleziona l'artissta per nome e cognome
    /*non so se è necessario
    @RequestMapping(value = "/artista/{nome, cognome}", method = RequestMethod.GET)
    public String getArtistaPerNomeECognome(@PathVariable("nome, cognome") String nome,
     String cognome, Model model) {
    	model.addAttribute("artista", this.artistaService.artistaPerNomeAndCognome(nome, cognome));
    	return "artista.html"; 
    }
    */

    @RequestMapping(value = "/artisti", method = RequestMethod.GET)
    public String getArtisti(Model model) {
    		model.addAttribute("artisti", this.artistaService.tutti());
    		return "artisti.html";//ancora non esiste
    }
    
    @RequestMapping(value = "/artista", method = RequestMethod.POST)
    public String newArtista(@ModelAttribute("artista") Artista artista, 
    									Model model, BindingResult bindingResult) {
    	this.artistaValidator.validate(artista, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.artistaService.inserisci(artista);
            model.addAttribute("artisti", this.artistaService.tutti());
            return "artisti.html";//ancora non esiste
        }
        return "artistaForm.html";//ancora non esiste
    }
}