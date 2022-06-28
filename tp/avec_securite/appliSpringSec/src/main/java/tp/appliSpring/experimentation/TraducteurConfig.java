package tp.appliSpring.experimentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraducteurConfig {
	
	/*
	 avec
	 traducteur.prefere=tp.appliSpring.experimentation.TraducteurFrancaisAllemand
	           ou bien =tp.appliSpring.experimentation.TraducteurFrancaisAnglais
	 dans application.properties
	 */
	@Value("${traducteur.prefere}") 
	private String nomClasseTraducteurPrefere;
	
	@Bean() // default name = traducteurParDefaut
	public Traducteur traducteurParDefaut() {
		Traducteur traducteur=null;
		try {
			Class c = Class.forName(nomClasseTraducteurPrefere);
			traducteur = (Traducteur) c.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return traducteur;
	}

}
