package com.mycompany.xyz.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter  @NoArgsConstructor
@Table(name="client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_incr qui remonte en memoire
	private Long numero;   //pk
	
	private String nom;
	
	@OneToMany(mappedBy = "client")
    @JsonIgnore
	private List<Compte> comptes;

	@Override
	public String toString() {
		return "Client [numero=" + numero + ", nom=" + nom + "]";
	}

	public Client(Long numero, String nom) {
		super();
		this.numero = numero;
		this.nom = nom;
	}

	
	

}
