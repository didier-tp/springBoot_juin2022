package tp.appliSpring.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.AppliSpringApplication;
import tp.appliSpring.dao.ClientDao;
import tp.appliSpring.entity.Client;
import tp.appliSpring.entity.Compte;

@ExtendWith(SpringExtension.class) //si junit5/jupiter
@SpringBootTest(classes= {AppliSpringApplication.class})
public class TestCompteService {
	//logging.level.root=WARN
	//logging.level.tp.appliSpring=DEBUG dans application.properties
	private static Logger logger = LoggerFactory.getLogger(TestCompteService.class);
	
	@Autowired
	private CompteService compteService; //à tester
	
	@Autowired
	private ClientDao clientDao; //pour aider à tester (jeux de données)
	
	
	@Test
	public void testComptesDuClient() {
		Client client1 = clientDao.save(new Client(null,"jean","Bon"));
		Compte compte1A = new Compte(null,"compte1A",50.0);
		compte1A.setClient(client1);
		compteService.sauvegarderCompte(compte1A);
		Compte compte1B = new Compte(null,"compte1B",80.0);
		compte1B.setClient(client1);
		compteService.sauvegarderCompte(compte1B);
		
		Client client2 = clientDao.save(new Client(null,"alex","Therieur"));
		Compte compte2A = new Compte(null,"compte2A",30.0);
		compte2A.setClient(client2);
		compteService.sauvegarderCompte(compte2A);
		
		List<Compte> comptesDuClient1 = compteService.comptesDuClient(client1.getNumero());
		logger.debug("comptesDuClient1=" + comptesDuClient1);
		Assertions.assertTrue(comptesDuClient1.size()==2);

	}
	
	@Test
	public void testBonTransfert() {
		Compte compteA = compteService.sauvegarderCompte(new Compte(null,"compteA",50.0));
		logger.debug("compteA_avant_virement :" + compteA );
		Long numCptA = compteA.getNumero();
		Compte compteB = compteService.sauvegarderCompte(new Compte(null,"compteB",150.0));
		logger.debug("compteB_avant_virement :" + compteB );
		Long numCptB = compteB.getNumero();
		compteService.effectuerVirement(50, numCptA, numCptB);
		Compte nouveauCompteA = compteService.compteSelonNumero(numCptA);
		logger.debug("compteA_apres_virement :" + nouveauCompteA );
		Compte nouveauCompteB = compteService.compteSelonNumero(numCptB);
		logger.debug("compteB_apres_virement :" + nouveauCompteB );
		Assertions.assertEquals(50-50, nouveauCompteA.getSolde(), 0.0001);
		Assertions.assertEquals(150+50, nouveauCompteB.getSolde(), 0.0001);
	}
	
	@Test
	public void testMauvaisTransfert() {
		Compte compteA = compteService.sauvegarderCompte(new Compte(null,"compteA",50.0));
		logger.debug("compteA_avant_virement :" + compteA );
		Long numCptA = compteA.getNumero();
		Compte compteB = compteService.sauvegarderCompte(new Compte(null,"compteB",150.0));
		logger.debug("compteB_avant_virement :" + compteB );
		Long numCptB = compteB.getNumero();
		try {
			compteService.effectuerVirement(50, numCptA, 0); //erreur volontaire , le compte 0 n'existe pas
			Assertions.fail("une exception aurait dû remonter");
		} catch (Exception e) {
			//e.printStackTrace();
			logger.debug("echec normal du virement");
		}
		Compte nouveauCompteA = compteService.compteSelonNumero(numCptA);
		logger.debug("compteA_apres_mauvais_virement :" + nouveauCompteA );
		Compte nouveauCompteB = compteService.compteSelonNumero(numCptB);
		logger.debug("compteB_apres_mauvais_virement :" + nouveauCompteB );
		//les valeurs en base ne doivent pas être modifiées si echec virement et rollback ok:
		Assertions.assertEquals(50, nouveauCompteA.getSolde(), 0.0001);
		Assertions.assertEquals(150, nouveauCompteB.getSolde(), 0.0001);
	}

}
