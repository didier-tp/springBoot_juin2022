package tp.appliSpring.service;

import java.util.List;

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
	private DaoCompte daoCompte=null;

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
