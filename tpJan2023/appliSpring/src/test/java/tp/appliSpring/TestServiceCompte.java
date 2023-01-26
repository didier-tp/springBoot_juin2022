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
}
