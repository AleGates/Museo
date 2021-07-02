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

import com.sun.xml.bind.v2.runtime.reflect.Accessor.SetterOnlyReflection;

import it.uniroma3.siw.museo.controller.validator.CollezioneValidator;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.service.CollezioneService;
import it.uniroma3.siw.museo.service.CuratoreService;
import it.uniroma3.siw.museo.service.OperaService;
import net.bytebuddy.asm.Advice.This;
//vanno aggiunti metodi di: aggiunta/rimozione opere e curatori nella collezione corrente
//va veriricato il funzionamento di deleteCollezione (specie il path e il return)
@Controller
public class CollezioneController {
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private CuratoreService curatoreService;
	
	@Autowired
	private OperaService operaService;
	
	
    @Autowired
    private CollezioneValidator collezioneValidator;  

    @RequestMapping(value="/admin/addCollezione", method = RequestMethod.GET)
    public String addCollezione(Model model) {
    	model.addAttribute("collezione", new Collezione());
    	model.addAttribute("curatori", this.curatoreService.tutti());
    	return "admin/collezioneForm.html";
    	}

    @RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("collezione", this.collezioneService.collezionePerId(id));
    	model.addAttribute("curatore", this.collezioneService.collezionePerId(id).getCuratore());
    	model.addAttribute("opere", this.operaService.trovaPerCollezioneId(id));
    	return "collezione.html"; 
    }
    
    @RequestMapping(value = "/collezioni", method = RequestMethod.GET)
    public String getCollezioni(Model model) {
    		model.addAttribute("collezioni", this.collezioneService.tutti());
    		return "collezioni.html";
    }
    
    @RequestMapping(value = "/admin/collezione", method = RequestMethod.POST)
    public String newCollezione(@ModelAttribute("collezione") Collezione collezione, @RequestParam("c") String curatore,
    									Model model, BindingResult bindingResult) {
    	this.collezioneValidator.validate(collezione, bindingResult);
        if (!bindingResult.hasErrors()) {
            curatore.trim(); //elimino spazi bianchi iniziali e finali
            String[] nomeCognome = curatore.split("\\s+"); //divido le stringhe per spazi bianchi
            List<Curatore> curator = this.curatoreService.curatorePerNomeAndCognome(nomeCognome[0], nomeCognome[1]); //prendo l'oggetto curatore
        	collezione.setCuratore(curator.get(0));  //setto il curatore della collezione 
            this.collezioneService.inserisci(collezione);
            model.addAttribute("collezioni", this.collezioneService.tutti());
            return "collezioni.html";
        }
        return "admin/collezioneForm.html";
    }
    
    @RequestMapping(value= "/collezione/{id}/delete", method= RequestMethod.GET)
    public String deleteCollezione(Model model) {
    	model.addAttribute("collezioni", this.collezioneService.tutti());
    	return "Delecollezioni.html";
    }
    
    
	@RequestMapping(value = "/collezione/{id}/delete", method = RequestMethod.POST)
	public String deleteCollezione(@PathVariable Long id, Model model) {
		this.collezioneService.deleteCollezioneById(id);
		return "collezioni.html";
	}
}