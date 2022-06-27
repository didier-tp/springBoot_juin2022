package tp.appliSpring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;

//version sans springBoot
@Configuration
@ComponentScan(basePackages = { "tp.appliSpring.core"})
public class MySpringApplication {

	public static void main(String[] args) {
		/*ApplicationContext*/ AnnotationConfigApplicationContext springContext = new
				AnnotationConfigApplicationContext(MySpringApplication.class) ;
				
		ServiceCompte serviceCompte = springContext.getBean(ServiceCompte.class);
		Compte cptA = new Compte(null,"compteA",100.0);
		Compte cptA_sauvegarde = serviceCompte.sauvegarderCompte(cptA);
		
		Compte cptA_relu = serviceCompte.rechercherCompteParNumero(cptA_sauvegarde.getNumero());
		System.out.println("cptA_relu="+cptA_relu);
		
		springContext.close();
	}

}
