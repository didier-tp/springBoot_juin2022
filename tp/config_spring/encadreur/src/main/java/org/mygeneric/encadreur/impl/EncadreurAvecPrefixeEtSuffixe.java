package org.mygeneric.encadreur.impl;


import org.mygeneric.encadreur.Encadreur;
import org.mygeneric.encadreur.Prefixeur;
import org.mygeneric.encadreur.Suffixeur;
import org.mygeneric.encadreur.ext.OptionalFormatter;

public class EncadreurAvecPrefixeEtSuffixe implements Encadreur {
	
	private Suffixeur suffixeur;
	private Prefixeur prefixeur;
	private OptionalFormatter formatter; //optional (may be null)
	
	public EncadreurAvecPrefixeEtSuffixe( Prefixeur prefixeur , Suffixeur suffixeur, OptionalFormatter formatter) {
		super();
		this.prefixeur = prefixeur;
		this.suffixeur = suffixeur;
		this.formatter = formatter;
	}


	public EncadreurAvecPrefixeEtSuffixe( Prefixeur prefixeur , Suffixeur suffixeur) {
		this(prefixeur, suffixeur, null);
	}
	
	public EncadreurAvecPrefixeEtSuffixe() {
		this(null,null,null);
	}

	public String encadrer(String s) {
		String sRes=s;
		if(prefixeur!=null) sRes=prefixeur.prefixer(sRes);
		if(suffixeur!=null) sRes=suffixeur.suffixer(sRes);
		return sRes;
	}

	public String encadrerAvecFormat(String s) {
	    if(this.formatter==null) {
		    return encadrer(s).toUpperCase(); //by default
	    }else {
	    	return formatter.format(encadrer(s));
	    }
	}
	
	public Suffixeur getSuffixeur() {
		return suffixeur;
	}

	public void setSuffixeur(Suffixeur suffixeur) {
		this.suffixeur = suffixeur;
	}

	public Prefixeur getPrefixeur() {
		return prefixeur;
	}

	public void setPrefixeur(Prefixeur prefixeur) {
		this.prefixeur = prefixeur;
	}

	public OptionalFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(OptionalFormatter formatter) {
		this.formatter = formatter;
	}

}
