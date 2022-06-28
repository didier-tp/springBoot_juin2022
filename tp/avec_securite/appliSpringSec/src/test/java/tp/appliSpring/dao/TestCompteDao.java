package tp.appliSpring.dao;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.entity.Compte;

//@RunWith(SpringRunner.class) //si junit 4
@ExtendWith(SpringExtension.class) //si junit5/jupiter
//@SpringBootTest(classes= {AppliSpringApplication.class}) //meme config que classe avec main()
@DataJpaTest //better of SpringBootTest for dao testing if use of spring-data-jpa extension
//@ActiveProfiles({"remoteDb"})
@ActiveProfiles({"embbededDb"})
public class TestCompteDao {
	
	private static Logger logger = LoggerFactory.getLogger(TestCompteDao.class);

	@Autowired //injection de dépendance spring
	private CompteDao compteDao; //à tester
	
	
	@Test()
	void testDao1() {
		Compte compteA = new Compte(null,"compteA",50.0);
		compteDao.save(compteA);
		Long numCptA = compteA.getNumero();
		//Compte compteARelu = compteDao.findById(numCptA).get(); //renvoi exception si pas trouvé
		Compte compteARelu = compteDao.findById(numCptA).orElse(null);
		logger.debug("compteARelu="+compteARelu.toString());
		Assertions.assertEquals("compteA",compteARelu.getLabel());
		Assertions.assertEquals(50.0,compteARelu.getSolde(),0.0001);
	}
	
	@Test()
	void testDao2() {
		Compte compteA = compteDao.save(new Compte(null,"compteA",50.0));
		Compte compteB = compteDao.save(new Compte(null,"compteB",-50.0));
		Compte compteC = compteDao.save(new Compte(null,"compteC",150.0));
		Compte compteD = compteDao.save(new Compte(null,"compteD",-150.0));
		
        List<Compte> comptes = (List<Compte>) compteDao.findAll();
        Assertions.assertTrue(comptes.size()>=4);
        logger.debug("all comptes:" + comptes);
        
        List<Compte> comptesAvecDecouvert =  compteDao.findBySoldeLessThan(0.0);
        Assertions.assertTrue(comptesAvecDecouvert.size()>=2);
        logger.debug("comptesAvecDecouvert:" + comptesAvecDecouvert);
	}
}
