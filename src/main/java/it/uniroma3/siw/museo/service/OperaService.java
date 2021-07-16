package it.uniroma3.siw.museo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.repository.OperaRepository;

@Service
public class OperaService{

	@Autowired
	private OperaRepository operaRepository;

	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}

	@Transactional
	public List<Opera> operaPerTitolo(String titolo) {
		return operaRepository.findByTitolo(titolo);
	}

	@Transactional
	public List<Opera> tutti() {
		return (List<Opera>) operaRepository.findAll();
	}

	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Opera opera) {
		List<Opera> opere = this.operaRepository.findByTitolo(opera.getTitolo());
		if (opere.size() > 0)
			return true;
		else 
			return false;
	}
	
	public List<Opera> trovaPerCollezioneId(Long id){
		return this.operaRepository.findByCollezioneId(id);
	}
	
	public List<Opera> trovaPerArtistaId(Long id){
		return this.operaRepository.findByArtistaId(id);
	}

	public void deleteOperaById(Long id) {
		operaRepository.deleteById(id);
	}
	
	public List<Opera> prendiOpereACaso(int numero){
	     List<Opera> casuali = new ArrayList<Opera>();
         casuali = this.operaRepository.dammiNOggettiRandom(numero);
	     return casuali;
	}
}