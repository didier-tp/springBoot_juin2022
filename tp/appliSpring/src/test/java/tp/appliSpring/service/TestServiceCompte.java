package tp.appliSpring.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.dao.DaoClient;
import tp.appliSpring.entity.Client;
import tp.appliSpring.entity.Compte;

//@RunWith(SpringRunner.class)  //si junit4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles("reInit,embeddedDB")
//@ActiveProfiles("reInit,remoteDB")
public class TestServiceCompte {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceCompte.class);

	@Autowired
	private ServiceCompte serviceCompte; //à tester
	
	@Autowired
	private DaoClient daoClient; //pour aider à tester
	
	@Test
	public void testRechercherComptesDuClient() {
		Client client1 = daoClient.save(new Client(null,"jean","Bon"));
		Compte cptA = new Compte(null,"compteA1",101.0);
		cptA.setClient(client1);
		Compte cptA_sauvegarde = serviceCompte.sauvegarderCompte(cptA);
		Compte cptB = new Compte(null,"compteB1",51.0);
		cptB.setClient(client1);
		Compte cptB_sauvegarde = serviceCompte.sauvegarderCompte(cptB);
		
		serviceCompte.sauvegarderCompte(new Compte(null,"compteC",80.0));
		serviceCompte.sauvegarderCompte(new Compte(null,"compteD",20.0));
		
		List<Compte> comptesDuClient1 = 
				serviceCompte.rechercherComptesDuClient(client1.getId());
		logger.debug("comptesDuClient1="+comptesDuClient1);
		Assertions.assertTrue(comptesDuClient1.size()==2);
	}
	
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
