package org.mygeneric.encadreur.impl;

public class PrefixeurAvecSeparateurEgal extends PrefixeurGenerique {

	public PrefixeurAvecSeparateurEgal() {
		super("==");
	}

	public PrefixeurAvecSeparateurEgal(String prefixe) {
		super("==" , prefixe);
	}

}
