package it.uniroma3.siw.museo.repository;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.museo.model.Opera;

@Repository
public interface OperaRepository extends CrudRepository<Opera, Long> {

	public List<Opera> findByTitolo(String titolo);
	
	public List<Opera> findAll();
	
	public Optional<Opera> findById(Long id);
	
	public List<Opera> findByCollezioneId(Long id);
	
	public List<Opera> findByArtistaId(Long id);
	
	public List<Opera> findByTitoloAndAnno(String titolo, Long anno);
	
	public List<Opera> findByTitoloOrAnno(String titolo, Long anno);
	
	@Query(value = "SELECT * FROM Opera ORDER BY RANDOM() LIMIT ?1", nativeQuery=true)
	public List<Opera> dammiNOggettiRandom(int n);
}
