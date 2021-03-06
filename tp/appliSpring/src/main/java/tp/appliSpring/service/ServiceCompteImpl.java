package tp.appliSpring.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.dao.DaoCompte;
import tp.appliSpring.entity.Compte;
//classe de Service prise en charge par spring
@Service() //nom du composant spring = nom_classe_avec_minuscule = serviceCompteImpl
//@Service("serviceCompteImpl")
@Transactional
public class ServiceCompteImpl implements ServiceCompte{
	
	//@Qualifier("simu") //ou bien 
	//@Qualifier("jpa") pour anciennes versions sans spring-data
	@Autowired //injection de dépendance par annotation
	           //daoCompte sera initialisée par Spring pour
	           //référencer un composant existant compatible 
	           //avec l'interface DaoCompte
	//@Inject du concurrent CDI se comporte exactement pareil que @Autowired
	private DaoCompte daoCompte=null;
	
	

	public ServiceCompteImpl() {
		super();
		System.out.println("dans constructeur de ServiceCompteImpl , daoCompte="+daoCompte);
		//impossible de déleguer des appels à daoCompte qui est null
	}
	
	@PostConstruct()
	public void initialiser() {
		System.out.println("dans méthode préfixée par @PostConstruct() , daoCompte="+daoCompte);
		//possible ici de déleguer des appels à daoCompte qui n'est plus null
	}

	@Override
	public Compte rechercherCompteParNumero(long numero) {
		return daoCompte.findById(numero).orElse(null);
	}
	
	@Override
	public Compte sauvegarderCompte(Compte compte) {
		return daoCompte.save(compte);
	}

	@Override
	public List<Compte> rechercherTousComptes() {
		return daoCompte.findAll();
	}

	@Override
	public List<Compte> rechercherComptesDuClient(long numClient) {
		//return daoCompte.findByClientId(numClient);
		return daoCompte.findByClientIdAvecRequeteSpecifique(numClient);
	}

	

	@Override
	public void supprimerCompte(long numCpt) {
		daoCompte.deleteById(numCpt);
		
	}

	@Override
	//@Transactional(/*propagation = Propagation.REQUIRED */)
	//NB: @Transactional peut être placé ici ou bien sur l'ensemble de la classe
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		Compte cptDeb = daoCompte.findById(numCptDeb).get();
		//cptDeb est ici à l'état détache si pas de @Transactional
		//et à l'état persistant si @Transactional
		cptDeb.setSolde(cptDeb.getSolde() - montant);
		//daoCompte.save(cptDeb); //utile que si pas de @Transactional
		
		Compte cptCred = daoCompte.findById(numCptCred).get();
		cptCred.setSolde(cptCred.getSolde() + montant);
		//daoCompte.save(cptCred); //utile que si pas de @Transactional
	}
}
