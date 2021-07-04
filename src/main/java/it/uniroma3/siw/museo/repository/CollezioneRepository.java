package it.uniroma3.siw.museo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.museo.model.Collezione;

@Repository
public interface CollezioneRepository extends CrudRepository<Collezione, Long> {

	public List<Collezione> findByNome(String nome);

}