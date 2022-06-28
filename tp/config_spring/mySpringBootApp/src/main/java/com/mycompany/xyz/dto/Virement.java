package com.mycompany.xyz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Virement {
	  public Double montant;
	  public Long numCptDeb;
	  public Long numCptCred;
	  public String message;
}
