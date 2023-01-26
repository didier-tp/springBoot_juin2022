package tp.appliSpring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tp.appliSpring.dao.DaoClient;
import tp.appliSpring.dao.DaoCompte;
import tp.appliSpring.entity.Client;
import tp.appliSpring.entity.Compte;

import java.util.List;

@ExtendWith(SpringExtension.class) //test interprété par JUnit5 + SpringExtension
@SpringBootTest(classes= {AppliSpringApplication.class}) //reprend config de la classe principale
//@ActiveProfiles("h2")
@ActiveProfiles("postgres")
public class TestDaoCompte {

    @Autowired
    private DaoCompte daoCompte;

    @Autowired
    private DaoClient daoClient;

    @Test
    public void testCrudCompte(){
      Client clientX  = new Client(null,"jean","Bon");
      daoClient.save(clientX);
      int numClientX = clientX.getNumero();

      Compte cA = new Compte(null,"compteA",50.0);
      cA.setClient(clientX);
      daoCompte.save(cA); //saveOrUpdate , ici INSERT INTO
      int numCptA = cA.getNumero();
      System.out.println("numero de c1=" + numCptA)  ;

      daoCompte.save(new Compte(null,"compteB",20.0));

      Compte cC = new Compte(null,"compteC",200.0);
      cC.setClient(clientX);
      daoCompte.save( cC);

      Compte cArelu = daoCompte.findById(numCptA).orElse(null); //SELECT ...
      //System.out.println("cArelu=" + cArelu.toString())  ;
        System.out.println("cArelu=" + cArelu)  ;
        //Assertions.assertTrue( cArelu.getLabel().equals("compteA") );
        Assertions.assertEquals("compteA" , cArelu.getLabel());

      List<Compte> comptesSuperieursA50 = daoCompte.findBySoldeGreaterThanEqual(50);
        System.out.println("comptesSuperieursA50:");
      for(Compte c : comptesSuperieursA50){
          System.out.println("\t" + c);
      }

      //List<Compte> comptesDuClientX = daoCompte.findByClientNumero(numClientX);
        List<Compte> comptesDuClientX = daoCompte.findByNumeroDeClientQueJaime(numClientX);
        System.out.println("comptesDuClientX:");
      for(Compte c : comptesDuClientX){
            System.out.println("\t" + c);
      }
    }

}
