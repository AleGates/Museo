package it.uniroma3.siw.museo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.controller.validator.OperaValidator;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.OperaService;

//vanno aggiunti metodi di: aggiunta di artista nell'opera corrente
//va veriricato il funzionamento di deleteOpera (specie il path e il return)
@Controller
public class OperaController {
	
	@Autowired
	private OperaService operaService;
	
    @Autowired
    private OperaValidator operaValidator;

    @RequestMapping(value="/admin/addOpera", method = RequestMethod.GET)
    public String addOpera(Model model) {
    	model.addAttribute("opera", new Opera());
        return "admin/operaForm";
    }
    

    @RequestMapping(value = "/opera/{id}", method = RequestMethod.GET)
    public String getOpera(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("opera", this.operaService.operaPerId(id));
    	return "opera.html"; 
    }

    @RequestMapping(value = "/opere", method = RequestMethod.GET)
    public String getOpere(Model model) {
    		model.addAttribute("opere", this.operaService.tutti());
    		return "opere.html";
    }
    
    
    @RequestMapping(value = "/admin/opera", method = RequestMethod.POST)
    public String newOpera(@ModelAttribute("opera") Opera opera, 
    									Model model, BindingResult bindingResult) {
    	this.operaValidator.validate(opera, bindingResult);
        if (!bindingResult.hasErrors()) {
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