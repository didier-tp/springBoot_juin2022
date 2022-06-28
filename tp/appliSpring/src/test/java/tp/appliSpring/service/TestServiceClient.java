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
public class TestServiceClient {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceClient.class);

	@Autowired
	private ServiceCompte serviceCompte; //aider à tester
	
	@Autowired
	private ServiceClient serviceClient; // à tester
	
	@Test
	public void testRechercherClientAvecOuSansComptes() {
		Client client1 = serviceClient.sauvegarderClient(new Client(null,"jean","Bon"));
		Compte cptA = new Compte(null,"compteA1",101.0);
		cptA.setClient(client1);
		Compte cptA_sauvegarde = serviceCompte.sauvegarderCompte(cptA);
		Compte cptB = new Compte(null,"compteB1",51.0);
		cptB.setClient(client1);
		Compte cptB_sauvegarde = serviceCompte.sauvegarderCompte(cptB);
		
		serviceCompte.sauvegarderCompte(new Compte(null,"compteC",80.0));
		serviceCompte.sauvegarderCompte(new Compte(null,"compteD",20.0));
		
		//Client client1_relu = serviceClient.rechercherClientParId(client1.getId());
		Client client1_relu = serviceClient.rechercherClientAvecComptesParId(client1.getId());
		logger.debug("client1_relu:" + client1_relu.getPrenom());
		Assertions.assertEquals("jean", client1_relu.getPrenom());
		//afficher en boucle les comptes ratachés au client en exploitant le lien @OneToMany
		for(Compte cpt : client1_relu.getComptes()) {
			logger.debug("\t " + cpt.toString());
		}
		
	}

}
