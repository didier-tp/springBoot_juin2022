package tp.appliSpring.experimentation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//Version2 avec injection via constructeur

@Component
@Scope("singleton")  //scope par défaut , une seule instance créée et injectée partout
//@Scope("prototype")  //une instance re-créée pour chaque besoin d'injection (rare)
//@Scope("session ou application ou request")  //scopes WEB des vielles technos java/web (JSF , JSP, servlet, ....)
public class Afficheur {
	
	private Traducteur traducteurPrincipal/*=null*/;
	
	//public Afficheur(@Qualifier("FrancaisAnglais") Traducteur traducteurPrincipal) {
	public Afficheur(@Qualifier("traducteurParDefaut") Traducteur traducteurPrincipal) {
		this.traducteurPrincipal=traducteurPrincipal;
		System.out.println("Dans le constructeur de Afficheur qui fait de l'injection de dépendance traducteurPrincipal="+traducteurPrincipal);
		//pas besoin de @PostConstruct si injection via constructeur
	}
	
	
	
	public void traduireEtAfficher(String message) {
		String traduction = this.traducteurPrincipal.traduire(message);
		System.out.println("traduction="+traduction);
	}

}
