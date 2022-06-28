package tp.appliSpring.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

//DTO = Data Transfert Object 
//Concept proche : VO : Value Object (ou view)

public class CompteDto {
     
	private Long num;
	
	@Length(min=3,message="label doit avoir au moins 3 caracteres")
	private String label;
	
	@Min(10)
	private Double solde;
	
	//+get/set, constructeurs , toString()
	@Override
	public String toString() {
		return "CompteDto [num=" + num + ", label=" + label + ", solde=" + solde + "]";
	}
	
	
	public CompteDto() {
		super();
	}


	public CompteDto(Long num, String label, Double solde) {
		super();
		this.num = num;
		this.label = label;
		this.solde = solde;
	}


	public Long getNum() {
		return num;
	}
	
	public void setNum(Long num) {
		this.num = num;
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
	
	
	

}
