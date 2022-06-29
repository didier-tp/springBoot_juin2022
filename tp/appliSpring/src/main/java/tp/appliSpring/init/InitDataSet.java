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
		serviceCompte.sauvegarderCompte(cptA);
		Compte cptB = new Compte(null,"compteB1",51.0);
		cptB.setClient(client1);
		serviceCompte.sauvegarderCompte(cptB);
		
		Client client2 = serviceClient.sauvegarderClient(new Client(null,"alex","Therieur"));
		Compte cptC = new Compte(null,"compteC",80.0);
		cptC.setClient(client2);
		serviceCompte.sauvegarderCompte(cptC);
		Compte cptD = new Compte(null,"compteD",20.0);
		cptD.setClient(client2);
		serviceCompte.sauvegarderCompte(cptD);
	}
}
