package it.uniroma3.siw.museo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Curatore {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column (nullable=false)
	private String nome;
	
	@Column (nullable=false)
	private String cognome;
	//constraint-da rivedere
	@Column (nullable=false)
	private Long matricola;
	
	private LocalDate dataDiNascita;
	
	private String luogoDiNascita;
	
	private String email;
	
	private Long numeroTelefono;
	
	@OneToMany(mappedBy="curatore")
	List<Collezione> collezioniCurate;
	
	public Curatore() {
		
	}
}
