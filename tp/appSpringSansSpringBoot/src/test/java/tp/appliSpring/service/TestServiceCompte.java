package tp.appliSpring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.core.MySpringApplication;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.service.ServiceCompte;

//@RunWith(SpringRunner.class)  //si junit4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {MySpringApplication.class})//reprendre la configuration de la classe principale
public class TestServiceCompte {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceCompte.class);

	@Autowired
	private ServiceCompte serviceCompte; //à tester
	
	@Test
	public void testRechercherCompte() {
		Compte cptA = new Compte(null,"compteA",100.0);
		Compte cptA_sauvegarde = serviceCompte.sauvegarderCompte(cptA);
		
		Compte cptA_relu = serviceCompte.rechercherCompteParNumero(cptA_sauvegarde.getNumero());
		logger.debug("cptA_relu="+cptA_relu);
		//Assert.assertTrue(.) en JUnit4
		Assertions.assertTrue(cptA_relu.getLabel().equals("compteA"));//en JUnit5"
        //...
	}
}
