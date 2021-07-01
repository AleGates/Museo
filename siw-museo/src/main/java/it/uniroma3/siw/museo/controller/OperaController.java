package it.uniroma3.siw.museo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.museo.controller.validator.OperaValidator;
import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.ArtistaService;
import it.uniroma3.siw.museo.service.CollezioneService;
import it.uniroma3.siw.museo.service.OperaService;

//vanno aggiunti metodi di: aggiunta di artista nell'opera corrente
//va veriricato il funzionamento di deleteOpera (specie il path e il return)
@Controller
public class OperaController {
	
	@Autowired
	private OperaService operaService;
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private ArtistaService artistaService;
	
    @Autowired
    private OperaValidator operaValidator;

    @RequestMapping(value="/admin/addOpera", method = RequestMethod.GET)
    public String addOpera(Model model) {
    	model.addAttribute("opera", new Opera());
    	model.addAttribute("artisti", this.artistaService.tutti());
    	model.addAttribute("collezioni", this.collezioneService.tutti());
        return "admin/operaForm";
    }
    

    @RequestMapping(value = "/opera/{id}", method = RequestMethod.GET)
    public String getOpera(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("opera", this.operaService.operaPerId(id));
    	model.addAttribute("artista", this.operaService.operaPerId(id).getArtista());
    	model.addAttribute("collezione", this.operaService.operaPerId(id).getCollezione());
    	return "opera.html"; 
    }

    @RequestMapping(value = "/opere", method = RequestMethod.GET)
    public String getOpere(Model model) {
    		model.addAttribute("opere", this.operaService.tutti());
    		return "opere.html";
    }
    
    
    @RequestMapping(value = "/admin/opera", method = RequestMethod.POST)
    public String newOpera(@ModelAttribute("opera") Opera opera,   @RequestParam("a") String artista,
    		@RequestParam("co") String collezione, Model model, BindingResult bindingResult) {
    	this.operaValidator.validate(opera, bindingResult);
        if (!bindingResult.hasErrors()) {
        	collezione.trim(); 
           
            List<Collezione> coll= this.collezioneService.collezionePerNome(collezione);
        	opera.setCollezione(coll.get(0));
        	artista.trim(); 
            String[] nomeCognome = artista.split("\\s+"); 
            List<Artista> artist = this.artistaService.artistaPerNomeAndCognome(nomeCognome[0], nomeCognome[1]); 
        	opera.setArtista(artist.get(0));
        	this.operaService.inserisci(opera);
            model.addAttribute("opere", this.operaService.tutti());
            return "opere.html";
        }
        return "admin/operaForm.html";
    }

    
    @RequestMapping(value= "/opera/{id}/delete", method= RequestMethod.GET)
    public String deleteOpera(Model model) {
    	model.addAttribute("opere", this.operaService.tutti());
    	return "opere.html";
    }
    
	@RequestMapping(value = "/opera/{id}/delete", method = RequestMethod.POST)
	public String deleteOpera(@PathVariable Long id, Model model) {
		this.operaService.deleteOperaById(id);
		return "opere.html";
	}
}