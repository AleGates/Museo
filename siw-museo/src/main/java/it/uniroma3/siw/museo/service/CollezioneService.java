package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.repository.CollezioneRepository;

public class CollezioneService {

	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Transactional
	public Collezione inserisci(Collezione collezione, Curatore curatore) {
		collezione.setCuratore(curatore);
		return collezioneRepository.save(collezione);
	}
	
	@Transactional
	public List<Collezione> collezionePerNome(String nome) {
		return collezioneRepository.findByNome(nome);
	}

	@Transactional
	public List<Collezione> tutti() {
		return (List<Collezione>) collezioneRepository.findAll();
	}

	@Transactional
	public Collezione collezionePerId(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public void aggiornaCollezione(Long id, Collezione collezioneFinale) {
		Collezione collezione= this.collezioneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Collezione non trovata"));
		collezione.setNome(collezioneFinale.getNome());
		collezione.setDescrizione(collezioneFinale.getDescrizione());
		this.collezioneRepository.save(collezione);
	}

	@Transactional
	public boolean alreadyExists(Collezione collezione) {
		List<Collezione> collezioni = this.collezioneRepository.findByNome(collezione.getNome());
		if (collezioni.size() > 0)
			return true;
		else 
			return false;
	}

	public void deleteCollezioneById(Long id) {
		collezioneRepository.deleteById(id);
	}
}
