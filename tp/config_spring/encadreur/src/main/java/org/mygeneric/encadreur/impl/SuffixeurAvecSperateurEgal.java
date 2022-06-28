package org.mygeneric.encadreur.impl;

public class SuffixeurAvecSperateurEgal extends SuffixeurGenerique {

	public SuffixeurAvecSperateurEgal() {
		super("==");
	}

	public SuffixeurAvecSperateurEgal(String separateur) {
		super("==",separateur);
	}

}
