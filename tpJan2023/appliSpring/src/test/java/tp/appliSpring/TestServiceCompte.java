package tp.appliSpring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tp.appliSpring.entity.Compte;
import tp.appliSpring.service.ServiceCompte;

@ExtendWith(SpringExtension.class) //test interprété par JUnit5 + SpringExtension
@SpringBootTest(classes= {AppliSpringApplication.class}) //reprend config de la classe principale
//@ActiveProfiles("h2")
@ActiveProfiles("postgres")
public class TestServiceCompte {

    @Autowired
    private ServiceCompte serviceCompte;

    @Test
    public void testSurLesComptes() {
        Compte cA = new Compte(null, "compteA", 50.0);
        serviceCompte.sauvegarder(cA);
        int numCptA = cA.getNumero();

        Compte cArelu = serviceCompte.rechercherCompteParNumero(numCptA);
        System.out.println("cArelu=" + cArelu);
        Assertions.assertEquals("compteA", cArelu.getLabel());
    }

    @Test
    public void testBonTransfert(){
        Compte cX = new Compte(null, "compteX", 50.0);
        serviceCompte.sauvegarder(cX);
        Compte cY = new Compte(null, "compteY", 50.0);
        serviceCompte.sauvegarder(cY);

        serviceCompte.transferer(20.0 , cX.getNumero(), cY.getNumero());

        double soldeCxApres = serviceCompte.rechercherCompteParNumero(cX.getNumero()).getSolde();
        double soldeCYApres = serviceCompte.rechercherCompteParNumero(cY.getNumero()).getSolde();
        System.out.println("Apres bon virement: soldeCxApres="+soldeCxApres + " , soldeCYApres=" + soldeCYApres);
        Assertions.assertEquals(30.0 ,soldeCxApres , 0.00001 );
        Assertions.assertEquals(70.0 ,soldeCYApres , 0.00001 );
    }

    @Test
    public void testMauvaisTransfert(){
        Compte cX = new Compte(null, "compteX", 50.0);
        serviceCompte.sauvegarder(cX);
        Compte cY = new Compte(null, "compteY", 50.0);
        serviceCompte.sauvegarder(cY);
        try {
            serviceCompte.transferer(20.0, cX.getNumero(), -cY.getNumero()); //le compte -numdeY n'existe pas !!!
        }catch(Exception ex){
            System.out.println("echec normal du virement " + ex.getMessage());
        }
        double soldeCxApres = serviceCompte.rechercherCompteParNumero(cX.getNumero()).getSolde();
        double soldeCYApres = serviceCompte.rechercherCompteParNumero(cY.getNumero()).getSolde();
        System.out.println("Apres mauvais virement: soldeCxApres="+soldeCxApres + " , soldeCYApres=" + soldeCYApres);
        Assertions.assertEquals(50.0 ,soldeCxApres , 0.00001 );
        Assertions.assertEquals(50.0 ,soldeCYApres , 0.00001 );
    }
}
