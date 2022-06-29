package tp.appliSpring.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class CompteDto {
	
    private Long numero;
    
    @Length(min=3, max=20, message = "Nom trop long ou trop court")
    private String label;
    
    @Min(0)
    private Double solde;
}
