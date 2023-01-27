package tp.appliSpring.reInit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tp.appliSpring.entity.Compte;
import tp.appliSpring.service.ServiceCompte;

import javax.annotation.PostConstruct;

@Component //composant Spring quelconque
@Profile("reInit") //toute cette classe ne sera activée que si le profile "reInit"
//est activé au démarrage de l'application (dans main() ou ...)
public class ReInitDefaultDataSet {

    @Autowired
    private ServiceCompte serviceCompte;

    public ReInitDefaultDataSet(){
        //constructeur appelé avant que @Autowired remplace la valeur null
        //par une référence sur un serviceCompte existant
    }

    @PostConstruct
    public void initDataSet() {
        //une méthode préfixée par @PostConstruct est appelée automatiquement par Spring
        //APRES que les @Autowired aient été pris en compte
        serviceCompte.sauvegarder(new Compte(null,"compte_a",50.0));
        serviceCompte.sauvegarder(new Compte(null,"compte_B",100.0));
        serviceCompte.sauvegarder(new Compte(null,"compte_c",150.0));
    }
}
