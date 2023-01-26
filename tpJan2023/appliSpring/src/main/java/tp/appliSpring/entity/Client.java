package tp.appliSpring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;

    private String prenom;
    private String nom;
    //...


    public Client(Integer numero, String prenom, String nom) {
        this.numero = numero;
        this.prenom = prenom;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numero=" + numero +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
