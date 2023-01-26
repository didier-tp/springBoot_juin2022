package tp.appliSpring.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity //entity de données qui persiste en base
@Table(name="Compte")
public class Compte {

    @Id //identifiant (pk)
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_incr en base qui remonte en mémoire
    private Integer numero;
    
    @Column(name="label",length = 64)//VARCHAR(64)
    private String label;
    
    private Double solde;

    @ManyToOne //many Compte to one Client
    @JoinColumn(name="num_compte") //nom de la colonne clef étrangère dans la table des comptes
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