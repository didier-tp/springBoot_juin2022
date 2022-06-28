package org.mygeneric.encadreur.properties;

//classe pour récupérer des sous propriétés de configuration
//au sein de application.properties ou ...

//sub level of properties:
public class PrefixOuSuffixProperties {
	private String affixe; // suffixe ou préxife , ex: "**" ou "##" ou ">>" 
	private String separateur; //ex: "--" ou "==" ou " " 
	
	public String getAffixe() {
		return affixe;
	}
	public void setAffixe(String affixe) {
		this.affixe = affixe;
	}
	public String getSeparateur() {
		return separateur;
	}
	public void setSeparateur(String separateur) {
		this.separateur = separateur;
	}
	
	@Override
	public String toString() {
		return "PrefixOuSuffixProperties [affixe=" + affixe + ", separateur=" + separateur + "]";
	}
	
}
