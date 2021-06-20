package it.uniroma3.siw.museo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {
	
	public Optional<Admin> findByUsername(String username);

}