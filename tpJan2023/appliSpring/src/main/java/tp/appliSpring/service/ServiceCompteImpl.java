package tp.appliSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tp.appliSpring.dao.DaoCompte;
import tp.appliSpring.entity.Compte;

import java.util.List;

//@Component  //composant Spring quelconque
@Service //composant Spring de type Service métier
         //pour que cette classe java soit prise en charge par Spring
         //une instance de cette classe sera dans le spring-context (l'ensemble des objets pris en charge par spring)
//@Scope("singleton") par défaut
public class ServiceCompteImpl implements ServiceCompte{

    @Autowired //demander à Spring d'initialiser la référence daoCompte
    //en pointant vers un composant "spring" existant de l'application
    //dont le type est compatible avec l'interface DaoCompte
    private DaoCompte daoCompte;

    @Override
    public Compte rechercherCompteParNumero(int numCompte) {
        return daoCompte.findById(numCompte).orElse(null);
    }

    @Override
    public List<Compte> rechercherComptesDuClient(int numClient) {
        return null;
    }

    @Override
    public Compte sauvegarder(Compte compte) {
        return daoCompte.save(compte);
    }

    @Override
    public void supprimerCompte(int numCompte) {

    }

    @Override
    public void transferer(double montant, int numCptDeb, int numCptCred) {

    }
}
