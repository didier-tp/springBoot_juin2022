package org.mygeneric.encadeur.test;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;//for assertTrue() , ...

import org.mygeneric.encadreur.Encadreur;
import org.mygeneric.encadreur.Prefixeur;
import org.mygeneric.encadreur.Suffixeur;
import org.mygeneric.encadreur.impl.EncadreurAvecPrefixeEtSuffixe;
import org.mygeneric.encadreur.impl.PrefixeurAvecSeparateurEgal;
import org.mygeneric.encadreur.impl.PrefixeurAvecSeparateurTiret;
import org.mygeneric.encadreur.impl.SuffixeurAvecSperateurEgal;
import org.mygeneric.encadreur.impl.SuffixeurAvecSperateurTiret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEncadreur {

	private static Logger logger = LoggerFactory.getLogger(TestEncadreur.class);
	
	@Test
	public void testSuffixeur() {
		String s="azerty";
		Suffixeur suffixeur1 = new SuffixeurAvecSperateurTiret("**");
		String sRes1 = suffixeur1.suffixer(s);
		assertEquals("azerty--**",sRes1);
		logger.debug("avec SuffixeurAvecSperateurTiret, sRes1="+sRes1);
		Suffixeur suffixeur2 = new SuffixeurAvecSperateurEgal("**");
		String sRes2 = suffixeur2.suffixer(s);
		assertEquals("azerty==**",sRes2);
		logger.debug("avec SuffixeurAvecSperateurEgal, sRes2="+sRes2);
	}
	
	@Test
	public void testPrefixeur() {
		String s="azerty";
		Prefixeur prefixeur1 = new PrefixeurAvecSeparateurTiret("**");
		String sRes1 = prefixeur1.prefixer(s);
		assertEquals("**--azerty",sRes1);
		logger.debug("avec PrefixeurAvecSeparateurTiret, sRes1="+sRes1);
		Prefixeur prefixeur2 = new PrefixeurAvecSeparateurEgal("**");
		String sRes2 = prefixeur2.prefixer(s);
		assertEquals("**==azerty",sRes2);
		logger.debug("avec PrefixeurAvecSeparateurEgal, sRes2="+sRes2);
	}
	
	@Test
	public void testEncadreurAvecTiret() {
		String s="azerty";
		Prefixeur prefixeur = new PrefixeurAvecSeparateurTiret("**");
		Suffixeur suffixeur = new SuffixeurAvecSperateurTiret("**");
		Encadreur encadreur = new EncadreurAvecPrefixeEtSuffixe(prefixeur,suffixeur);
		String sRes1 = encadreur.encadrer(s);
		assertEquals("**--azerty--**",sRes1);
		logger.debug("avec Encadreur Tiret, sRes1="+sRes1);
		String sRes2 = encadreur.encadrerAvecFormat(s);
		assertEquals("**--AZERTY--**",sRes2);
		logger.debug("avec Encadreur Tiret et Format, sRes2="+sRes2);
	}
	
	@Test
	public void testEncadreurAvecEgal() {
		String s="azerty";
		Prefixeur prefixeur = new PrefixeurAvecSeparateurEgal("**");
		Suffixeur suffixeur = new SuffixeurAvecSperateurEgal("**");
		Encadreur encadreur = new EncadreurAvecPrefixeEtSuffixe(prefixeur,suffixeur);
		String sRes1 = encadreur.encadrer(s);
		assertEquals("**==azerty==**",sRes1);
		logger.debug("avec Encadreur Egal, sRes1="+sRes1);
		String sRes2 = encadreur.encadrerAvecFormat(s);
		assertEquals("**==AZERTY==**",sRes2);
		logger.debug("avec Encadreur Egal et Format, sRes2="+sRes2);
	}
	
	@Test
	public void testEncadreurPartiel() {
		String s="azerty";
		Prefixeur prefixeur = new PrefixeurAvecSeparateurEgal("**");
		Suffixeur suffixeur = new SuffixeurAvecSperateurEgal("**");
		EncadreurAvecPrefixeEtSuffixe encadreur1 = new EncadreurAvecPrefixeEtSuffixe();
		encadreur1.setPrefixeur(prefixeur);
		String sRes1 = encadreur1.encadrer(s);
		assertEquals("**==azerty",sRes1);
		logger.debug("avec Encadreur partiel 1, sRes1="+sRes1);
		EncadreurAvecPrefixeEtSuffixe encadreur2 = new EncadreurAvecPrefixeEtSuffixe();
		encadreur2.setSuffixeur(suffixeur);
		String sRes2 = encadreur2.encadrer(s);
		assertEquals("azerty==**",sRes2);
		logger.debug("avec Encadreur partiel 2, sRes2="+sRes2);
	}

}
