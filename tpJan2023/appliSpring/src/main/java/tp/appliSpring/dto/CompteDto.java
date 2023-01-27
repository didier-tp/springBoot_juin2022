package tp.appliSpring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString //pas de piege sur Dto , piege sur @Entity seulement
public class CompteDto {

    private Integer numero;
    private String label; //on pourrait mettre String libelle
    private Double solde;

    public CompteDto(Integer numero, String label, Double solde) {
        this.numero = numero;
        this.label = label;
        this.solde = solde;
    }
}
