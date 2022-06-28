package tp.appliSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="COMPTE")
@NamedQuery(name = "Compte.findAll" , query="SELECT c FROM Compte c")
@NamedQuery(name = "Compte.findByClientNumeroQueJaime" , 
                    query = "SELECT cpt FROM Compte cpt WHERE cpt.client.numero = ?1")
public class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment en base
	                                                    //et la valeur auto_incrémentée remonte ici en mémoire
	private Long numero;
	
	@Column(name="label" , length=32) //varchar(32)
	private String label;
	
	private Double solde;
	
	@ManyToOne
	@JoinColumn(name="num_client") //nom colonne clef etrangere = "num_client"
	//@JsonIgnore //utile que si pas de DTO
	private Client client; //+get/set mais pas dans toString()
	
	//+get/set , +toString() + constructeurs
	public Compte() {
	}
	
	
	
	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}
	
	



	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}



	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
}
