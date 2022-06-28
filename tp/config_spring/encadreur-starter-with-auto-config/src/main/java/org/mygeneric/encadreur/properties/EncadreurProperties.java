package org.mygeneric.encadreur.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

//classe pour récupérer des propriétés de configuration
//au sein de application.properties ou ...

@ConfigurationProperties(prefix="encadreur")
public class EncadreurProperties {
	private PrefixOuSuffixProperties prefixeur;
	private PrefixOuSuffixProperties suffixeur;
	
	public EncadreurProperties() {
	}
		
	public PrefixOuSuffixProperties getPrefixeur() {
		return prefixeur;
	}

	public void setPrefixeur(PrefixOuSuffixProperties prefixeur) {
		this.prefixeur = prefixeur;
	}

	public PrefixOuSuffixProperties getSuffixeur() {
		return suffixeur;
	}

	public void setSuffixeur(PrefixOuSuffixProperties suffixeur) {
		this.suffixeur = suffixeur;
	}

	@Override
	public String toString() {
		return "EncadreurProperties [prefixeur=" + prefixeur + ", suffixeur=" + suffixeur + "]";
	}
	
	
}
