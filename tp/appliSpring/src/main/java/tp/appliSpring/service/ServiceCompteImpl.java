package tp.appliSpring.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.appliSpring.dao.DaoCompte;
import tp.appliSpring.entity.Compte;

@Service //classe de Service prise en charge par spring
public class ServiceCompteImpl implements ServiceCompte{
	
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
		return daoCompte.findById(numero);
	}
	
	@Override
	public Compte sauvegarderCompte(Compte compte) {
		return daoCompte.save(compte);
	}

	@Override
	public List<Compte> rechercherTousComptes() {
		return null;
	}

	@Override
	public List<Compte> rechercherComptesDuClient(long numClient) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void supprimerCompte(long numCpt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		// TODO Auto-generated method stub
		
	}
}
