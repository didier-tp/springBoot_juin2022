package tp.appliSpring.service;

import java.util.List;

import tp.appliSpring.entity.Compte;
//throws RuntimeException implicites
public interface CompteService {
	List<Compte> comptesDuClient(long numClient);
	List<Compte> tousLesComptes();
	
	/**
	 * 
	 * @param numeroCompte
	 * @return Compte if found or null if not found
	 */
	Compte compteSelonNumero(long numeroCompte);
	
	void effectuerVirement(double montant,long numCptDeb ,long numCptCred);
	Compte sauvegarderCompte(Compte compte);
	void supprimerCompte(long numCpt);
}
