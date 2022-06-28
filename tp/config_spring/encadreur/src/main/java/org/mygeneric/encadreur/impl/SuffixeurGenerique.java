package org.mygeneric.encadreur.impl;

import org.mygeneric.encadreur.Suffixeur;

public class SuffixeurGenerique implements Suffixeur {
	
	private String suffixe;
	private String separateur;
	
	public SuffixeurGenerique( String separateur, String suffixe) {
		super();
		this.separateur = separateur;
		this.suffixe = suffixe;
	}
	
	public SuffixeurGenerique( String separateur) {
		this(separateur,"");
	}
	
	public SuffixeurGenerique() {
		this("","");
	}

	public void setSuffix(String suffix) {
		this.suffixe = suffix;
	}

	public String suffixer(String s) {
		return s+separateur+suffixe;
	}

}
