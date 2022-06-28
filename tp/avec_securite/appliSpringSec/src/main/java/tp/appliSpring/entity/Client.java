package tp.appliSpring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CLIENT")
//@NamedQuery(name = "Compte.findAll" , query="SELECT c FROM Compte c")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment en base
	                                                    //et la valeur auto_incrémentée remonte ici en mémoire
	private Long numero;
	

	private String prenom;
	
	private String nom;
	
	@OneToMany(mappedBy = "client") 
	//mappedBy du coté secondaire/inverse de la relation bi-directionnelle
	//valeur de mappedBy = nom java au dessus duquel on trouve @ManyToOne
	@JsonIgnore //utile que si pas de DTO
	private List<Compte> comptes = new ArrayList<>(); //+get/set
	


	@Override
	public String toString() {
		return "Client [numero=" + numero + ", prenom=" + prenom + ", nom=" + nom + "]";
	}

	public Client() {
		super();
	}

	public Client(Long numero, String prenom, String nom) {
		super();
		this.numero = numero;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
	
	
}
