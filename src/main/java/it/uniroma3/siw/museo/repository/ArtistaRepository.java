package it.uniroma3.siw.museo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.museo.model.Artista;

@Repository
public interface ArtistaRepository extends CrudRepository<Artista, Long> {

	public List<Artista> findByNome(String nome);

	public List<Artista> findByNomeAndCognome(String nome, String cognome);

	public List<Artista> findByNomeOrCognome(String nome, String cognome);
}