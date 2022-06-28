package org.mygeneric.encadreur.impl;

public class PrefixeurAvecSeparateurTiret extends PrefixeurGenerique {

	public PrefixeurAvecSeparateurTiret() {
		super("--");
	}

	public PrefixeurAvecSeparateurTiret(String prefixe) {
		super("--" , prefixe);
	}

}
