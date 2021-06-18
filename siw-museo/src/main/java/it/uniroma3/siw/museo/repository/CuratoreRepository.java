package it.uniroma3.siw.museo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.museo.model.Curatore;

@Repository
public interface CuratoreRepository extends CrudRepository<Curatore, Long> {

	public List<Curatore> findByNome(String nome);

	public List<Curatore> findByNomeAndCognome(String nome, String cognome);

	public List<Curatore> findByNomeOrCognome(String nome, String cognome);
	
	public Optional<Curatore> findByMatricola (Long matricola);
}