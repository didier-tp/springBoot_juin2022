package tp.appliSpring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CLIENT")
@NamedQuery(name="Client.findByIdWithComptes",
            query="SELECT cli FROM Client cli LEFT JOIN FETCH cli.comptes cpt WHERE cli.id = ?1")
public class Client {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment en base
               //et la valeur auto_incrémentée remonte en mémoire ici.
    private Long id;
    
    private String prenom;
    
    private String nom;
    //email , adresse , telephone
    
    @OneToMany(mappedBy = "client" , fetch = FetchType.LAZY)
    @JsonIgnore //pas nécessaire si DTO
    private List<Compte> comptes = new ArrayList<>(); //+get/set
    
    public Client() {
		super();
	}



	public Client(Long id, String prenom, String nom) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}
	
	

	@Override
	public String toString() {
		return "Client [id=" + id + ", prenom=" + prenom + ", nom=" + nom + "]";
	}



	public Long getId() {
		return id;
	}
	
	

	

	public void setId(Long id) {
		this.id = id;
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
    
  //+get/set , constructeur , toString()
    
    
}