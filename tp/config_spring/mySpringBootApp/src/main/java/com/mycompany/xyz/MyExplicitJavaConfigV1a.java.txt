package com.mycompany.xyz;

import org.mygeneric.encadreur.Encadreur;
import org.mygeneric.encadreur.Prefixeur;
import org.mygeneric.encadreur.Suffixeur;
import org.mygeneric.encadreur.impl.EncadreurAvecPrefixeEtSuffixe;
import org.mygeneric.encadreur.impl.PrefixeurAvecSeparateurEgal;
import org.mygeneric.encadreur.impl.PrefixeurAvecSeparateurTiret;
import org.mygeneric.encadreur.impl.SuffixeurAvecSperateurTiret;


public class MyExplicitJavaConfigV1 {
	

	public Prefixeur monPrefixeurSpringAvecTiret() {
		return new PrefixeurAvecSeparateurTiret("**");
	}
	


	public Prefixeur monPrefixeurSpringAvecEgal() {
		return new PrefixeurAvecSeparateurEgal("**");
	}
	


	public Suffixeur monSuffixeurSpring() {
		return new SuffixeurAvecSperateurTiret("**");
	}
	

	public Encadreur monEncadreurSpring(Prefixeur prefixeur,
			                            Suffixeur suffixeur) {
		return new EncadreurAvecPrefixeEtSuffixe(prefixeur,suffixeur);
	}

}
