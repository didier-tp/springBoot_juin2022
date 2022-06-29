package tp.appliSpring.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.appliSpring.entity.Client;
import tp.appliSpring.entity.Compte;
import tp.appliSpring.service.ServiceClient;
import tp.appliSpring.service.ServiceCompte;

@Component
@Profile("reInit")
public class InitDataSet {

	//@Resource(name="serviceCompteImpl")
	@Autowired
	private ServiceCompte serviceCompte; //aider à tester
	
	@Autowired
	private ServiceClient serviceClient; // à tester
	
	@PostConstruct
	public void init() {
		Client client1 = serviceClient.sauvegarderClient(new Client(null,"jean","Bon"));
		Compte cptA = new Compte(null,"compteA1",101.0);
		cptA.setClient(client1);
		Compte cptA_sauvegarde = serviceCompte.sauvegarderCompte(cptA);
		Compte cptB = new Compte(null,"compteB1",51.0);
		cptB.setClient(client1);
		Compte cptB_sauvegarde = serviceCompte.sauvegarderCompte(cptB);
		
		serviceCompte.sauvegarderCompte(new Compte(null,"compteC",80.0));
		serviceCompte.sauvegarderCompte(new Compte(null,"compteD",20.0));
	}
}
