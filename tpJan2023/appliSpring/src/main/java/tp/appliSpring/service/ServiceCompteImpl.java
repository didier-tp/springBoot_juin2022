package tp.appliSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.dao.DaoCompte;
import tp.appliSpring.entity.Compte;

import java.util.List;

//@Component  //composant Spring quelconque
@Service //composant Spring de type Service métier
         //pour que cette classe java soit prise en charge par Spring
         //une instance de cette classe sera dans le spring-context (l'ensemble des objets pris en charge par spring)
//@Scope("singleton") par défaut
@Transactional() // avec propagation = Propagation.REQUIRED par defaut
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
        return daoCompte.findByClientNumero(numClient);
    }

    @Override
    public List<Compte> rechercherTousComptes() {
        return (List<Compte>) daoCompte.findAll();
    }

    @Override
    public List<Compte> rechercherComptesAvecSoldeMini(double soldeMini) {
        return daoCompte.findBySoldeGreaterThanEqual(soldeMini);
    }

    @Override
    public Compte sauvegarder(Compte compte) {
        return daoCompte.save(compte);
    }

    @Override
    public Compte sauvegarderNouveau(Compte compte) {
        return daoCompte.save(compte); //améliorable
    }

    @Override
    public Compte sauvegarderExistant(Compte compte) {
        if(daoCompte.existsById(compte.getNumero()))
            return daoCompte.save(compte);
        else
            throw new RuntimeException("impossible de mettre a jour le compte qui existe pas de numero " + compte.getNumero());
    }

    @Override
    public void supprimerCompte(int numCompte) {
          daoCompte.deleteById(numCompte);
    }

    @Override
    // @Transactional ici ou bien au dessus de l'ensemble de la classe pour ne pas l'oublier
    public void transferer(double montant, int numCptDeb, int numCptCred) {
        try {
            Compte cptDeb = daoCompte.findById(numCptDeb).orElse(null);
            cptDeb.setSolde(cptDeb.getSolde() - montant);
            daoCompte.save(cptDeb);
            Compte cptCred = daoCompte.findById(numCptCred).orElse(null);
            cptCred.setSolde(cptCred.getSolde() + montant);
            daoCompte.save(cptCred);
        }catch(Exception ex){
            throw new RuntimeException("echec virement", ex);
            //ou bien  throw new BankException("echec virement", ex);
            //où BankException serait une classe qui hérite RuntimeException ...
        }
    }
}
