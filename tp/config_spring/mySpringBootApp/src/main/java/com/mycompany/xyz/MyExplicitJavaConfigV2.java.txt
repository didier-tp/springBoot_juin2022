package com.mycompany.xyz;

import org.mygeneric.encadreur.Prefixeur;
import org.mygeneric.encadreur.Suffixeur;
import org.mygeneric.encadreur.impl.PrefixeurAvecSeparateurEgal;
import org.mygeneric.encadreur.impl.SuffixeurAvecSperateurEgal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration

//pas besoin de @Import({MyEncadreurAutoConfiguration.class})
//car META-INF/spring.factories mentionnant
//....autoconfigure.EnableAutoConfiguration=...MyEncadreurAutoConfiguration
//présent dans encadreur-starter.jar
public class MyExplicitJavaConfigV2 {
	
	//NB: si on définit ici les beans Prefixeur et Suffixeur
	//alors ceux-ci (en tant que config. explicite) l'emportent
	//sur la configuration automatique de encadreur-starter.jar
	
	/*
	@Bean
	public Prefixeur monPrefixeurSpringAvecEgal() {
		return new PrefixeurAvecSeparateurEgal("**");
	}
	
	@Bean
	public Suffixeur monSuffixeurSpringAvecEgal() {
		return new SuffixeurAvecSperateurEgal("**");
	}
   */
}
