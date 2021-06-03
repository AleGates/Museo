package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.repository.CollezioneRepository;
import it.uniroma3.siw.museo.repository.OperaRepository;

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
}