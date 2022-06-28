package org.mygeneric.encadreur.impl;

import org.mygeneric.encadreur.Prefixeur;

public class PrefixeurGenerique implements Prefixeur {
	
	private String prefixe;
	private String separateur;
	
	public PrefixeurGenerique( String separateur, String prefixe) {
		super();
		this.separateur = separateur;
		this.prefixe = prefixe;
	}
	
	public PrefixeurGenerique( String separateur) {
		this(separateur,"");
	}
	
	public PrefixeurGenerique() {
		this("","");
	}

	public void setPrefix(String prefix) {
		this.prefixe = prefix;
	}

	public String prefixer(String s) {
		return prefixe+separateur+s;
	}

}
