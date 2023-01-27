package tp.appliSpring.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity //entity de données qui persiste en base
@Table(name="Compte")
@NamedQuery(name="Compte.findByNumeroDeClientQueJaime" ,
           query="SELECT c FROM Compte c WHERE c.client.numero = ?1 ")
//requête JPA Query Langage (proche de SQL) où tous les noms sont en java
// (pas des tables mais des classes , pas de colonnes mais des attributs)
// ?1 signifie valeur du premier paramètre de la fonction de recherche
public class Compte {

    @Id //identifiant (pk)
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incr en base qui remonte en mémoire
    private Integer numero;
    
    @Column(name="label",length = 64)//VARCHAR(64)
    private String label;
    
    private Double solde;

    @JsonIgnore //pour ignorer la partie en dessous (ici client)
    //lors de la conversion java vers json d'un compte
    //éventuellement nécessaire si PAS de DTO
    @ManyToOne //many Compte to one Client
    @JoinColumn(name="num_client") //nom de la colonne clef étrangère dans la table des comptes
    private Client client;

    @java.lang.Override
    public java.lang.String toString() {
        return "Compte{" +
                "numero=" + numero +
                ", label='" + label + '\'' +
                ", solde=" + solde +
                '}';
    }

    public Compte(Integer numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }

    public Compte() {
        //constructeur par défaut (avec zero parametre) obligatoire pour techno JPA
    }
}
