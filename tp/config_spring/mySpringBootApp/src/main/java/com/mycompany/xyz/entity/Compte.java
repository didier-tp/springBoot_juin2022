package com.mycompany.xyz.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter  @NoArgsConstructor
@Table(name="compte")
public class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_incr qui remonte en memoire
	private Long numero; 
	
	private String label;
	
	private Double solde;
	
	@ManyToOne
	@JoinColumn(name="idClient") //nom de la colonne clef etrangere
	@JsonIgnore
	private Client client;

	public Compte(Long numero, String label, Double solde) {
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}


	

}
