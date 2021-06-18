package it.uniroma3.siw.museo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.Opera;

public interface OperaRepository extends CrudRepository<Opera, Long> {

	public List<Opera> findByTitolo(String titolo);
	
	public List<Opera> findAll();
	
	public Optional<Opera> findById(Long id);
	
	public List<Opera> findByTitoloAndAnno(String titolo, Long anno);
	
	public List<Opera> findByTitoloOrAnno(String titolo, Long anno);
}
