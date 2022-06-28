package tp.appliSpring.dao;

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
import tp.appliSpring.entity.Compte;

//@RunWith(SpringRunner.class)  //si junit4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
@SpringBootTest(classes= {AppliSpringApplication.class})//reprendre la configuration de la classe principale
@ActiveProfiles({"reInit","embeddedDB"})
//@ActiveProfiles({"reInit","remoteDB"})
public class TestDaoCompte {
	
	private static Logger logger = LoggerFactory.getLogger(TestDaoCompte.class);

	@Autowired
	private DaoCompte daoCompte; //à tester
	
	@Test
	public void testFindBySoldeGreaterThan() {
		daoCompte.save(new Compte(null,"compteA",100.0));
		daoCompte.save(new Compte(null,"compteB",-20.0));
		daoCompte.save(new Compte(null,"compteC",200.0));
		daoCompte.save(new Compte(null,"compteD",-50.0));
		daoCompte.save(new Compte(null,"compteE",0.0));
		
		List<Compte> listeCompteAvecSoldePositif = daoCompte.findBySoldeGreaterThanEqual(0);
        logger.debug("listeCompteAvecSoldePositif=" + listeCompteAvecSoldePositif);
        Assertions.assertTrue(listeCompteAvecSoldePositif.size()>=3);
	}
}
