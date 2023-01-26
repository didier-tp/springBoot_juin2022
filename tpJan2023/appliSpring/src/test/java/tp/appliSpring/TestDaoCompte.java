package tp.appliSpring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tp.appliSpring.dao.DaoCompte;
import tp.appliSpring.entity.Compte;

import java.util.List;

@ExtendWith(SpringExtension.class) //test interprété par JUnit5 + SpringExtension
@SpringBootTest(classes= {AppliSpringApplication.class}) //reprend config de la classe principale
//@ActiveProfiles("h2")
@ActiveProfiles("postgres")
public class TestDaoCompte {

    @Autowired
    private DaoCompte daoCompte;

    @Test
    public void testCrudCompte(){
      Compte cA = new Compte(null,"compteA",50.0);
      daoCompte.save(cA); //saveOrUpdate , ici INSERT INTO
      int numCptA = cA.getNumero();
      System.out.println("numero de c1=" + numCptA)  ;

      daoCompte.save(new Compte(null,"compteB",20.0));
      daoCompte.save(new Compte(null,"compteC",200.0));

      Compte cArelu = daoCompte.findById(numCptA).orElse(null); //SELECT ...
      //System.out.println("cArelu=" + cArelu.toString())  ;
        System.out.println("cArelu=" + cArelu)  ;
        //Assertions.assertTrue( cArelu.getLabel().equals("compteA") );
        Assertions.assertEquals("compteA" , cArelu.getLabel());

      List<Compte> comptesSuperieursA50 = daoCompte.findBySoldeGreaterThanEqual(50);
      for(Compte c : comptesSuperieursA50){
          System.out.println("\t" + c);
      }
    }

}
