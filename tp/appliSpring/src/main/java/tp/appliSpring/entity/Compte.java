package tp.appliSpring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="COMPTE")
@NamedQuery(name="Compte.findByClientIdAvecRequeteSpecifique" , 
            query="SELECT cpt FROM Compte cpt WHERE cpt.client.id = ?1")
public class Compte {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment en base
               //et la valeur auto_incrémentée fremonte en mémoire ici.
    private Long numero;
    
    private String label;
    private Double solde;
    
    @ManyToOne
    @JoinColumn(name="id_client") //nom de la colonne clefEtrangere dans table compte
    private Client client;  //+get/set
    
  //+get/set , constructeur , toString()
    
	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}


	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}


	public Compte() {
		super();
	}
	


    //+get/set , constructeur , toString()


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