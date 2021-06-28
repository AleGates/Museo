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
public class Artista {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column (nullable=false)
	private String nome;

	@Column (nullable=false)
	private String cognome;

	@Column (nullable=false)
	private LocalDate dataNascita;
	
	@Column (nullable=false)
	private String luogoNascita;
	
	@Column (nullable=false)
	private String nazionalita;

	private LocalDate dataMorte;
	
	private String luogoMorte;



	@OneToMany(mappedBy="artista")
	List<Opera> opereRealizzate;


	public Artista(){

	}
}
