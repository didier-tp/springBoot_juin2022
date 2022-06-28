package org.mygeneric.encadreur.impl;

public class SuffixeurAvecSperateurTiret extends SuffixeurGenerique {

	public SuffixeurAvecSperateurTiret() {
		super("--");
	}

	public SuffixeurAvecSperateurTiret(String separateur) {
		super("--",separateur);
	}

}
