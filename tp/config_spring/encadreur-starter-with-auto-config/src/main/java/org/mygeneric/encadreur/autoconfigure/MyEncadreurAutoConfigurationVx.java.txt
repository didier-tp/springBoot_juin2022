package org.mygeneric.encadreur.autoconfigure;

import org.mygeneric.encadreur.Encadreur;
import org.mygeneric.encadreur.Prefixeur;
import org.mygeneric.encadreur.Suffixeur;
import org.mygeneric.encadreur.ext.OptionalFormatter;
import org.mygeneric.encadreur.impl.EncadreurAvecPrefixeEtSuffixe;
import org.mygeneric.encadreur.impl.PrefixeurAvecSeparateurTiret;
import org.mygeneric.encadreur.impl.PrefixeurGenerique;
import org.mygeneric.encadreur.impl.SuffixeurAvecSperateurTiret;
import org.mygeneric.encadreur.impl.SuffixeurGenerique;
import org.mygeneric.encadreur.properties.EncadreurProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 NB: cette classe est référencée dans le fichier
 META-INF/spring.factories (de src/main/resources)
 org.springframework.boot.autoconfigure.EnableAutoConfiguration=org.mygeneric.encadreur.autoconfigure.MyEncadreurAutoConfiguration
 (ou bien = AutoConfig1,...,AutoConfigN
 selon les spécifications suivantes:
 https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/boot-features-developing-auto-configuration.html
 */

@Configuration
@ConfigurationPropertiesScan("org.mygeneric.encadreur.properties")
//eventuel @AutoConfigureAfter ou @AutoConfigureBefore ou ...
public class MyEncadreurAutoConfiguration {
	
	@Autowired(required = false)
	public EncadreurProperties encadreurProperties;
	
	/*
	//pas absolument nécessaire si @ConfigurationPropertiesScan sur cette classe
	//et si @ConfigurationProperties(prefix="encadreur") sur EncadreurProperties
	@ConfigurationProperties(prefix="encadreur")
	@Bean
	public EncadreurProperties encadreurPropertiesBean() {
		return new EncadreurProperties();
	}
	*/
	
	@Bean
	@ConditionalOnMissingBean(type = { "org.mygeneric.encadreur.Prefixeur" })
	public Prefixeur monPrefixeurSpring() {
		//System.out.println("encadreurProperties="+encadreurProperties);
		if(encadreurProperties!=null && encadreurProperties.getPrefixeur()!=null) {
			return new PrefixeurGenerique(
					encadreurProperties.getPrefixeur().getSeparateur(),
					encadreurProperties.getPrefixeur().getAffixe()
					);
		}else {
		   return new PrefixeurAvecSeparateurTiret("##"); //par défaut
		}
	}
	
	
	@Bean
	@ConditionalOnMissingBean(type = { "org.mygeneric.encadreur.Suffixeur" })
	public Suffixeur monSuffixeurSpring() {
		//System.out.println("encadreurProperties="+encadreurProperties);
		if(encadreurProperties!=null && encadreurProperties.getPrefixeur()!=null) {
			return new SuffixeurGenerique(
					encadreurProperties.getSuffixeur().getSeparateur(),
					encadreurProperties.getSuffixeur().getAffixe()
					);
		}else {
			return new SuffixeurAvecSperateurTiret("##");
		}
	}
	
	@Bean
	@ConditionalOnMissingBean(type = { "org.mygeneric.encadreur.Encadreur" })
	@ConditionalOnMissingClass("org.mygeneric.fmt.MyFormatter")
	public Encadreur monEncadreurSpringSansFormatter(Prefixeur prefixeur,
			                            Suffixeur suffixeur) {
		return new EncadreurAvecPrefixeEtSuffixe(prefixeur,suffixeur);
	}
	
	@Bean
	@ConditionalOnMissingBean(type = { "org.mygeneric.encadreur.Encadreur" })
	@ConditionalOnClass(name = "org.mygeneric.fmt.MyFormatter")
	public Encadreur monEncadreurSpringAvecFormatter(Prefixeur prefixeur,
			                            Suffixeur suffixeur) {
		OptionalFormatter formatter = null;
		try {
			formatter= (OptionalFormatter) Class.forName("org.mygeneric.fmt.MyFormatter").getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.err.println("cannot instanciate org.mygeneric.fmt.MyFormatter" + e.getMessage());
		}
		return new EncadreurAvecPrefixeEtSuffixe(prefixeur,suffixeur,formatter);
	}

}

