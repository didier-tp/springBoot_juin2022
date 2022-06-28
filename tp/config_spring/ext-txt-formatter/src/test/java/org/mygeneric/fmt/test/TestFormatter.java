package org.mygeneric.fmt.test;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;//for assertTrue() , ...

import org.mygeneric.encadreur.Encadreur;
import org.mygeneric.encadreur.Prefixeur;
import org.mygeneric.encadreur.Suffixeur;
import org.mygeneric.encadreur.ext.OptionalFormatter;
import org.mygeneric.encadreur.impl.EncadreurAvecPrefixeEtSuffixe;
import org.mygeneric.encadreur.impl.PrefixeurAvecSeparateurEgal;
import org.mygeneric.encadreur.impl.PrefixeurAvecSeparateurTiret;
import org.mygeneric.encadreur.impl.SuffixeurAvecSperateurEgal;
import org.mygeneric.encadreur.impl.SuffixeurAvecSperateurTiret;
import org.mygeneric.fmt.MyFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFormatter {

	private static Logger logger = LoggerFactory.getLogger(TestFormatter.class);
	
	@Test
	public void testFormat() {
		String s="chameauAvecDesBosses";
		OptionalFormatter formatter = new MyFormatter();
		String sRes1 = formatter.format(s);
		assertEquals("chameau_avec_des_bosses",sRes1);
		logger.debug("en version snakeCase, sRes1="+sRes1);
		
	}
	
	

}
