package tp.appliSpring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.dao.ClientDao;
import tp.appliSpring.dao.CompteDao;
import tp.appliSpring.entity.Client;
import tp.appliSpring.entity.Compte;
import tp.appliSpring.service.CompteService;

@Service //@Component de type "service metier"
//@Transactional
public class CompteServiceImpl implements CompteService {

	@Autowired //@Autowired (spécifique Spring) est un équivalent de
	//@Inject du concurrent DI/CDI/EJB/JEE
	//@Autowired demande au framework spring d'initialiser la référence compteDao
	//pour que ça pointe vers un commposant spring qui est compatible avec l'interface
	private CompteDao compteDao=null;
	
	@Autowired
	private ClientDao clientDao;
	
	@Override
	public List<Compte> comptesDuClient(long numClient) {
		/*
		Client client = clientDao.findById(numClient).get();
		return client.getComptes(); //lazy exception (mauvaise solution)
		*/
		//return compteDao.findByClientNumero(numClient);
		return compteDao.findByClientNumeroQueJaime(numClient);
	}

	@Override
	public List<Compte> tousLesComptes() {
		return (List<Compte>) compteDao.findAll(); //caster Iterable en List ou bien ...
	}

	@Override
	public Compte compteSelonNumero(long numeroCompte) {
		//return compteDao.findById(numeroCompte).get(); //return exception si empty
		return compteDao.findById(numeroCompte).orElse(null); //return null if not null
	}
	
	
	/*
	//sans @Tansactional = pas bien !!!! (4 petites transactions séparées sur le dao)
	public void effectuerVirement(double montant, long numCptDeb, long numCptCred) {
		Compte compteDeb = compteDao.findById(numCptDeb).get();
        compteDeb.setSolde(compteDeb.getSolde() - montant); //modif à l'état détaché
        compteDao.save(compteDeb);
        
        Compte compteCred = compteDao.findById(numCptCred).get();
        compteCred.setSolde(compteCred.getSolde() + montant); //modif à l'état détaché
        compteDao.save(compteCred);
	}
	 */

	@Override
	@Transactional(/*propagation = Propagation.REQUIRED*/)
	public void effectuerVirement(double montant, long numCptDeb, long numCptCred) {
		Compte compteDeb = compteDao.findById(numCptDeb).get();
        compteDeb.setSolde(compteDeb.getSolde() - montant); //à l'état persistant
        //compteDao.save(compteDeb);//pas nécessaire si @Transactional
        
        Compte compteCred = compteDao.findById(numCptCred).get();
        compteCred.setSolde(compteCred.getSolde() + montant); //à l'état persistant
        //compteDao.save(compteCred);//pas nécessaire si @Transactional
	}

	@Override
	public Compte sauvegarderCompte(Compte compte) {
		return compteDao.save(compte);
	}

	@Override
	public void supprimerCompte(long numCpt) {
		compteDao.deleteById(numCpt);
	}

}
