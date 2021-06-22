package it.uniroma3.siw.museo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Entity
@Data
@AllArgsConstructor
public class Admin {

	public static final String ADMIN_ROLE = "ADMIN";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;
		
}